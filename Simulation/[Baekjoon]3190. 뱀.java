/* https://www.acmicpc.net/problem/3190 */

import java.io.*;
import java.util.*;

public class Main {
	public static int[][] map;
	public static Map<Integer,String> move;
	public static Deque<Pair> snake;
	public static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		int k=Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			map[y-1][x-1]=1;//»ç°ú
		}
		
		move=new HashMap<Integer,String>();
		int l=Integer.parseInt(br.readLine());
		for(int i=0;i<l;i++) {
			st=new StringTokenizer(br.readLine());
			int sec=Integer.parseInt(st.nextToken());
			String direction=st.nextToken();
			move.put(sec, direction);
		}
		
		snake=new LinkedList<Pair>();//¸Ó¸®´Â First, ²¿¸®´Â Last
		snake.addFirst(new Pair(0,0));
		map[0][0]=-1;//¹ì ¸ö : -1
		System.out.println(endSec(1,4));
	}
	
	//direction
	//1:»ó
	//2:ÇÏ
	//3:ÁÂ
	//4:¿ì
	public static int endSec(int sec,int direction) {
		int result=0;
		Pair head=snake.peekFirst();
		
		int nexty=0;
		int nextx=0;
		switch(direction) {
		case 1:
			nexty=head.y-1;
			nextx=head.x;
			break;
		case 2:
			nexty=head.y+1;
			nextx=head.x;
			break;
		case 3:
			nexty=head.y;
			nextx=head.x-1;
			break;
		case 4:
			nexty=head.y;
			nextx=head.x+1;
			break;
		}
		
		//º®¿¡ ºÎµúÇûÀ» ¶§
		if(nexty<0||nexty>=n||nextx<0||nextx>=n)
			return sec;
		
		//¸ö¿¡ ºÎµúÇûÀ» ¶§
		if(map[nexty][nextx]==-1)
			return sec;
		
		
		if(map[nexty][nextx]!=1) {//»ç°ú¸ÔÁö ¾Ê¾Ò´Ù¸é
			map[snake.peekLast().y][snake.peekLast().x]=0;
			snake.removeLast();//²¿¸® Áö¿ì±â
		}
		snake.addFirst(new Pair(nexty,nextx));//¸Ó¸® ´ÃÀÌ±â		
		map[nexty][nextx]=-1;
		
		
		String change="";
		if(move.containsKey(sec)) {//¹æÇâÁ¶ÀÛÇØ¾ßÇÒ °æ¿ì
			change=move.get(sec);
			switch(change) {
			case "L"://¿ÞÂÊ
				switch(direction) {
				case 1:
					direction=3;
					break;
				case 2:
					direction=4;
					break;
				case 3:
					direction=2;
					break;
				case 4:
					direction=1;
					break;
				}
				break;
			case "D"://¿À¸¥ÂÊ
				switch(direction) {
				case 1:
					direction=4;
					break;
				case 2:
					direction=3;
					break;
				case 3:
					direction=1;
					break;
				case 4:
					direction=2;
					break;
				}
				break;
			}
		}
				
		result=endSec(sec+1,direction);
			
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