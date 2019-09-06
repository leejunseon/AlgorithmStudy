/* https://programmers.co.kr/learn/courses/30/lessons/42578 */

//입력값에서 옷의 종류를 key로 하는 HashMap 선언
//각 종류별로 의상이 몇개 있는지 계산
//각 의상종류+1을 차례로 곱한 후 -1 (의상 종류별로 안입을 경우 포함한 경우의 수 모두 곱한 후, 다 안입을 경우 빼는 식)
//자료구조 어떻게 써먹을지 잘 고민할 것. 이문제는 HashMap

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String[][] clothes= {
								{"yellow_hat","headgear"},
								{"blue_sunglasses","eyewear"},
								{"green_turban","headgear"}
							};
		
		System.out.println(solution(clothes));
	}
	
	public static int solution(String[][] clothes) {
		int answer = 1;
		
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<clothes.length;i++) {
			String input=clothes[i][1];
			if(map.get(input)==null) {
				map.put(input, 1);
			}else {
				map.put(input, map.get(input)+1);
			}
		}
		
		for(int i:map.values())
			answer*=(i+1);
		
		return answer-1;
	}
}
