/* https://programmers.co.kr/learn/courses/30/lessons/42747 */
//인용(citations)배열을 오름차순 정렬.-> citations[i]번 이상 인용된 논문 수 = citations.length-i가 됨
//min(citations[i],citations-i) 중 최댓값 = 정답
//규칙찾는 게 어려웠음

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] citations= {0, 5, 6, 7, 8};
		System.out.println(solution(citations));
	}

	public static int solution(int[] citations) {
		Arrays.sort(citations);

	    int max = 0;
	    for(int i = citations.length-1; i > -1; i--){
	        int min = (int)Math.min(citations[i], citations.length - i);
	        if(max < min) max = min;
            else
                break;
	    }

	    return max;
	}
}
