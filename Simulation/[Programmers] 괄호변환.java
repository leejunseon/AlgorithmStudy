/* https://programmers.co.kr/learn/courses/30/lessons/60058 */
//요구조건을 찬찬히 따라가면 풀 수 있는 문제.

import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String p="()))((()";
		System.out.println(solution(p));
	}

	public static String solution(String p) {
		if(isRight(p)||p=="")
			return p;

		String result="";

		int left=0;
		int right=0;
		int uIndex=0;
		for(int i=0;i<p.length();i++) {
			switch(p.charAt(i)) {
			case '(':
				left++;
				break;
			case ')':
				right++;
				break;
			}
			if(left==right) {
				uIndex=i;
				break;
			}
		}

		String u=p.substring(0,uIndex+1);
		String v=p.substring(uIndex+1);

		if(isRight(u))
			result=u+solution(v);
		else {
			String resultU="";
			for(int i=1;i<u.length()-1;i++) {
				switch(u.charAt(i)) {
				case '(':
					resultU+=")";
					break;
				case ')':
					resultU+="(";
					break;
				}
			}
			result="("+solution(p.substring(uIndex+1))+")"+resultU;
		}

        return result;
    }

	public static boolean isRight(String s) {
		Stack<Character> st=new Stack<Character>();
		for(int i=0;i<s.length();i++) {
			switch(s.charAt(i)) {
			case '(':
				st.push(s.charAt((i)));
				break;
			case ')':
				if(!st.isEmpty())
					st.pop();
				else
					return false;
				break;
			}
		}

		if(st.isEmpty())
			return true;
		else
			return false;
	}
}
