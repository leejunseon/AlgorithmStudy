/* https://programmers.co.kr/learn/courses/30/lessons/42628 */
//max뽑을 큐, min뽑을 큐 두개 사용

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String[] operations= {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
		int[] result=solution(operations);
		System.out.println(result[0]+","+result[1]);
	}

	public static int[] solution(String[] operations) {
		int[] answer=new int[2];
		PriorityQueue<Integer> min=new PriorityQueue<Integer>();
		PriorityQueue<Integer> max=new PriorityQueue<Integer>(Collections.reverseOrder());
		StringTokenizer st;

		for(int i=0;i<operations.length;i++) {
			st=new StringTokenizer(operations[i]);
			String inst=st.nextToken();
			int value=Integer.parseInt(st.nextToken());

			switch(inst) {
			case "I":
				min.add(value);
				max.add(value);
				break;
			case "D":
				int remove=0;
				if(min.size()>0) {
					if(value==-1) {
						remove=min.remove();
						max.remove(remove);
					}else {
						remove=max.remove();
						min.remove(remove);
					}
				}
				break;
			}
		}

		if(min.size()==0)
			return answer;
		else {
			answer[0]=max.peek();
			answer[1]=min.peek();
			return answer;
		}
	}
}
