/* https://programmers.co.kr/learn/courses/30/lessons/42629 */
//우선순위 큐 사용
//0일부터 k일 k이전까지 하루에 stock--
//보급 들어오는 날은 pq에 보급량 저장
//stock==0되는 날 pq에서 가장 큰 거 꺼내 씀 -> answer++;
//k일 이후 answer반환
//우선순위큐 적용 아이디어 떠올리기가 힘들었음

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int stock=4;
		int[] dates= {4,10,15};
		int[] supplies= {20,5,10};
		int k=30;
		System.out.println(solution(stock,dates,supplies,k));
	}

	public static int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer=0;
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}

		});

		int dateIndex=0;
		//0부터 시작하므로 k이전까지
		for(int i=0;i<k;i++) {
			if(dateIndex<dates.length&&i==dates[dateIndex]) {
				pq.add(supplies[dateIndex]);
				dateIndex++;
			}

			if(stock==0) {
				stock+=pq.remove();
				answer++;
			}

			stock--;
		}
		return answer;
	}
}
