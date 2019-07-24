/* https://www.acmicpc.net/problem/14502 */

import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int m;
	public static List<Pair> virus;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());//세로
		m=Integer.parseInt(st.nextToken());//가로
		virus=new ArrayList<Pair>();
		int[][] map=new int[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				int input=Integer.parseInt(st.nextToken());
				map[i][j]=input;
				if(input==2)
					virus.add(new Pair(i,j));
			}
		}
		
		System.out.println(maxSpace(map,3,0));
	}
	
	public static int maxSpace(int[][] map,int remain,int start) {
		int[][] mapCopy=deepCopy(map);
		
		int result=0;
		
		if(remain==0)//벽을 모두 세웠다면
			return space(mapCopy);//안전영역 계산 후 리턴
		
		//세울 벽이 남아있다면
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(mapCopy[i][j]==0&&(i*n+j+1>start)) {
					mapCopy[i][j]=1;
					result=Math.max(result, maxSpace(mapCopy,remain-1,i*n+j+1));
					mapCopy[i][j]=0;
				}	
			}
		}
				
		return result;
	}
	
	public static int space(int[][] map) {
		Queue<Pair> q=new LinkedList<Pair>();
		for(int i=0;i<virus.size();i++) {
			q.add(virus.get(i));
		}
		while(!q.isEmpty()) {
			Pair p=q.remove();
			for(int i=0;i<4;i++) {
				int nexty=p.y+dy[i];
				int nextx=p.x+dx[i];
				if(0<=nexty&&nexty<n&&0<=nextx&&nextx<m&&map[nexty][nextx]==0) {
					q.add(new Pair(nexty,nextx));
					map[nexty][nextx]=2;
				}
			}
		}
		return count(map);
	}
	
	public static int count(int[][] map) {
		int result=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==0)
					result++;
			}
		}
		return result;
	}
	
	public static int[][] deepCopy(int[][] original){
		int[][] copy=new int[original.length][original[0].length];
		for(int i=0;i<original.length;i++) {
			copy[i]=original[i].clone();
		}
		
		return copy;
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