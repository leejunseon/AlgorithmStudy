/* https://www.acmicpc.net/problem/16236 */

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int[][] map;
	public static int[][] check;
	public static Shark shark;
	public static int fish;
	//상 -> 좌 -> 우 -> 하
	public static int[] dy= {-1,0,0,1};
	public static int[] dx= {0,-1,1,0};
	public static int c;
	public static PriorityQueue<Pair> pq;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int input=Integer.parseInt(st.nextToken());
				if(1<=input&&input<=6) {
					map[i][j]=input;
					fish++;
				}
				if(input==9)
					shark=new Shark(i,j,2,0);
			}
		}
		
		if(fish==0)
			System.out.println(0);
		else
			System.out.println(Simulate());
	}
	
	public static int Simulate() {
		int result=0;
		Pair p;
		
		while(fish!=0) {
			check=new int[n][n];
			c=0;
			pq=new PriorityQueue<Pair>();
			p=bfs(new Pair(shark.y,shark.x,0));
			if(p==null)
				return result;
			shark.y=p.y;
			shark.x=p.x;
			result+=p.count;
			shark.fishes++;
			if(shark.fishes==shark.size) {
				shark.size++;
				shark.fishes=0;
			}
			map[p.y][p.x]=0;
			fish--;
		}
		
		return result;
	}
	
	public static Pair bfs(Pair start) {
		Queue<Pair> q=new LinkedList<Pair>();
		q.add(start);
		check[start.y][start.x]=1;
		while(!q.isEmpty()) {
			Pair p=q.remove();
			if(c!=p.count) {
				if(!pq.isEmpty()) {
					return pq.peek();
				}
				c=p.count;
			}
			for(int i=0;i<4;i++) {
				int nexty=p.y+dy[i];
				int nextx=p.x+dx[i];
				if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n&&check[nexty][nextx]==0
						&&map[nexty][nextx]<=shark.size) {
					if(0<map[nexty][nextx]&&map[nexty][nextx]<shark.size) {
						pq.add(new Pair(nexty,nextx,p.count+1));
					}
					q.add(new Pair(nexty,nextx,p.count+1));
					check[nexty][nextx]=1;
				}
			}
		}
		return null;
	}
}

class Shark{
	int y;
	int x;
	int size;
	int fishes;
	Shark(int y,int x,int size,int fishes){
		this.y=y;
		this.x=x;
		this.size=size;
		this.fishes=fishes;
	}
}

class Pair implements Comparable<Pair>{
	int y;
	int x;
	int count;
	Pair(int y,int x,int count){
		this.y=y;
		this.x=x;
		this.count=count;
	}
	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		if(this.y!=o.y)
			return this.y-o.y;
		else {
			return this.x-o.x;
		}
	}
}