/* http://tech.kakao.com/2017/08/11/code-festival-round-1/ */
//완전탐색할때는 꼭 dynamic programming 적용할 수 있는지 검토할 것.

import java.util.*;
import java.io.*;

public class Solution {
	public static final int MOD = 20170805;
	public static int[] dy= {0,1};
	public static int[] dx= {1,0};
	public static int[][][] cache;

	public static void main(String[] args) {
		int[][] cityMap= {{0, 2, 0, 0, 0, 2},
						  {0, 0, 2, 0, 1, 0},
						  {1, 0, 0, 2, 2, 0}};
		System.out.println(solution(cityMap.length,cityMap[0].length,cityMap));
	}

	public static int solution(int m, int n, int[][] cityMap) {
		cache=new int[m][n][2];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++)
				Arrays.fill(cache[i][j], -1);
		}
		return search(0,0,0,cityMap);
	}

	public static int search(int y,int x,int direction,int[][] cityMap) {
		if(y==cityMap.length-1&&x==cityMap[0].length-1)
			return 1;

		if(cache[y][x][direction]!=-1)
			return cache[y][x][direction];

		cache[y][x][direction]=0;

		//현재 위치에 따라
		switch(cityMap[y][x]) {
		case 0://이동 가능
			for(int i=0;i<2;i++) {
				//0:오른쪽
				//1:아래
				int nexty=y+dy[i];
				int nextx=x+dx[i];
				if(0<=nexty&&nexty<cityMap.length&&0<=nextx&&nextx<cityMap[0].length&&cityMap[nexty][nextx]!=1) {
					cache[y][x][direction]+=search(nexty,nextx,i,cityMap);
					cache[y][x][direction]%=MOD;
				}
			}
			break;
		case 2://회전X
			switch(direction) {
			case 0:
				if(x+1<cityMap[0].length) {
					cache[y][x][direction]+=search(y,x+1,0,cityMap);
					cache[y][x][direction]%=MOD;
				}
				break;
			case 1:
				if(y+1<cityMap.length) {
					cache[y][x][direction]+=search(y+1,x,1,cityMap);
					cache[y][x][direction]%=MOD;
				}
				break;
			}
			break;
		}

		return cache[y][x][direction];
	}
}
