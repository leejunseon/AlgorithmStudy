/* https://programmers.co.kr/learn/courses/30/lessons/42746 */
//String을 사전순으로 내림차순 sorting
//sorting시 두 문자열의 접두사 길이가 두 문자열 중 짧은 문자열과 같을 때는
//순서를 다르게 이어붙인 두 문자열의 내림차순
//"0000..."일 시 예외처리

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] numbers= {3, 30, 34, 5, 9};
		System.out.println(solution(numbers));
	}

	public static String solution(int[] numbers) {
        String answer = "";
        String[] num=new String[numbers.length];
        for(int i=0;i<numbers.length;i++) {
        	num[i]=Integer.toString(numbers[i]);
        }

        Arrays.sort(num,new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				int min=Math.min(arg0.length(), arg1.length());
				if(!arg0.substring(0,min).equals(arg1.subSequence(0, min)))
					return arg1.substring(0,min).compareTo(arg0.substring(0,min));
				else {
					String s1=arg0+arg1;
					String s2=arg1+arg0;
					return s2.compareTo(s1);
				}
			}

        });

        if(num[0].equals("0"))
        	return "0";

        for(int i=0;i<num.length;i++)
        	answer+=num[i];

      	return answer;
    }
}
