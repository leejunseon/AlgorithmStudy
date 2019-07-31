/* https://www.acmicpc.net/problem/2098 */

import java.util.*;

public class Main {
	static int n;
	static int[][] dist;
	static int[][] cache;
	static int max=987654321;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		dist=new int[n][n];
		cache=new int[n][1<<n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				dist[i][j]=sc.nextInt();
			}
			Arrays.fill(cache[i], -1);
		}
		System.out.println(S(0,1<<0));
	}
	
	public static int S(int here,int visited) {
		if(visited==(1<<n)-1) {
			if(dist[here][0]!=0)
				return dist[here][0];
			else
				return max;
		}
		
		if(cache[here][visited]!=-1)
			return cache[here][visited];
		
		cache[here][visited]=max;
		for(int next=0;next<n;next++) {
			if((visited&(1<<next))==0&&dist[here][next]!=0)
				cache[here][visited]=Math.min(cache[here][visited], dist[here][next]+S(next,visited+(1<<next)));
		}
		
		return cache[here][visited];
	}
}