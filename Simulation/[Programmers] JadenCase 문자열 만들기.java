/* https://programmers.co.kr/learn/courses/30/lessons/12951 */
//StringTokenizer 와 split 쓸 때 차이점
//split은 split문자를 공백으로 대체하고 자릿수 유지
//StringTokenizer는 자릿수 유지 X
//ex)
/*
public static void main(String[] args) {
	String s="sdfsdf]]sdfsdfsdf]]]]]]]]]]]]sdfsdf]sdf]sdf]sdff";

	String[] split=s.split("]");
	for(String S:split)
		System.out.print(S+" ");

	System.out.println();

	StringTokenizer st=new StringTokenizer(s,"]");
	while(st.hasMoreTokens())
		System.out.print(st.nextToken()+" ");
}

결과값)
sdfsdf  sdfsdfsdf            sdfsdf sdf sdf sdff
sdfsdf sdfsdfsdf sdfsdf sdf sdf sdff

*/


import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String s=" sdf      sdfsdf  ";
		System.out.println("("+solution(s)+")");
	}

	public static String solution(String s) {
		String answer="";
		s=s.toLowerCase();
		String[] split=s.split(" ");
		for(String sub:split) {
			if(sub.length()>=1) {
				char[] arr=sub.toCharArray();
				arr[0]=Character.toUpperCase(arr[0]);
				sub=new String(arr);
			}
			answer+=sub+" ";
		}

		for(int i=s.length()-1;i>=0;i--) {
			if(s.charAt(i)==' ')
				answer+=" ";
			else
				break;
		}

		return answer.substring(0,answer.length()-1);
	}
}
