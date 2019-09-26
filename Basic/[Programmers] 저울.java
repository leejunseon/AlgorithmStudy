/* https://programmers.co.kr/learn/courses/30/lessons/42886 */
//현재 주어진 저울추로 0부터 n까지 만들 수 있다고 했을 때 -> answer = n 으로 정의
//weight을 오름차순 정렬하고, 순차적으로 탐색하면서
//answer+1이 weight[i]보다 크거나 같다면
//answer=answer+=weight[i]가 됨.
//작다면 answer+1을 못만드므로
//답은 answer+1이 됨.

//알고보면 간단한 문제지만 아이디어를 떠올리기가 힘듦

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] weight= {3, 1, 6, 2, 7, 30, 1};
		System.out.println(solution(weight));
	}

	public static int solution(int[] weight) {
		int answer=0;
		Arrays.sort(weight);
		for(int i=0;i<weight.length;i++) {
			if(answer+1>=weight[i]) {
				answer+=weight[i];
			}else
				break;
		}

		return answer+1;
	}
}
