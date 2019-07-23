/* https://www.acmicpc.net/problem/14499 */

import java.io.*;
import java.util.*;

public class Main {
	public static int[][] map;
	public static int[] directions;
	public static int n;
	public static int m;
	public static int k;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());//map ����
		m=Integer.parseInt(st.nextToken());//map ����
		map=new int[n][m];
		int y=Integer.parseInt(st.nextToken());//�ֻ��� ��ǥ y
		int x=Integer.parseInt(st.nextToken());//�ֻ��� ��ǥ x
		k=Integer.parseInt(st.nextToken());//��� ���� k
		directions=new int[k];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<k;i++)
			directions[i]=Integer.parseInt(st.nextToken());
		
		int[] dice=new int[6];
		Print(y,x,1,directions[0],dice);
	}
	
	public static void Print(int y,int x,int order,int direction,int[] dice) {
		
		int currentx=0;
		int currenty=0;
		switch(direction) {
		case 1://��
			currenty=y;
			currentx=x+1;
			break;
		case 2://��
			currenty=y;
			currentx=x-1;
			break;
		case 3://��
			currenty=y-1;
			currentx=x;
			break;
		case 4://��
			currenty=y+1;
			currentx=x;
			break;
		}
		
		if(0<=currenty&&currenty<n&&0<=currentx&&currentx<m) {
			dice=move(direction,dice);//�����̰�
			
			if(map[currenty][currentx]==0) {
				map[currenty][currentx]=dice[1];
			}else {
				dice[1]=map[currenty][currentx];
				map[currenty][currentx]=0;
			}
		
			System.out.print(dice[3]+" ");
			if(order<k)
				Print(currenty,currentx,order+1,directions[order],dice);
		}else {
			if(order<k)
				Print(y,x,order+1,directions[order],dice);
		}
	}
	
	public static int[] move(int direction,int[] dice) {
		int[] copy=dice.clone();
		
		switch(direction) {
		case 1://��
			copy[1]=dice[5];
			copy[4]=dice[1];
			copy[5]=dice[3];
			copy[3]=dice[4];
			break;
		case 2://��
			copy[4]=dice[3];
			copy[1]=dice[4];
			copy[5]=dice[1];
			copy[3]=dice[5];
			break;
		case 3://��
			copy[0]=dice[3];
			copy[1]=dice[0];
			copy[2]=dice[1];
			copy[3]=dice[2];
			break;
		case 4://��
			copy[0]=dice[1];
			copy[1]=dice[2];
			copy[2]=dice[3];
			copy[3]=dice[0];
			break;
		}
		
		return copy;
	}
}