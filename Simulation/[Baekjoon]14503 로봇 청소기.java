/* https://www.acmicpc.net/problem/14503 */

import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int m;
	public static int[][] map;
	public static int[] dy= {0,0,1,-1};
	public static int[] dx= {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());//세로
		m=Integer.parseInt(st.nextToken());//가로
		map=new int[n][m];
		
		st=new StringTokenizer(br.readLine());
		int y=Integer.parseInt(st.nextToken());
		int x=Integer.parseInt(st.nextToken());
		int direction=Integer.parseInt(st.nextToken());
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(count(y,x,direction,0));
	}
	
	//direction
	//0:북
	//1:동
	//2:남
	//3:서
	public static int count(int currenty,int currentx,int direction,int num) {
		int result=0;
		
		if(map[currenty][currentx]==0)
			map[currenty][currentx]=2;
		
		boolean zero=false;
		for(int i=0;i<4;i++) {
			if(map[currenty+dy[i]][currentx+dx[i]]==0) {
				zero=true;//갈 공간이 있음
				break;
			}
		}
		
		int nexty=0;
		int nextx=0;
		if(zero) {
			switch(direction) {
			case 0://북
				nexty=currenty;
				nextx=currentx-1;
				if(map[nexty][nextx]==0) {//왼쪽에 자리가 있을 경우
					result=count(nexty,nextx,3,num+1);//서쪽으로 한칸 이동
				}else {
					result=count(currenty,currentx,3,num);//방향만 서쪽으로
				}
				break;
			case 1://동
				nexty=currenty-1;
				nextx=currentx;
				if(map[nexty][nextx]==0) {//왼쪽에 자리가 있을 경우
					result=count(nexty,nextx,0,num+1);//북쪽으로 한칸 이동
				}else {
					result=count(currenty,currentx,0,num);//방향만 북쪽으로
				}
				break;
			case 2://남
				nexty=currenty;
				nextx=currentx+1;
				if(map[nexty][nextx]==0) {//왼쪽에 자리가 있을 경우
					result=count(nexty,nextx,1,num+1);//동쪽으로 한칸 이동
				}else {
					result=count(currenty,currentx,1,num);//방향만 동쪽으로
				}
				break;
			case 3://서
				nexty=currenty+1;
				nextx=currentx;
				if(map[nexty][nextx]==0) {//왼쪽에 자리가 있을 경우
					result=count(nexty,nextx,2,num+1);//남쪽으로 한칸 이동
				}else {
					result=count(currenty,currentx,2,num);//방향만 남쪽으로
				}
				break;
			}
		}else {//갈 공간이 없음
			switch(direction) {
			case 0://북
				nexty=currenty+1;
				nextx=currentx;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//후진
				else
					return num+1;
				break;
			case 1://동
				nexty=currenty;
				nextx=currentx-1;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//후진
				else
					return num+1;
				break;
			case 2://남
				nexty=currenty-1;
				nextx=currentx;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//후진
				else
					return num+1;
				break;
			case 3://서
				nexty=currenty;
				nextx=currentx+1;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//후진
				else
					return num+1;
				break;
			}
		}
		
		return result;
	}
}