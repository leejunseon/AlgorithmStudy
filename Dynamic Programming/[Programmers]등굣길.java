/* https://programmers.co.kr/learn/courses/30/lessons/42898 */
//최단거리이므로 오른쪽/아래쪽으로만 이동
//(y,x)에서의 최단거리 경우의 수는 (y+1,x)와 (y,x+1)에서의 경우의 수의 합.
//puddle이 있는 곳은 탐색 X
//puddle의 y,x를 거꾸로 입력해서 한참 삽질함. 문제 똑바로 읽을것.

import java.util.*;

public class Solution {
	public static final int mod=1000000007;
	public static int[][] map;
	public static int[][] cache;
	public static int Ylen;
	public static int Xlen;
	public static int[] dy= {1,0};
	public static int[] dx= {0,1};

	public static void main(String[] args) {
		int[][] puddles= {{2, 2}};
		System.out.println(solution(100,100,puddles));
	}

	public static int solution(int m, int n, int[][] puddles) {
        map=new int[n][m];
        cache=new int[n][m];
        for(int i=0;i<n;i++)
        	Arrays.fill(cache[i], -1);
        for(int i=0;i<puddles.length;i++)
        	map[puddles[i][1]-1][puddles[i][0]-1]=1;
        Ylen=n;
        Xlen=m;

        return getNum(0,0,puddles);
    }

	public static int getNum(int y,int x,int[][] puddles) {
		if(y==Ylen-1&&x==Xlen-1)
			return 1;

		if(cache[y][x]!=-1)
			return cache[y][x]%mod;

		cache[y][x]=0;
		for(int i=0;i<2;i++) {
			int nexty=y+dy[i];
			int nextx=x+dx[i];
			if(nexty<Ylen&&nextx<Xlen&&map[nexty][nextx]!=1) {
				cache[y][x]+=getNum(nexty,nextx,puddles)%mod;
			}
		}

		return cache[y][x]%mod;
	}
}
