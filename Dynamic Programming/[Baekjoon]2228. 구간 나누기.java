/* https://www.acmicpc.net/problem/2228 */

import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[] arr;
	static int[] sum;
	static int[][] cache;
	static int min=-987654321;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		arr=new int[n];
		sum=new int[n+1];
		cache=new int[n+1][m+1];
		for(int i=0;i<=n;i++)
			Arrays.fill(cache[i], -1);
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		sum[1]=arr[0];
		for(int i=1;i<n;i++)
			sum[i+1]=sum[i]+arr[i];
		System.out.println(maxValue(n,m));
	}
	
	public static int maxValue(int N,int M) {
		//기저사례 정하는것이 어려움.
		if(M==0)
			return 0;
		if(N<0)
			return min;
		
		if(cache[N][M]!=-1)
			return cache[N][M];
		
		cache[N][M]=0;
		cache[N][M]=maxValue(N-1,M);//N번째가 M구간에 안속할 때
		for(int i=N;i>0;i--) {//N번째가 M구간에 속할 때 M구간의 시작점을 찾는 과정
			cache[N][M]=Math.max(cache[N][M], maxValue(i-2,M-1)+sum[N]-sum[i-1]);
		}
		
		return cache[N][M];
	}
}