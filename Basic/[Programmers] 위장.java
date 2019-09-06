/* https://programmers.co.kr/learn/courses/30/lessons/42578 */

//�Է°����� ���� ������ key�� �ϴ� HashMap ����
//�� �������� �ǻ��� � �ִ��� ���
//�� �ǻ�����+1�� ���ʷ� ���� �� -1 (�ǻ� �������� ������ ��� ������ ����� �� ��� ���� ��, �� ������ ��� ���� ��)
//�ڷᱸ�� ��� ������� �� ����� ��. �̹����� HashMap

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
