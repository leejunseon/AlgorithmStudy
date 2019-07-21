/* https://www.acmicpc.net/problem/13460 */

import java.io.*;
import java.util.*;

public class Solution {
	public static int n;
	public static int m;
	public final static int max=987654321;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		char[][] map=new char[n][m];
		
		Ball Red=new Ball(0,0);
		Ball Blue=new Ball(0,0);
		
		for(int i=0;i<n;i++) {
			String input=br.readLine();
			for(int j=0;j<m;j++) {
				char in=input.charAt(j);
				map[i][j]=in;
				
				if(in=='R') {
					Red.y=i;
					Red.x=j;
				}
				if(in=='B') {
					Blue.y=i;
					Blue.x=j;
				}
			}
		}
		
		int result=max;
		for(int i=1;i<=4;i++) {
			result=Math.min(result, minMove(1,i,Red,Blue,map));
		}
		
		if(result==max)
			System.out.println(-1);
		else
			System.out.println(result);
	}
	
	//1:상
	//2:하
	//3:좌
	//4:우
	public static int minMove(int number,int direction,Ball Red,Ball Blue,char[][] map) {
		int result=max;
		
		char[][] mapClone=deepCopy(map);
		Ball rClone=new Ball(Red.y,Red.x);
		Ball bClone=new Ball(Blue.y,Blue.x);
		
		//움직이기
		mapClone[Red.y][Red.x]='.';
		mapClone[Blue.y][Blue.x]='.';
		move(direction,rClone,bClone,mapClone);
		
		if(rClone.y==Red.y&&rClone.x==Red.x&&bClone.y==Blue.y&&bClone.x==Blue.x)//멈춰있으면 제외
			return max;
		if(mapClone[bClone.y][bClone.x]=='O')//blue가 구멍에 위치해있으면 제외
			return max;
		if(mapClone[rClone.y][rClone.x]=='O')//현재 number return
			return number;
				
		//다음 단계
		for(int i=1;i<=4;i++) {
			if(i!=direction&&number<10)
				result=Math.min(result, minMove(number+1,i,rClone,bClone,mapClone));
		}
		
		return result;
	}
	
	public static void move(int direction,Ball red,Ball blue,char[][] map) {
		switch(direction) {
		case 1://상
			if(red.y<blue.y) {//Red가 Blue보다 위에 있으면
				while(map[red.y-1][red.x]=='.'||map[red.y-1][red.x]=='O') {
					red.y--;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
				while(map[blue.y-1][blue.x]=='.'||map[blue.y-1][blue.x]=='O') {
					blue.y--;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
			}else {
				while(map[blue.y-1][blue.x]=='.'||map[blue.y-1][blue.x]=='O') {
					blue.y--;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
				while(map[red.y-1][red.x]=='.'||map[red.y-1][red.x]=='O') {
					red.y--;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
			}
			break;
		case 2://하
			if(red.y>blue.y) {//Red가 Blue보다 밑에 있으면
				while(map[red.y+1][red.x]=='.'||map[red.y+1][red.x]=='O') {
					red.y++;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
				while(map[blue.y+1][blue.x]=='.'||map[blue.y+1][blue.x]=='O') {
					blue.y++;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
			}else {
				while(map[blue.y+1][blue.x]=='.'||map[blue.y+1][blue.x]=='O') {
					blue.y++;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
				while(map[red.y+1][red.x]=='.'||map[red.y+1][red.x]=='O') {
					red.y++;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
			}
			break;
		case 3://좌
			if(red.x<blue.x) {//Red가 Blue보다 왼쪽에 있으면
				while(map[red.y][red.x-1]=='.'||map[red.y][red.x-1]=='O') {
					red.x--;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
				while(map[blue.y][blue.x-1]=='.'||map[blue.y][blue.x-1]=='O') {
					blue.x--;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
			}else {
				while(map[blue.y][blue.x-1]=='.'||map[blue.y][blue.x-1]=='O') {
					blue.x--;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
				while(map[red.y][red.x-1]=='.'||map[red.y][red.x-1]=='O') {
					red.x--;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
			}
			break;
		case 4://우
			if(red.x>blue.x) {//Red가 Blue보다 오른쪽에 있으면
				while(map[red.y][red.x+1]=='.'||map[red.y][red.x+1]=='O') {
					red.x++;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
				while(map[blue.y][blue.x+1]=='.'||map[blue.y][blue.x+1]=='O') {
					blue.x++;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
			}else {
				while(map[blue.y][blue.x+1]=='.'||map[blue.y][blue.x+1]=='O') {
					blue.x++;
					if(map[blue.y][blue.x]=='O')
						break;
				}
				if(map[blue.y][blue.x]!='O')
					map[blue.y][blue.x]='B';
				while(map[red.y][red.x+1]=='.'||map[red.y][red.x+1]=='O') {
					red.x++;
					if(map[red.y][red.x]=='O')
						break;
				}
				if(map[red.y][red.x]!='O')
					map[red.y][red.x]='R';
			}
			break;
		}
	}
	
	public static char[][] deepCopy(char[][] original){
		char[][] copy=new char[original.length][original[0].length];
		
		for(int i=0;i<original.length;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
		}
		
		return copy;
	}
}

class Ball{
	int y;
	int x;
	Ball(int y,int x){
		this.y=y;
		this.x=x;
	}
}