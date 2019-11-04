/* https://www.acmicpc.net/problem/1937 */

import java.util.*;

public class Main {
	static int n;
	static int[][] forest;
	static int[][] max;
	static int[][] cache;
	static int[] dy= {-1,1,0,0};
	static int[] dx= {0,0,-1,1};

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		forest=new int[n][n];
		max=new int[n][n];
		cache=new int[n][n];
		for(int i=0;i<n;i++)
			Arrays.fill(cache[i], -1);

		//숲 입력
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				forest[i][j]=sc.nextInt();
			}
		}

		//숲의 각 위치에서의 최대 생존일수 구하기
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				max[i][j]=Max(i,j);
			}
		}

		//숲 전체에서의 최대 생존일수
		int ans=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				ans=Math.max(ans, max[i][j]);
			}
		}

		System.out.println(ans);
	}

	public static int Max(int y,int x) {
		if(cache[y][x]!=-1)
			return cache[y][x];

		int flag=0;
		cache[y][x]=0;
		for(int i=0;i<4;i++) {
			int Y=y+dy[i];
			int X=x+dx[i];
			if(0<=Y&&Y<n&&0<=X&&X<n&&(forest[y][x]<forest[Y][X])) {
				flag=1;
				cache[y][x]=Math.max(cache[y][x],Max(Y,X)+1);
			}
		}

		if(flag==0)
			return cache[y][x]=1;

		return cache[y][x];
	}
}
