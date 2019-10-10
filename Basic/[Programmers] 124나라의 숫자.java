/* https://programmers.co.kr/learn/courses/30/lessons/12899 */
//3진법 계산을 기반으로
//나머지가 0일 때는 "4"를 붙이고, n-=1
//나머지가 1일 때는 "1" 붙이고,
//나머지가 2일 때는 "2" 붙이기

import java.io.*;
import java.util.*;

public class Solution {
	public static String[] num= {"","1","2","4"};

	public static void main(String[] args) {
		int n=2;
		System.out.println(solution(n));
	}

	public static String solution(int n) {
		String answer = "";

		while(n>3) {
			if(n%3==0) {
				answer="4"+answer;
				n/=3;
				n-=1;
			}else {
				answer=num[n%3]+answer;
				n/=3;
			}
		}
		answer=num[n]+answer;

		return answer;
	}
}
