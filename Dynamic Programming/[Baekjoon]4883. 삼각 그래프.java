/* https://www.acmicpc.net/problem/4883 */

import java.util.*;

public class Main {
	static int n;
	static int[][] cost;
	static int[][] cache;
	static int[] dy= {0,1,1,1};
	static int[] dx= {1,-1,0,1};
	static int max=987654321;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=1;
		while(true) {
			n=sc.nextInt();
			if(n==0)
				break;
			cost=new int[n][3];
			cache=new int[n][3];
			for(int i=0;i<n;i++) {
				Arrays.fill(cache[i], -1);
				for(int j=0;j<3;j++) {
					cost[i][j]=sc.nextInt();
				}
			}
			System.out.println(num+++". "+MinCost(0,1));
		}
	}
	
	public static int MinCost(int y,int x) {
		if(y==n-1&&x==1)
			return cost[y][x];
		if(y==n-1&&x==2)
			return max;
		
		if(cache[y][x]!=-1)
			return cache[y][x];
		
		cache[y][x]=max;
		for(int i=0;i<4;i++) {
			int nexty=y+dy[i];
			int nextx=x+dx[i];
			if(0<=nexty&&nexty<n&&0<=nextx&&nextx<3) {
				cache[y][x]=Math.min(cache[y][x], MinCost(nexty,nextx)+cost[y][x]);
			}
		}
		
		return cache[y][x];
	}
}