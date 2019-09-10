/* https://www.acmicpc.net/problem/1495 */

import java.util.*;

public class Main {
	static int n;
	static int s;
	static int m;
	static int[] v;
	static int[][] cache;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		s=sc.nextInt();
		m=sc.nextInt();
		v=new int[n+1];
		cache=new int[m+1][n+1];
		for(int i=0;i<=m;i++)
			Arrays.fill(cache[i], -1);
		for(int i=1;i<=n;i++)
			v[i]=sc.nextInt();
		System.out.println(Vol(s,1));
	}

	public static int Vol(int now,int start) {
		if(start>n)
			return now;

		if(cache[now][start]!=-1)
			return cache[now][start];

		cache[now][start]=-987654321;
		if(now-v[start]>=0)
			cache[now][start]=Math.max(cache[now][start], Vol(now-v[start],start+1));
		if(now+v[start]<=m)
			cache[now][start]=Math.max(cache[now][start], Vol(now+v[start],start+1));
		if(now-v[start]<0&&now+v[start]>m)
			return -1;

		return cache[now][start];
	}
}
