/* https://www.acmicpc.net/problem/17142 */

import java.util.*;
import java.io.*;

public class Main {
	public static final int max=987654321;
	public static int[][] map;
	public static int n;
	public static int m;
	public static List<virus> viruses;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	public static int c=0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());//맵 크기
		m=Integer.parseInt(st.nextToken());//사용 바이러스 갯수
		map=new int[n][n];
		viruses=new ArrayList<virus>();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int input=Integer.parseInt(st.nextToken());
				if(input==2) {
					viruses.add(new virus(i,j));
				}else if(input==0)
					c++;
				map[i][j]=input;
			}
		}
		
		if(c==0)
			System.out.println(0);
		else {
			int[] v=new int[viruses.size()];
			int result=min(v,m,-1);
			if(result==max)
				System.out.println(-1);
			else
				System.out.println(result);
		}
	}
	
	public static int min(int[] used,int remain,int start) {
		int result=max;
		int[] copy=used.clone();
		
		if(remain==0) {
			return time(used);
		}
		
		for(int i=start+1;i<used.length;i++) {
			copy[i]=1;
			result=Math.min(result, min(copy,remain-1,i));
			copy[i]=0;
		}
		
		return result;
	}
	
	public static int time(int[] used) {
		int result=0;
		int[][] check=new int[n][n];	
		int C=c;
		
		Queue<pair> q=new LinkedList<pair>();
		for(int i=0;i<used.length;i++) {
			if(used[i]==1) {
				virus v=viruses.get(i);
				q.add(new pair(v.y,v.x,0));
				check[v.y][v.x]=-1;
			}
		}
		
		while(!q.isEmpty()) {
			pair p=q.remove();
			if(C==0)
				break;
			for(int i=0;i<4;i++) {
				int nexty=p.y+dy[i];
				int nextx=p.x+dx[i];
				if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n&&(check[nexty][nextx]==0||check[nexty][nextx]>p.count+1)&&map[nexty][nextx]!=1) {
					check[nexty][nextx]=p.count+1;
					result=Math.max(result, p.count+1);
					if(map[nexty][nextx]==0)
						C--;
					q.add(new pair(nexty,nextx,p.count+1));
				}
			}
		}
		
		boolean flag=true;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(check[i][j]==0&&map[i][j]!=1&&map[i][j]!=2) {
					flag=false;
					break;
				}
			}
		}
		
		if(flag)
			return result;
		else
			return max;
	}
}

class virus{
	int y;
	int x;
	virus(int y,int x){
		this.y=y;
		this.x=x;
	}
}

class pair{
	int y;
	int x;
	int count;
	pair(int y,int x,int count){
		this.y=y;
		this.x=x;
		this.count=count;
	}
}