/* https://programmers.co.kr/learn/courses/30/lessons/12913 */
//getMax(row,col)을 land[row][col]에서 얻을 수 있는 최대 점수라고 가정하면,
//getMax(row,col)=land[row][col]+max((col 제외)getMax(row+1,0),getMax(row+1,1), ... ,getMax(row+1,land[0].length))
//라고 할 수 있다.
//위 점화식을 바탕으로 DP(TopBottom)구현

import java.io.*;
import java.util.*;

public class Solution {
	public static int[][] cache;

	public static void main(String[] args) {
		int[][] land= {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
		System.out.println(solution(land));
	}

	public static int solution(int[][] land) {
		int answer=0;
		cache=new int[land.length][land[0].length];
		for(int i=0;i<land.length;i++)
			Arrays.fill(cache[i], -1);
		for(int i=0;i<land[0].length;i++) {
			answer=Math.max(answer, getMax(land,0,i));
		}
		return answer;
	}

	public static int getMax(int[][] land,int row,int col) {
		if(row==land.length-1)
			return land[row][col];

		if(cache[row][col]!=-1)
			return cache[row][col];

		cache[row][col]=0;
		for(int i=0;i<land[0].length;i++) {
			if(i==col)
				continue;
			cache[row][col]=Math.max(cache[row][col], land[row][col]+getMax(land,row+1,i));
		}
		return cache[row][col];
	}
}
