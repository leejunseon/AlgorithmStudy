/* https://www.acmicpc.net/problem/10942 */

import java.util.*;

public class Main {
	static int[] num;
	static int[][] cache;

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		num=new int[n+1];
		for(int i=1;i<=n;i++)
			num[i]=sc.nextInt();
		int m=sc.nextInt();
		cache=new int[n+1][n+1];
		for(int j=0;j<=n;j++)
			Arrays.fill(cache[j], -1);

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= m; i++) {
			int s=sc.nextInt();
			int e=sc.nextInt();
		    if(pelin(s, e) == 1){
		        sb.append("1\n");
		    }
		    else {
		        sb.append("0\n");
		    }
		}

		System.out.println(sb.toString());
	}

	public static int pelin(int s,int e) {
		if(s+1==e) {
			if(num[s]==num[e])
				return 1;
			else
				return 0;
		}

		if(s==e)
			return 1;

		if(cache[s][e]!=-1)
			return cache[s][e];

		if(num[s]!=num[e])
			cache[s][e]= 0;
		else
			cache[s][e]=pelin(s+1,e-1);

		return cache[s][e];
	}
}
