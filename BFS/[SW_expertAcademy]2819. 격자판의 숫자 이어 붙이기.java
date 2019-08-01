/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7I5fgqEogDFAXB&categoryId=AV7I5fgqEogDFAXB&categoryType=CODE */

import java.io.*;
import java.util.*;

public class Solution {
	public static String[][] map;
	public static HashSet<String> list;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++) {
			map=new String[4][4];
			list=new HashSet<String>();
			for(int i=0;i<4;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<4;j++) {
					map[i][j]=st.nextToken();
				}
			}
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					add(i,j);
				}
			}
			
			System.out.println("#"+test+" "+list.size());
		}
	}
	
	public static void add(int y,int x) {
		Queue<Pair> q=new LinkedList<Pair>();
		q.add(new Pair(y,x,map[y][x]));
		while(!q.isEmpty()) {
			Pair p=q.remove();
			if(p.s.length()==7) {
				list.add(p.s);
			}else {
				for(int i=0;i<4;i++) {
					int nexty=p.y+dy[i];
					int nextx=p.x+dx[i];
					String s=p.s;					
					if(0<=nexty&&nexty<4&&0<=nextx&&nextx<4) {
						q.add(new Pair(nexty,nextx,s+map[nexty][nextx]));
					}
				}
			}
		}
	}
}

class Pair{
	int y;
	int x;
	String s;
	Pair(int y,int x,String s){
		this.y=y;
		this.x=x;
		this.s=s;
	}
}