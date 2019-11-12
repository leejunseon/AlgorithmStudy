/* https://programmers.co.kr/learn/courses/30/lessons/12953 */

import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) {
		Solution s=new Solution();

		int[] arr= {2,6,8,14};
		System.out.println(s.solution(arr));
	}

	public int solution(int[] arr) {
		return getAns(1,arr);
	}

	public int getAns(int l,int[] arr) {
		if(arr.length==0)
			return l;
		return getAns(lcm(l,arr[0]),Arrays.copyOfRange(arr, 1, arr.length));
	}

	public int gcd(int a,int b) {
		while(b>0) {
			int tmp=a;
			a=b;
			b=tmp%a;
		}
		return a;
	}

	public int lcm(int a,int b) {
		return a*b/gcd(a,b);
	}
}
