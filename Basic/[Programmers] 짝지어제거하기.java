/* https://programmers.co.kr/learn/courses/30/lessons/12973 */

import java.io.*;
import java.util.*;

class Solution {
	public static String str="";

	public static void main(String[] args) {
		Solution sol=new Solution();

		String s="cdcd";
		System.out.println(sol.solution(s));
	}

	public int solution(String s){
		Stack<Character> st=new Stack<Character>();
		for(int i=0;i<s.length();i++) {
			if(st.isEmpty())
				st.add(s.charAt(i));
			else {
				if(st.peek()==s.charAt(i))
					st.pop();
				else
					st.push(s.charAt(i));
			}
		}

		if(st.isEmpty())
			return 1;
		else
			return 0;
	}
}
