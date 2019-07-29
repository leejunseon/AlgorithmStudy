/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD&categoryId=AV15QRX6APsCFAYD&categoryType=CODE */
//우선순위 큐 사용

import java.io.*;
import java.util.*;

public class Solution {
	public static int n;
	public static int[][] map;
	public static int[][] check;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int num=Integer.parseInt(br.readLine());
		
		for(int test=1;test<=num;test++) {
			
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			for(int i=0;i<n;i++) {
				String input=br.readLine();
				for(int j=0;j<n;j++) {
					map[i][j]=input.charAt(j)-'0';
				}
			}
			check=new int[n][n];
			
			System.out.println("#"+test+" "+bfs(0,0,0));
		}
	}
	
	public static int bfs(int y,int x,int cost) {
		int result=0;
		
		PriorityQueue<Pair> pq=new PriorityQueue<Pair>();
		pq.add(new Pair(y,x,cost));
		check[y][x]=1;
		while(!pq.isEmpty()) {
			Pair p=pq.remove();
			if(p.y==n-1&&p.x==n-1) {
				result=p.cost;
				break;
			}
			for(int i=0;i<4;i++) {
				int nexty=p.y+dy[i];
				int nextx=p.x+dx[i];
				if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n&&check[nexty][nextx]==0) {
					pq.add(new Pair(nexty,nextx,p.cost+map[nexty][nextx]));
					check[nexty][nextx]=1;
				}
			}
		}
		
		return result;
	}
}

class Pair implements Comparable<Pair>{
	int y;
	int x;
	int cost;
	Pair(int y,int x,int cost){
		this.y=y;
		this.x=x;
		this.cost=cost;
	}
	
	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return this.cost-o.cost;
	}
}