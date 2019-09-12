/* https://www.acmicpc.net/problem/11060 */

import java.util.*;

public class Main {
	static int n;
	static int[] a;
	static int[][] cache;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		a=new int[n+1];
		cache=new int[n][n+1];
		for(int i=0;i<n;i++)
			Arrays.fill(cache[i], -1);
		for(int i=1;i<=n;i++)
			a[i]=sc.nextInt();
		int ans=jump(0,1);
		if(ans==1001)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	public static int jump(int move,int start) {
		if(start==n)
			return move;
		if(a[start]==0)
			return 1001;

		if(cache[move][start]!=-1)
			return cache[move][start];

		cache[move][start]=987654321;
		for(int i=1;i<=a[start];i++) {
			if(start+i<=n)
				cache[move][start]=Math.min(cache[move][start], jump(move+1,start+i));
		}
		return cache[move][start];
	}
}
