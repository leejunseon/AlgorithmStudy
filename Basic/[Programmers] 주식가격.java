/* https://programmers.co.kr/learn/courses/30/lessons/42584 */

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] prices= {1, 2, 3, 2, 3};
		int[] result=solution(prices);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}

	public static int[] solution(int[] prices) {
		int[] answer=new int[prices.length];
		for(int i=0;i<prices.length;i++) {
			int time=0;
			for(int j=i+1;j<prices.length;j++) {
				time++;
				if(prices[i]>prices[j])
					break;
			}
			answer[i]=time;
		}
		return answer;
	}
}
