/* https://programmers.co.kr/learn/courses/30/lessons/42883 */
//number 숫자를 차례대로 Stack에 넣으면서
//stack.peek()이 현재 숫자보다 작으면 pop => 반복
//반복 이후 현재숫자 push
//k가 0이되면 나머지는 모두 push
//number의 length-k만큼 문자열로 옮겨서 반환(length-k 이후에 다른 문자 남는 경우가 있음)

//stack 아이디어 떠올리지 못했음. 자료구조 적용 연습 더할것

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String number="1231234";
		int k=3;
		System.out.println(solution(number,k));
	}

	public static String solution(String number, int k) {
		Stack<Character> st=new Stack<Character>();
		int remain=k;
		for(int i=0;i<number.length();i++) {
        	while(!st.isEmpty()&&k!=0&&st.peek()<number.charAt(i)&&remain-->0) {
        		if(remain==0)
        			break;
        		remain--;
        		st.pop();
        	}
        	st.push(number.charAt(i));
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<number.length()-k;i++)
			sb.append(st.get(i));
        return sb.toString();
    }
}
