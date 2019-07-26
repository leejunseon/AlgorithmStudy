/* https://www.acmicpc.net/problem/1102 */

import java.util.*;

public class Main {
	static int n;
	static int[][] cost;
	static int[] cache;
	static int p;
	static int max=987654321;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		cost=new int[n][n];
		cache=new int[1<<n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				cost[i][j]=sc.nextInt();
			}
		}
		Arrays.fill(cache, -1);
		sc.nextLine();
		String onoff=sc.nextLine();
		int visited=0;
		for(int i=0;i<n;i++) {
			if(onoff.charAt(i)=='Y') {
				visited+=(1<<i);
			}
		}
		p=sc.nextInt();
		if(visited==0&&p>0)
			System.out.println(-1);
		else {
			int ans=minValue(visited);
			if(ans>=max)
				System.out.println(-1);
			else
				System.out.println(ans);
		}
	}
	
	public static int minValue(int visited) {
		if(working(visited)>=p)//적어도 p개 고쳤다면
			return 0;
		
		if(cache[visited]!=-1)
			return cache[visited];
		
		cache[visited]=max;
		for(int start=0;start<n;start++) {
			if((visited&(1<<start))!=0) {//Y에서
				for(int next=0;next<n;next++) {
					if((visited&(1<<next))==0){//N으로
						cache[visited]=Math.min(cache[visited], cost[start][next]+minValue(visited+(1<<next)));
					}
				}
			}
		}
		
		return cache[visited];
	}
	
	public static int working(int visited) {
		int num=0;
		for(int i=0;i<n;i++) {
			if((visited&(1<<i))!=0) {
				num++;
			}
		}
		return num;
	}
}