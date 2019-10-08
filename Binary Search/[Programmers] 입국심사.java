/* https://programmers.co.kr/learn/courses/30/lessons/43238 */
//times 중에서 가장 큰 요소 * n = max,
//0 = min
//으로 놓고 이분탐색.
//min시간동안 모두 심사 가능한지 검사
//-> 가능하다면 min=mid+1, 가능하지 않다면 max=mid

//long 자료형 선언할 때는 끝에 L 반드시 붙이기

import java.io.*;
import java.util.*;

public class Solution {
	public static int N;

	public static void main(String[] args) {
		int[] times= {7,10};
		int n=6;
		System.out.println(solution(n,times));
	}

	public static long solution(int n, int[] times) {
		N=n;
		long max=0L;
		for(int i=0;i<times.length;i++) {
			max=Math.max(max, times[i]);
		}

		return getMin(times,0,max*n);
	}

	public static long getMin(int[] times,long min,long max) {
		if(min==max)
			return min;

		long mid=(min+max)/2;

		if(getPeople(times,mid)) {
			return getMin(times,min,mid);
		}else
			return getMin(times,mid+1,max);
	}

	//심사 가능한지
	public static boolean getPeople(int[] times,long time) {
		long people=0L;
		for(int i=0;i<times.length;i++) {
			people+=time/times[i];
			if(people>=N)
				break;
		}
		if(people>=N)
			return true;
		else
			return false;
	}
}
