/* https://programmers.co.kr/learn/courses/30/lessons/43165 */
//주어지는 numbers배열의 각 요소에 -1 혹은 1 곱하고 모두 더한 값이 Target과 같은 경우의 수 구하기
//1. op배열 선언 후 각 요소에 -1 or 1 넣음 (dfs 사용)
//2. 다 채운 후 연산 결과가 Target과 같으면 1 반환 -> answer에 더하기
//3. 전체 탐색 후의 answer 반환

import java.util.*;

public class Solution {
	public static int Target;

	public static void main(String[] args) {
		int[] numbers= {1, 1, 1, 1, 1};
		System.out.println(solution(numbers,3));
	}

	public static int solution(int[] numbers, int target) {
		Target=target;
        int[] op=new int[numbers.length];
        return getAnswer(numbers,op,-1);
    }

	public static int getAnswer(int[] numbers,int[] op,int current) {
		int answer=0;

		//기저사항
		if(current==numbers.length-1) {
			int result=0;
			for(int i=0;i<numbers.length;i++) {
				result+=numbers[i]*op[i];
			}
			if(result==Target)
				return 1;
			else
				return 0;
		}

		for(int i=0;i<2;i++) {
			switch(i) {
			case 0:
				op[current+1]=-1;
				break;
			case 1:
				op[current+1]=1;
				break;
			}
			answer+=getAnswer(numbers,op,current+1);
		}

		return answer;
	}
}
