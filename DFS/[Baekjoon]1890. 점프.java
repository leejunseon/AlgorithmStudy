/* https://www.acmicpc.net/problem/1890 */

import java.util.*;

public class Main {	
	static int[] dx= {1,0};
	static int[] dy= {0,1};
	static int N;
	static int[][] a;
	static long[][] d;
	
	public static long dfs(Pair p) {
		int x=p.x;
		int y=p.y;
		if(x==N-1&&y==N-1) {
			//도착점
			return 1;
		}
		if(d[y][x]==0) {
			//안와본 곳
			if(a[y][x]==0)
				return 0;
			for(int i=0;i<2;i++) {
				int nx=x+a[y][x]*dx[i];
				int ny=y+a[y][x]*dy[i];
				if(0<=nx&&nx<N&&0<=ny&&ny<N) {
					d[y][x]+=dfs(new Pair(nx,ny));
				}
			}
		}
		//와본 곳
		return d[y][x];
	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		N=sc.nextInt();
		a=new int[N][N];
		d=new long[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				a[i][j]=sc.nextInt();
			}
		}
		dfs(new Pair(0,0));
		System.out.println(d[0][0]);
	}
}

class Pair{
	int x;
	int y;
	Pair(int x,int y){
		this.x=x;
		this.y=y;
	}
}