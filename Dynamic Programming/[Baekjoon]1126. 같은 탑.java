/* https://www.acmicpc.net/problem/1126 */

import java.util.*;

public class Main {
	static int n;
	static int[] block;
	static int sum;
	static int[][] cache;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		block=new int[n];
		sum=0;
		for(int i=0;i<n;i++) {
			block[i]=sc.nextInt();
			sum+=block[i];
		}
		cache=new int[n+1][sum+1];
		for(int i=0;i<=n;i++) {
			Arrays.fill(cache[i], -1);
		}
		int ans=tower(0,0);
		if(ans>0)
			System.out.println(ans);
		else
			System.out.println(-1);
	}
	
	public static int tower(int now,int diff) {
		if(now==n&&diff!=0)
			return Integer.MIN_VALUE;
		if(now==n&&diff==0)
			return 0;
		
		if(cache[now][diff]!=-1)
			return cache[now][diff];
		
		//기준은 더 낮은 탑의 높이로 해야 함.
		cache[now][diff]=Integer.MIN_VALUE;
		cache[now][diff]=Math.max(cache[now][diff],tower(now+1,diff));//블록 안썼을 때
		cache[now][diff]=Math.max(cache[now][diff], tower(now+1,diff+block[now]));//높은 곳에 쌓았을 때
		if(now>0) {
			if(block[now]>diff) //diff보다 큰 블록을 낮은 곳에 쌓을 때
				cache[now][diff]=Math.max(cache[now][diff], tower(now+1,block[now]-diff)+diff);
			else	//diff보다 작거나 같은 블록을 낮은 곳에 쌓을 때
				cache[now][diff]=Math.max(cache[now][diff], tower(now+1,diff-block[now])+block[now]);
		}
		return cache[now][diff];

	}
}