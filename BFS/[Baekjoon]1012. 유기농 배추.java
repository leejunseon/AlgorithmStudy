/* https://www.acmicpc.net/problem/1012 */

import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[][] ground;
	static int[][] check;
	static int[] dx= {1,-1,0,0};
	static int[] dy= {0,0,1,-1};
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		while(num-->0) {
			m=sc.nextInt();
			n=sc.nextInt();
			int k=sc.nextInt();
			ground=new int[n][m];
			check=new int[n][m];
			for(int i=0;i<k;i++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				ground[y][x]=1;
			}
			int ans=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(check[i][j]==0&&ground[i][j]==1) {
						dfs(new Pair(i,j));
						ans++;
					}
				}
			}
			System.out.println(ans);
		}
	}
	
	public static void dfs(Pair p) {
		int y=p.y;
		int x=p.x;
		if(check[y][x]!=0)
			return;
		check[y][x]=1;
		for(int i=0;i<4;i++) {
			int nextX=x+dx[i];
			int nextY=y+dy[i];
			if(0<=nextY&&nextY<n&&0<=nextX&&nextX<m) {
				if(check[nextY][nextX]==0&&ground[nextY][nextX]==1) {
					dfs(new Pair(nextY,nextX));
				}
			}
		}
	}
}

class Pair{
	int y;
	int x;
	Pair(int y,int x){
		this.y=y;
		this.x=x;
	}
}