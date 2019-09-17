/* https://www.acmicpc.net/problem/10164 */

import java.util.*;

public class Main {
	static int n;
	static int m;
	static int k;
	static int[][] cache;

	static int[] dy= {1,0};
	static int[] dx= {0,1};
	static int Y;//목적지 y좌표
	static int X;//목적지 x좌표

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();

		cache=new int[16][16];
		for(int i=0;i<=15;i++) {
			Arrays.fill(cache[i], -1);
		}

		if(k!=0) {
			Y=(k-1)/m;
			X=k-m*Y-1;
			int way1=way(0,0);
			int a=Y;
			int b=X;
			Y=n-1;
			X=m-1;
			int way2=way(a,b);
			System.out.println(way1*way2);
		}else {
			Y=n-1;
			X=m-1;
			System.out.println(way(0,0));
		}
	}

	public static int way(int y,int x) {
		if(y==Y&&x==X)
			return 1;

		if(cache[y][x]!=-1)
			return cache[y][x];

		cache[y][x]=0;
		for(int i=0;i<2;i++) {
			int dY=y+dy[i];
			int dX=x+dx[i];
			if(0<=dY&&dY<=Y&&0<=dX&&dX<=X)
				cache[y][x]+=way(dY,dX);
		}

		return cache[y][x];
	}
}
