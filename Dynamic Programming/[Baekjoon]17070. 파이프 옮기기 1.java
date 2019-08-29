/* https://www.acmicpc.net/problem/17070 */

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int[][] map;
	public static int[][][] cache;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		cache=new int[n][n][4];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				Arrays.fill(cache[i][j], -1);
			}
		}

		Pair start=new Pair(0,0);
		Pair end=new Pair(0,1);
		Pipe p=new Pipe(start,end);
		System.out.println(dfs(p));
	}

	public static int dfs(Pipe pipe) {
		if(pipe.end.y==n-1&&pipe.end.x==n-1)
			return 1;

		int direction=direction(pipe);

		if(cache[pipe.start.y][pipe.start.x][direction]!=-1)
			return cache[pipe.start.y][pipe.start.x][direction];

		cache[pipe.start.y][pipe.start.x][direction]=0;
		Pair p=pipe.end;//이전 파이프의 끝 부분
		Pair start=p.clone();
		Pair end=null;
		switch(direction) {
		case 1://가로
			//가로 이동
			end=new Pair(start.y,start.x+1);
			if(end.x<n&&map[end.y][end.x]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			//대각 이동
			end=new Pair(start.y+1,start.x+1);
			if(end.x<n&&end.y<n&&map[end.y][end.x]==0&&map[end.y-1][end.x]==0&&map[end.y][end.x-1]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			break;
		case 2://대각
			//가로 이동
			end=new Pair(start.y,start.x+1);
			if(end.x<n&&map[end.y][end.x]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			//대각 이동
			end=new Pair(start.y+1,start.x+1);
			if(end.x<n&&end.y<n&&map[end.y][end.x]==0&&map[end.y-1][end.x]==0&&map[end.y][end.x-1]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			//세로 이동
			end=new Pair(start.y+1,start.x);
			if(end.y<n&&map[end.y][end.x]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			break;
		case 3://세로
			//대각 이동
			end=new Pair(start.y+1,start.x+1);
			if(end.x<n&&end.y<n&&map[end.y][end.x]==0&&map[end.y-1][end.x]==0&&map[end.y][end.x-1]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			//세로 이동
			end=new Pair(start.y+1,start.x);
			if(end.y<n&&map[end.y][end.x]==0)
				cache[pipe.start.y][pipe.start.x][direction]+=dfs(new Pipe(start,end));
			break;
		}

		return cache[pipe.start.y][pipe.start.x][direction];
	}

	public static int direction(Pipe pipe) {
		Pair start=pipe.start;
		Pair end=pipe.end;

		if(start.y==end.y) {
			return 1;
		}else {
			if(start.x==end.x)
				return 3;
			else
				return 2;
		}
	}
}

class Pipe{
	Pair start;
	Pair end;
	Pipe(Pair start,Pair end){
		this.start=start;
		this.end=end;
	}
}

class Pair implements Cloneable{
	int y;
	int x;
	Pair(int y,int x){
		this.y=y;
		this.x=x;
	}

	@Override
	public Pair clone() {
		return new Pair(this.y,this.x);
	}
}
