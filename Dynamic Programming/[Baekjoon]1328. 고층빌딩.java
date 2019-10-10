/* https://www.acmicpc.net/problem/1328 */
//가장 작은 빌딩을 계속해서 추가한다고 가정했을 때
//빌딩이 n, 왼쪽에서 봤을 때 l, 오른쪽에서 봤을 때 r인 경우의 수는
//빌딩이 n-1, 왼쪽에서 봤을 때 l-1, 오른쪽에서 봤을 때 r일 때 가장 작은 빌딩을 왼쪽에 추가했을 경우
//+
//빌딩이 n-1, 왼쪽에서 봤을 때 l, 오른쪽에서 봤을 때 r일 때 가장 작은 빌딩을 기존 빌딩 사이사이에 추가했을 경우
//+
//빌딩이 n-1, 왼쪽에서 봤을 때 l, 오른쪽에서 봤을 때 r-1일 때 가장 작은 빌딩을 오른쪽에 추가했을 경우
//의 합이다.
//(n,l,r) = sum((n-1,l-1,r),(n-1,l,r)*(n-2),(n-1,l,r-1))
//로 세울 수 있고,
//기저사례는
//n,l,r중 하나라도 0이 되면 경우의 수는 0,
//빌딩이 오름차순 혹은 내림차순으로 세워져있을 때 경우의 수는 각각 1
//로 두면 된다.

import java.io.*;
import java.util.*;

public class Main {
	public static final int mod=1000000007;
	public static long[][][] cache;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int l=Integer.parseInt(st.nextToken());
		int r=Integer.parseInt(st.nextToken());

		cache=new long[n+1][l+1][r+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=l;j++) {
				Arrays.fill(cache[i][j], -1);
			}
		}

		System.out.println(getNum(n,l,r));
	}

	public static long getNum(int n,int l,int r) {
		if((l==1&&r==n)||(l==n&&r==1)) {
			return 1;
		}

		if(n==0||l==0||r==0)
			return 0;

		if(cache[n][l][r]!=-1)
			return cache[n][l][r];

		cache[n][l][r]=(getNum(n-1,l-1,r)+getNum(n-1,l,r)*(n-2)+getNum(n-1,l,r-1))%mod;
		return cache[n][l][r];
	}
}
