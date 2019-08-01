/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14BgD6AEECFAYh */

import java.io.*;
import java.util.*;

public class Solution {
	public static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int test=1;test<=10;test++) {
			
			int n=Integer.parseInt(br.readLine());
			map=new int[100][100];
			int y=0;
			int x=0;
			for(int i=0;i<100;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<100;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.println("#"+n+" "+min());
			
		}
	}
	
	public static int min() {
		int result=0;
		
		int min=987654321;
		for(int i=0;i<100;i++) {
			if(map[0][i]==1) {
				int[][] copy=copy(map);
				int dist=count(0,i,0,min,copy);
				if(dist<=min) {
					min=dist;
					result=i;
				}
			}
		}
		
		return result;
	}
	
	public static int count(int y,int x,int count,int min,int[][] Map) {
		int result=0;
		Map[y][x]=2;
		
		if(count>min)
			return 987654321;
		
		if(y==99)
			return count;
		
		if(x-1>=0&&Map[y][x-1]==1&&Map[y][x-1]!=2)
			result=count(y,x-1,count+1,min,Map);
		else if(x+1<100&&map[y][x+1]==1&&Map[y][x+1]!=2)
			result=count(y,x+1,count+1,min,Map);
		else
			result=count(y+1,x,count+1,min,Map);
		
		return result;
	}
	
	public static int[][] copy(int[][] original){
		int[][] copy=new int[original.length][original[0].length];
		for(int i=0;i<original.length;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, original[0].length);
		}
		return copy;
	}
}