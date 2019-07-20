/* https://swexpertacademy.com/main/code/problem/problemDetail.do */
시간초과. 탐색과정에서 dp 적용할 수 있는지 검토할 것.

import java.util.Scanner;

public class Solution {
	public static int n;
	public static int[][] map;
	public static int[][] check;
	public static int[] up;
	public static int[] down;
	public static int[] left;
	public static int[] right;
	public static int[] curved;
	public static int[] straighten;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		
		up=new int[3];
		down=new int[3];
		left=new int[3];
		right=new int[3];
		curved=new int[4];
		straighten=new int[2];
		up[0]=2;
		up[1]=3;
		up[2]=4;
		down[0]=2;
		down[1]=5;
		down[2]=6;
		left[0]=1;
		left[1]=3;
		left[2]=6;
		right[0]=1;
		right[1]=4;
		right[2]=5;
		for(int i=1;i<=2;i++)
			straighten[i-1]=i;
		for(int i=3;i<=6;i++)
			curved[i-3]=i;
		
		
		int t=sc.nextInt();
		for(int test=1;test<=t;test++) {
			
			n=sc.nextInt();
			map=new int[n][n];
			check=new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j]=sc.nextInt();
				}
			}
			
			//0:상
			//1:하
			//2:좌
			//3:우
			System.out.println("#"+test+" "+Optimal(2,new Pair(0,0),1,map,check));
			
		}
	}
	
	//최적길이를 반환
	public static int Optimal(int in,Pair start,int num,int[][] map,int[][] check) {
		int result=987654321;
		int[][] clone=new int[n][n];
		int[][] chclone=new int[n][n];
		for(int i=0;i<n;i++) {
			clone[i]=map[i].clone();
			chclone[i]=check[i].clone();
		}
		chclone[start.y][start.x]=1;
		
		//기저사항
		if(start.y==n-1&&start.x==n-1) {
			if(in==0) {
				if(Contains(curved,map[start.y][start.x])) {
					return num;
				}
			}
			if(in==2) {
				if(Contains(straighten,map[start.y][start.x])) {
					return num;
				}
			}
		}
			
		for(int i=0;i<4;i++) {
			if(i==in)
				continue;
			int nexty=start.y+dy[i];
			int nextx=start.x+dx[i];
			if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n&&clone[nexty][nextx]!=0&&chclone[nexty][nextx]==0) {
				switch(i) {
				case 0://위에 
					switch(in) {
					case 1://입구아래쪽
						if(Contains(straighten,clone[start.y][start.x])) {
							clone[start.y][start.x]=2;
							result=Math.min(result, Optimal(1,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 2://입구왼쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=5;
							result=Math.min(result, Optimal(1,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 3://입구오른쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=6;
							result=Math.min(result, Optimal(1,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					}
					break;
				case 1://아래에
					switch(in) {
					case 0://입구위쪽
						if(Contains(straighten,clone[start.y][start.x])) {
							clone[start.y][start.x]=2;
							result=Math.min(result, Optimal(0,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 2://입구왼쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=4;
							result=Math.min(result, Optimal(0,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 3://입구오른쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=3;
							result=Math.min(result, Optimal(0,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					}
					break;
				case 2://왼쪽에
					switch(in) {
					case 0://입구위쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=5;
							result=Math.min(result, Optimal(3,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 1://입구아래쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=4;
							result=Math.min(result, Optimal(3,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 3://입구오른쪽
						if(Contains(straighten,clone[start.y][start.x])) {
							clone[start.y][start.x]=1;
							result=Math.min(result, Optimal(3,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					}
					break;
				case 3://오른쪽에
					switch(in) {
					case 0://입구위쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=6;
							result=Math.min(result, Optimal(2,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 1://입구아래쪽
						if(Contains(curved,clone[start.y][start.x])) {
							clone[start.y][start.x]=3;
							result=Math.min(result, Optimal(2,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 2://입구왼쪽
						if(Contains(straighten,clone[start.y][start.x])) {
							clone[start.y][start.x]=1;
							result=Math.min(result, Optimal(2,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					}
					break;
				}
			}
		}
		
		return result;
	}
	
	public static boolean Contains(int[] contain,int number) {
		boolean result=false;
		
		for(int i=0;i<contain.length;i++) {
			if(contain[i]==number) {
				result=true;
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