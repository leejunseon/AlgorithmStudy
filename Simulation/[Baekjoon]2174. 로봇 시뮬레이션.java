/* https://www.acmicpc.net/problem/2174 */

import java.io.*;
import java.util.*;

public class Main {
	public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static int a;//map 가로
	public static int b;//map 세로
	public static int n;//로봇 개수
	public static int m;//명령 개수
	public static int[][] map;
	public static List<Robot> robots;

	public static void main(String[] args) throws IOException{
		st=new StringTokenizer(br.readLine());
		a=Integer.parseInt(st.nextToken());
		b=Integer.parseInt(st.nextToken());
		map=new int[b+1][a+1];

		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());

		robots=new ArrayList<Robot>();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			map[y][x]=i+1;
			String direction=st.nextToken();
			robots.add(new Robot(y,x,direction));
		}

		doCommand();
	}

	public static void doCommand() throws IOException{
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			String command=st.nextToken();
			int repeat=Integer.parseInt(st.nextToken());

			Robot r=robots.get(num-1);
			switch(command) {
			case "L":
				for(int j=0;j<repeat;j++) {
					switch(r.direction) {
					case "E":
						r.direction="N";
						break;
					case "W":
						r.direction="S";
						break;
					case "S":
						r.direction="E";
						break;
					case "N":
						r.direction="W";
						break;
					}
				}
				break;
			case "R":
				for(int j=0;j<repeat;j++) {
					switch(r.direction) {
					case "E":
						r.direction="S";
						break;
					case "W":
						r.direction="N";
						break;
					case "S":
						r.direction="W";
						break;
					case "N":
						r.direction="E";
						break;
					}
				}
				break;
			case "F":
				map[r.y][r.x]=0;
				switch(r.direction) {
				case "E":
					for(int j=0;j<repeat;j++) {
						r.x++;
						if(r.y<=0||r.y>b||r.x>a||r.x<=0) {
							System.out.println("Robot "+num+" crashes into the wall");
							return;
						}
						if(map[r.y][r.x]!=0) {
							System.out.println("Robot "+num+" crashes into robot "+map[r.y][r.x]);
							return;
						}
					}
					break;
				case "W":
					for(int j=0;j<repeat;j++) {
						r.x--;
						if(r.y<=0||r.y>b||r.x>a||r.x<=0) {
							System.out.println("Robot "+num+" crashes into the wall");
							return;
						}
						if(map[r.y][r.x]!=0) {
							System.out.println("Robot "+num+" crashes into robot "+map[r.y][r.x]);
							return;
						}
					}
					break;
				case "S":
					for(int j=0;j<repeat;j++) {
						r.y--;
						if(r.y<=0||r.y>b||r.x>a||r.x<=0) {
							System.out.println("Robot "+num+" crashes into the wall");
							return;
						}
						if(map[r.y][r.x]!=0) {
							System.out.println("Robot "+num+" crashes into robot "+map[r.y][r.x]);
							return;
						}
					}
					break;
				case "N":
					for(int j=0;j<repeat;j++) {
						r.y++;
						if(r.y<=0||r.y>b||r.x>a||r.x<=0) {
							System.out.println("Robot "+num+" crashes into the wall");
							return;
						}
						if(map[r.y][r.x]!=0) {
							System.out.println("Robot "+num+" crashes into robot "+map[r.y][r.x]);
							return;
						}
					}
					break;
				}
				map[r.y][r.x]=num;
				break;
			}
		}
		System.out.println("OK");
	}
}

class Robot{
	int y;
	int x;
	String direction;
	Robot(int y,int x,String direction){
		this.y=y;
		this.x=x;
		this.direction=direction;
	}
}
