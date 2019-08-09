/* https://www.acmicpc.net/problem/16234 */

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int l;//이상
	public static int r;//이하
	public static int[][] map;
	public static int[][] check;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		l=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int result=0;
		while(true) {
			int p=process();
			if(p==0)
				break;
			else
				result+=p;
		}
		
		System.out.println(result);
	}
	
	public static int process() {
		boolean flag=false;
		int count=0;
		check=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(check[i][j]==0) {
					count++;
					if(bfs(new Pair(i,j),count)) {
						if(flag==false)
							flag=true;
					}
				}
			}
		}
		
		if(flag)
			return 1;
		else 
			return 0;
	}
	
	public static boolean bfs(Pair start,int count) {//인구이동
		boolean result=false;
		int sum=0;//전체 합
		int num=0;//갯수
		Queue<Pair> q=new LinkedList<Pair>();
		q.add(start);
		check[start.y][start.x]=count;
		while(!q.isEmpty()) {
			Pair p=q.remove();
			sum+=map[p.y][p.x];
			num++;
			for(int i=0;i<4;i++) {
				int nexty=p.y+dy[i];
				int nextx=p.x+dx[i];
				int diff=0;
				if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n) {
					diff=Math.abs(map[p.y][p.x]-map[nexty][nextx]);
					if(l<=diff&&diff<=r&&check[nexty][nextx]==0) {
						if(result==false)
							result=true;
						q.add(new Pair(nexty,nextx));
						check[nexty][nextx]=count;
					}
				}
			}
		}
		
		int population=sum/num;
		if(result) {
			for(int l=0;l<n;l++) {
				for(int k=0;k<n;k++) {
					if(check[l][k]==count)
						map[l][k]=population;
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