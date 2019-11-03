/* https://www.acmicpc.net/problem/10835 */

import java.util.*;

public class Main {
	static int n;
	static int[] left;
	static int[] right;
	static int[][] cache;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		left=new int[n];
		right=new int[n];
		cache=new int[n+1][n+1];
		for(int i=0;i<=n;i++)
			Arrays.fill(cache[i], -1);
		for(int i=n-1;i>=0;i--)
			left[i]=sc.nextInt();
		for(int i=n-1;i>=0;i--)
			right[i]=sc.nextInt();
		System.out.println(maxScore(n,n));
	}

	public static int maxScore(int L,int R) {
		if(L==0||R==0)
			return 0;

		if(cache[L][R]!=-1)
			return cache[L][R];

		cache[L][R]=0;
		if(left[L-1]>right[R-1])
			cache[L][R]=Math.max(cache[L][R],maxScore(L,R-1)+right[R-1]);
		cache[L][R]=Math.max(cache[L][R], maxScore(L-1,R));
		cache[L][R]=Math.max(cache[L][R], maxScore(L-1,R-1));

		return cache[L][R];

	}
}
