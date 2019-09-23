/* https://programmers.co.kr/learn/courses/30/lessons/42587 */
//우선순위 내림차순으로 큐에 넣고, 프린트하나 빠질 때마다 우선순위도 하나 빼기
//큐 사용

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] priorities= {2, 1, 3, 2};
		int location=0;
		System.out.println(solution(priorities,location));
	}

	public static int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> prints=new LinkedList<Integer>();
        Queue<Integer> priority=new LinkedList<Integer>();

        for(int i=0;i<priorities.length;i++) {
        	prints.add(priorities[i]);
        }

        Arrays.sort(priorities);
        for(int i=priorities.length-1;i>=0;i--) {
        	priority.add(priorities[i]);
        }

        while(true) {
        	if(prints.peek()<priority.peek()) {
        		prints.add(prints.remove());
        	}else {
        		prints.remove();
        		priority.remove();
            	answer++;
            	if(location==0)
            		break;
        	}

        	if(location==0)
    			location=prints.size()-1;
    		else
    			location--;
        }

        return answer;
    }
}
