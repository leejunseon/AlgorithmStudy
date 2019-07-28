/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh */

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
					if(map[i][j]==2) {
						y=i;
						x=j;
					}
				}
			}
			
			System.out.println("#"+n+" "+answer(y,x));
			
		}
	}
	
	public static int answer(int y,int x) {
		int result=0;
		map[y][x]=2;
		
		if(y==0)
			return x;
		
		if(x-1>=0&&map[y][x-1]==1&&map[y][x-1]!=2)
			result=answer(y,x-1);
		else if(x+1<100&&map[y][x+1]==1&&map[y][x+1]!=2)
			result=answer(y,x+1);
		else
			result=answer(y-1,x);
		
		return result;
	}
}