/* https://swexpertacademy.com/main/code/problem/problemDetail.do */

import java.io.*;
import java.util.*;

public class Solution {
	public static int n;//세로
	public static int m;//가로
	public static char[][] map;
	public static int memory;
	public static int[][][][] check;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t=Integer.parseInt(br.readLine());
		
		
		for(int test=1;test<=t;test++) {
			boolean flag=false;
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			map=new char[n][m];
			check=new int[n][m][16][5];
			for(int i=0;i<n;i++) {
				String input=br.readLine();
				for(int j=0;j<m;j++) {
					map[i][j]=input.charAt(j);
					if(input.charAt(j)=='@')
						flag=true;
				}
			}
			
			memory=0;
			if(flag==false) {
				System.out.println("#"+test+" "+"NO");
			}else {
				System.out.println("#"+test+" "+result(0,0,4));
			}
		}
	}
	
	public static String result(int y,int x,int direction) {
		String result="";
			
		if(check[y][x][memory][direction]!=0)
			return "NO";
		else 
			check[y][x][memory][direction]=1;
				
		if(48<=(int)map[y][x]&&(int)map[y][x]<=57) {//숫자일 경우
			memory=(int)map[y][x]-48;
			switch(direction) {
			case 1://상
				if(y-1<0)
					result=result(n-1,x,direction);
				else
					result=result(y-1,x,direction);
				break;
			case 2://하
				if(y+1>=n)
					result=result(0,x,direction);
				else
					result=result(y+1,x,direction);
				break;
			case 3://좌
				if(x-1<0)
					result=result(y,m-1,direction);
				else
					result=result(y,x-1,direction);
				break;
			case 4://우
				if(x+1>=m)
					result=result(y,0,direction);
				else
					result=result(y,x+1,direction);
				break;
			}
		}else {//숫자 아닐 경우
			switch(map[y][x]) {
			case '<':
				if(x-1<0)
					result=result(y,m-1,3);
				else
					result=result(y,x-1,3);
				break;
			case '>':
				if(x+1>=m)
					result=result(y,0,4);
				else
					result=result(y,x+1,4);
				break;
			case '^':
				if(y-1<0)
					result=result(n-1,x,1);
				else
					result=result(y-1,x,1);
				break;
			case 'v':
				if(y+1>=n)
					result=result(0,x,2);
				else
					result=result(y+1,x,2);
				break;
			case '_':
				if(memory==0) {//0이면 오른쪽
					if(x+1>=m)
						result=result(y,0,4);
					else
						result=result(y,x+1,4);
				}else {//아니면 왼쪽
					if(x-1<0)
						result=result(y,m-1,3);
					else
						result=result(y,x-1,3);
				}
				break;
			case '|':
				if(memory==0) {//0이면 아래쪽
					if(y+1>=n)
						result=result(0,x,2);
					else
						result=result(y+1,x,2);
				}else//아니면 위쪽
					if(y-1<0)
						result=result(n-1,x,1);
					else
						result=result(y-1,x,1);
				break;
			case '?':
				//상
				if(y-1<0)
					result=result(n-1,x,1);
				else
					result=result(y-1,x,1);
				if(result.equals("YES"))
					return "YES";
				//하
				if(y+1>=n)
					result=result(0,x,2);
				else
					result=result(y+1,x,2);
				if(result.equals("YES"))
					return "YES";
				//좌
				if(x-1<0)
					result=result(y,m-1,3);
				else
					result=result(y,x-1,3);
				if(result.equals("YES"))
					return "YES";
				//우
				if(x+1>=m)
					result=result(y,0,4);
				else
					result=result(y,x+1,4);
				if(result.equals("YES"))
					return "YES";
				break;
			case '.':
				switch(direction) {
				case 1:
					if(y-1<0)
						result=result(n-1,x,1);
					else
						result=result(y-1,x,1);
					break;
				case 2:
					if(y+1>=n)
						result=result(0,x,2);
					else
						result=result(y+1,x,2);
					break;
				case 3:
					if(x-1<0)
						result=result(y,m-1,3);
					else
						result=result(y,x-1,3);
					break;
				case 4:
					if(x+1>=m)
						result=result(y,0,4);
					else
						result=result(y,x+1,4);
					break;
				}
				break;
			case '@':
				result="YES";
				break;
			case '+':
				if(memory==15)
					memory=0;
				else
					memory++;
				switch(direction) {
				case 1:
					if(y-1<0)
						result=result(n-1,x,1);
					else
						result=result(y-1,x,1);
					break;
				case 2:
					if(y+1>=n)
						result=result(0,x,2);
					else
						result=result(y+1,x,2);
					break;
				case 3:
					if(x-1<0)
						result=result(y,m-1,3);
					else
						result=result(y,x-1,3);
					break;
				case 4:
					if(x+1>=m)
						result=result(y,0,4);
					else
						result=result(y,x+1,4);
					break;
				}
				break;
			case '-':
				if(memory==0)
					memory=15;
				else
					memory--;
				switch(direction) {
				case 1:
					if(y-1<0)
						result=result(n-1,x,1);
					else
						result=result(y-1,x,1);
					break;
				case 2:
					if(y+1>=n)
						result=result(0,x,2);
					else
						result=result(y+1,x,2);
					break;
				case 3:
					if(x-1<0)
						result=result(y,m-1,3);
					else
						result=result(y,x-1,3);
					break;
				case 4:
					if(x+1>=m)
						result=result(y,0,4);
					else
						result=result(y,x+1,4);
					break;
				}
				break;
			}
		}
		
		return result;
	}
}