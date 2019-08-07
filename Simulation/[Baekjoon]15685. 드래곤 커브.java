/* https://www.acmicpc.net/problem/15685 */

import java.util.*;
import java.io.*;

public class Main {
	public static int[][] map;
	public static HashSet<Pair> locations;
	public static List<Integer> directions;
	public static int currenty;
	public static int currentx;
	public static int maxx;
	public static int maxy;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map=new int[101][101];
		locations=new HashSet<Pair>();
		maxx=0;
		maxy=0;
		
		int n=Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());//시작방향
			int g=Integer.parseInt(st.nextToken());//세대
			
			dragon(d,g);
			addLocation(x,y);
		}
		
		for(Pair p:locations) {
			map[p.y][p.x]=1;
		}
		
		int count=0;
		for(int i=0;i<maxy;i++) {
			for(int j=0;j<maxx;j++) {
				if(map[i][j]==1&&map[i][j+1]==1&&map[i+1][j]==1&&map[i+1][j+1]==1)
					count++;
			}
		}
		
		System.out.println(count);
	}
	
	public static void dragon(int start,int generation) {
		directions=new ArrayList<Integer>();
		directions.add(start);
		
		for(int i=1;i<=generation;i++) {
			List<Integer> input=new ArrayList<Integer>();
			input.addAll(directions);
			
			for(int j=0;j<input.size();j++) {
				switch(input.get(j)) {
				case 0:
					input.set(j, 1);
					break;
				case 1:
					input.set(j, 2);
					break;
				case 2:
					input.set(j, 3);
					break;
				case 3:
					input.set(j, 0);
					break;
				}
			}
			
			for(int j=input.size()-1;j>=0;j--) {
				directions.add(input.get(j));
			}
		}
		
		
	}
	
	public static void addLocation(int x,int y) {
		if(0<=x&&x<=100&&0<=y&&y<=100)
			locations.add(new Pair(x,y));
		
		currentx=x;
		currenty=y;
		for(int i=0;i<directions.size();i++) {
			switch(directions.get(i)) {
			case 0://우
				currentx++;
				break;
			case 1://상
				currenty--;
				break;
			case 2://좌
				currentx--;
				break;
			case 3://하
				currenty++;
				break;
			}
			if(0<=currentx&&currentx<=100&&0<=currenty&&currenty<=100)
				locations.add(new Pair(currentx,currenty));
			
			maxx=Math.max(maxx, currentx);
			maxy=Math.max(maxy, currenty);
		}
	}
}

class Pair{
	int x;
	int y;
	Pair(int x,int y){
		this.x=x;
		this.y=y;
	}
}
