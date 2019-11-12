/* https://programmers.co.kr/learn/courses/30/lessons/12980 */

import java.io.*;
import java.util.*;

class Solution {
	int answer=0;

	public static void main(String[] args) {
		Solution sol=new Solution();

		int n=5000;
		System.out.println(sol.solution(n));
	}

	public int solution(int n) {
		int answer=0;
		while(n>1) {
			if(n%2==1) {
				answer++;
				n--;
			}
			n/=2;
		}

		return answer+1;
	}
}
