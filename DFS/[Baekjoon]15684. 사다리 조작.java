/* https://www.acmicpc.net/problem/15684 */
//go 메소드를 재귀함수에서 반복문으로 변경했더니 시간초과가 해결됨. 반복문으로 쓸 수 있는건 되도록 반복문으로 쓸 것.

import java.util.*;
import java.io.*;

public class Main {
	public static int n;//세로선의 갯수
	public static int m;//가로선의 갯수
	public static int h;//세로선마다 가로선을 놓을 수 있는 위치 갯수
	public static final int max=987654321;
	public static int min;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		h=Integer.parseInt(st.nextToken());
		int[][] line=new int[h+1][n+1];
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			line[a][b]=1;
		}
		
		min=4;
		int result=min(line,0,1,0);
		if(result==max) {
			System.out.println(-1);
		}else
			System.out.println(result);
	}
	
	public static int min(int[][] line,int num,int y,int x) {
		
		if(num>min) {
			return max;
		}
		
		if(match(line)) {//match상태이면 반환
			min=num;
			return num;
		}
		
		if(num==3) {//match가 안됐는데 3개를 사용한 경우는 제외
			return max;
		}
		
		int result=max;
		int[][] copy=deepCopy(line);
		for(int i=y;i<=h;i++) {
			for(int j=1;j<n;j++) {
				if(i==y&&j<=x)
					continue;
				if(copy[i][j]==0) {
					if(j<n-1&&copy[i][j+1]!=0)
						continue;
					if(j>=2&&copy[i][j-1]!=0)
						continue;
					copy[i][j]=1;
					result=Math.min(result,min(copy,num+1,i,j));
					if(result==1)
						return result;
					copy[i][j]=0;
				}
			}
		}
		
		return result;
	}
	
	public static boolean match(int[][] line) {//match되는지 검사
		boolean result=true;
		for(int i=1;i<=n;i++) {
			if(!go(line,i)) {
				result=false;
				break;
			}
		}
		return result;
	}
	
	public static boolean go(int[][] line,int x) {//한줄 검사
		int l=x;
		for(int i=1;i<=h;i++) {
			if(l==1) {
				if(line[i][l]==1) {
					l++;
				}
			}else if(l==n) {
				if(line[i][l-1]==1) {
					l--;
				}
			}else {
				if(line[i][l-1]==1) {
					l--;
				}else if(line[i][l]==1) {
					l++;
				}
			}
		}
		
		if(l==x)
			return true;
		else
			return false;
	}
	
	public static int[][] deepCopy(int[][] original){
		int[][] copy=new int[original.length][original[0].length];
		for(int i=0;i<original.length;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, original[0].length);
		}
		return copy;
	}
}
