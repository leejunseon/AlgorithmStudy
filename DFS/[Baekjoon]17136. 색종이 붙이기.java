/* https://www.acmicpc.net/problem/17136 */
//dfs 탐색 시 지역변수 deepcopy보다 전역변수를 이용하기.
//이 문제에서 deepcopy이용한 dfs시 시간초과.

import java.util.*;
import java.io.*;

public class Main {
	public static int[] use;
	public static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map=new int[10][10];
		int y=-1;
		int remain=0;
		for(int i=0;i<10;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<10;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					if(y==-1) {
						y=i;
					}
					remain++;
				}
			}
		}

		use=new int[6];
		Arrays.fill(use, 5);

		if(y==-1)
			System.out.println(0);
		else {
			int result=getMin(y,0,remain);
			if(result==987654321)
				System.out.println(-1);
			else
				System.out.println(result);
		}
	}

	//y,x를 왼쪽위 모서리로 해서 색종이를 붙임
	public static int getMin(int y,int cnt,int remain) {

		if(remain==0) {
			return cnt;
		}

		int result=987654321;

		for(int i=y;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(map[i][j]==1) {
					boolean flag=true;
					for(int size=1;size<=5;size++) {
						if(use[size]==0)
							continue;
						if(!check(i,j,size)) {
							flag=false;
							break;
						}
						use[size]--;
						attach(i,j,size,0);
						result=Math.min(result, getMin(i,cnt+1,remain-(size*size)));
						use[size]++;
						attach(i,j,size,1);
					}
					if(!flag)
						return result;
				}
			}
		}

		return result;
	}

	//색종이 붙일 수 있는지 확인
	public static boolean check(int y,int x,int size) {
		boolean result=true;
		for(int i=y;i<y+size;i++) {
			for(int j=x;j<x+size;j++) {
				if(i>=10||j>=10||map[i][j]==0)
					return false;
			}
		}
		return result;
	}

	//색종이 붙이거나 떼기
	public static void attach(int y,int x,int size,int attach) {
		for(int i=y;i<y+size;i++) {
			for(int j=x;j<x+size;j++) {
				map[i][j]=attach;
			}
		}
	}
}
