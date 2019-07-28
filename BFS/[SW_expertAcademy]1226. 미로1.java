/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14vXUqAGMCFAYD& */

import java.io.*;
import java.util.*;

public class Solution {
	public static int[][] map;
	public static Pair start;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test=1;test<=10;test++) {
			
			int n=Integer.parseInt(br.readLine());
			map=new int[16][16];
			for(int i=0;i<16;i++) {
				String input=br.readLine();
				for(int j=0;j<16;j++) {
					map[i][j]=input.charAt(j)-'0';
					if(map[i][j]==2) {
						start=new Pair(i,j);
					}
				}
			}
			
			System.out.println("#"+n+" "+reach(start));
		}
	}
	
	public static int reach(Pair Start) {
		int result=0;
		
		Queue<Pair> q=new LinkedList<Pair>();
		q.add(Start);
		while(!q.isEmpty()) {
			Pair p=q.remove();
			for(int i=0;i<4;i++) {
				int nexty=p.y+dy[i];
				int nextx=p.x+dx[i];
				if(map[nexty][nextx]==3) {
					return 1;
				}
				if(map[nexty][nextx]==0) {
					q.add(new Pair(nexty,nextx));
					map[nexty][nextx]=2;
				}
			}
		}
		
		return result;
	}
}

class Pair{
	int y;
	int x;
	Pair(int y,int x){
		this.y=y;
		this.x=x;
	}
}