/* https://www.acmicpc.net/problem/17144 */

import java.util.*;
import java.io.*;

public class Main {
	public static int r;
	public static int c;
	public static int t;
	public static int[][] map;
	public static List<Pair> dust;
	public static Pair up;
	public static Pair down;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		dust=new ArrayList<Pair>();
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				int input=Integer.parseInt(st.nextToken());
				if(input>0)
					dust.add(new Pair(i,j,input));
				if(input==-1) {
					if(up==null)
						up=new Pair(i,j,0);
					else
						down=new Pair(i,j,0);
				}
			}
		}
		
		System.out.println(Simulate());
	}
	
	public static int Simulate() {
		int result=0;

		for(int i=1;i<=t;i++) {
			map=new int[r][c];
			map[up.y][up.x]=-1;
			map[down.y][down.x]=-1;
			diffusion();
			wind();
		}
		
		for(int i=0;i<dust.size();i++) {
			result+=dust.get(i).size;
		}
		
		return result;
	}
	
	public static void diffusion() {
		for(int i=0;i<dust.size();i++) {
			Pair p=dust.get(i);
			int share=p.size/5;
			for(int j=0;j<4;j++) {
				int nexty=p.y+dy[j];
				int nextx=p.x+dx[j];
				if(0<=nexty&&nexty<r&&0<=nextx&&nextx<c&&map[nexty][nextx]!=-1) {
					map[nexty][nextx]+=share;
					p.size-=share;
				}
			}
			map[p.y][p.x]+=p.size;
		}
		
		dust.clear();
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(map[i][j]!=-1)
					dust.add(new Pair(i,j,map[i][j]));
			}
		}
	}
	
	public static void wind() {
		
		for(int i=0;i<dust.size();i++) {
			Pair p=dust.get(i);
			
			if(p.y==0) {
				if(p.x==0)
					p.y++;
				else
					p.x--;
			}else if(p.y==up.y) {
				if(p.x==c-1)
					p.y--;
				else
					p.x++;
			}else if(p.y==down.y) {
				if(p.x==c-1)
					p.y++;
				else
					p.x++;
			}else if(p.y==r-1) {
				if(p.x==0)
					p.y--;
				else
					p.x--;
			}else {
				if(0<p.y&&p.y<up.y) {
					if(p.x==0) {
						if(p.y+1!=up.y)
							p.y++;
						else {
							dust.remove(i);
							i--;
							continue;
						}
					}else if(p.x==c-1) {
						p.y--;
					}
				}else if(down.y<p.y&&p.y<r) {
					if(p.x==0) {
						if(p.y-1!=down.y)
							p.y--;
						else {
							dust.remove(i);
							i--;
							continue;
						}
					}else if(p.x==c-1) {
						p.y++;
					}
				}
			}
			
		}
		
	}
}

class Pair{
	int y;
	int x;
	int size;
	Pair(int y,int x,int size) {
		this.y=y;
		this.x=x;
		this.size=size;
	}
}