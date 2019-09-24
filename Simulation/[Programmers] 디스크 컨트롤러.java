/* https://programmers.co.kr/learn/courses/30/lessons/42627 */
//jobs를 실행시간 -> 시작 시간 순으로 오름차순하여 list에 넣는다.
//time을 0으로 설정하고, start시간과 비교하면서 수행가능한 job은 list에서 빼고, time을 업데이트한다.
//수행가능한게 없으면 time++하고 넘어간다.
//위의 과정을 list가 빌때까지 반복한다.
//전체 과정에서 얻어진 수행시간 합을 jobs의 갯수로 나눈다.
//로직을 간단하게 구현하는게 힘들었다.

import java.util.*;

public class Solution {
	public static Map<Integer,Set<Integer>> job;

	public static void main(String[] args) {
		int[][] jobs= {{1, 1}, {1, 1}, {3, 1}};
		System.out.println(solution(jobs));
	}

	public static int solution(int[][] jobs) {
		List<job> list=new ArrayList<job>();
		for(int i=0;i<jobs.length;i++)
			list.add(new job(jobs[i][0],jobs[i][1]));

		Collections.sort(list);

		int time=0;
		int sum=0;
		while(!list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				job j=list.get(i);
				if(j.start<=time) {
					time+=j.length;
					sum+=time-j.start;
					list.remove(i);
					break;
				}

				if(i==list.size()-1)
					time++;
			}
		}

		return sum/jobs.length;
	}
}

class job implements Comparable<job>{
	int start;
	int length;
	job(int start,int length){
		this.start=start;
		this.length=length;
	}
	@Override
	public int compareTo(job arg0) {
		// TODO Auto-generated method stub
		if(this.length!=arg0.length)
			return this.length-arg0.length;
		else
			return this.start-arg0.start;
	}
}
