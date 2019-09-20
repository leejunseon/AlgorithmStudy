/* https://programmers.co.kr/learn/courses/30/lessons/43105 */
//(y,x)에서 시작했을 때 최대값은
//(y+1,x)에서 시작했을 때와 (y+1,x+1)에서 시작했을 때 중 더 큰 값 + (y,x)의 값
//이라고 할 수 있다.
//TopBottom DP 사용

import java.util.*;

public class Solution {
	public static int n;
	public static int[][] cache;

	public static void main(String[] args) {
		int[][] triangle= {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		System.out.println(solution(triangle));
	}

	public static int solution(int[][] triangle) {
		n=triangle.length;
        cache=new int[n][n];
        for(int i=0;i<cache.length;i++) {
        	Arrays.fill(cache[i], -1);
        }

        return getMax(0,0,triangle);
    }

	public static int getMax(int y,int x,int[][] tri) {
		if(y==n-1)
			return tri[y][x];

		if(cache[y][x]!=-1)
			return cache[y][x];

		cache[y][x]=0;
		cache[y][x]=Math.max(getMax(y+1,x,tri), getMax(y+1,x+1,tri))+tri[y][x];
		return cache[y][x];
	}
}
