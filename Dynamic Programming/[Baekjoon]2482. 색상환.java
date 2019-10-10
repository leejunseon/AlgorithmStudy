/* https://www.acmicpc.net/problem/2482 */
//circle(n,k) 에서 한 색깔을 골라
//그 색깔을 칠하는 경우 = n-3길이의 색 띠에서 k-1개의 색을 고르는 경우의 수
//그 색깔을 칠하지 않는 경우 = n-1길이의 색 띠에서 k개의 색을 고르는 경우의 수
//결과적으로,
//circle(n,k) = stripe(n-3,k-1) + stripe(n-1,k)
//stripe(n,k) = stripe(n-2,k-1) + stripe(n-1,k)
//기저사례
// - k=0 이면 return 1
// - n=1, k=1이면 return 1
// - n==k이면 return 0
// - k==1 이면 return n
//기저사례 정하는게 어려웠음.

import java.io.*;
import java.util.*;

public class Main {
	public static final int mod=1000000003;
	public static int[][] cache;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int k=Integer.parseInt(br.readLine());
		cache=new int[n+1][k+1];

		for(int i=0;i<=n;i++) {
			Arrays.fill(cache[i], -1);
		}

		System.out.println((stripe(n-3,k-1)+stripe(n-1,k))%mod);
	}

	public static int stripe(int n,int k) {
		if(k==0)
			return 1;
		if(n==1&&k==1)
			return 1;
		if(n==k)
			return 0;
		if(k==1)
			return n;

		if(cache[n][k]!=-1)
			return cache[n][k];

		cache[n][k]=(stripe(n-2,k-1)%mod+stripe(n-1,k)%mod)%mod;
		return cache[n][k];
	}
}
