/*  https://programmers.co.kr/learn/courses/30/lessons/42626 */
//우선순위 큐 사용해서 전체 scoville지수가 K넘어가거나 pq.size()가 1이 될때까지 scoville업데이트
//루프 나와서 scoville>=K이면 업데이트 횟수 반환, 아니면 -1 반환

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] scoville= {1, 2, 3, 9, 10, 12};
		int k=10000;
		System.out.println(solution(scoville,k));
	}

	public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
        for(int i=0;i<scoville.length;i++)
        	pq.add(scoville[i]);

        while(pq.peek()<K&&pq.size()>=2) {
        	pq.add(pq.remove()+pq.remove()*2);
        	answer++;
        }

        if(pq.peek()>=K)
        	return answer;
        else
        	return -1;
    }
}
