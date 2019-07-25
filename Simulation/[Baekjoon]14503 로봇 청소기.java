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
		n=Integer.parseInt(st.nextToken());//����
		m=Integer.parseInt(st.nextToken());//����
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
	//0:��
	//1:��
	//2:��
	//3:��
	public static int count(int currenty,int currentx,int direction,int num) {
		int result=0;
		
		if(map[currenty][currentx]==0)
			map[currenty][currentx]=2;
		
		boolean zero=false;
		for(int i=0;i<4;i++) {
			if(map[currenty+dy[i]][currentx+dx[i]]==0) {
				zero=true;//�� ������ ����
				break;
			}
		}
		
		int nexty=0;
		int nextx=0;
		if(zero) {
			switch(direction) {
			case 0://��
				nexty=currenty;
				nextx=currentx-1;
				if(map[nexty][nextx]==0) {//���ʿ� �ڸ��� ���� ���
					result=count(nexty,nextx,3,num+1);//�������� ��ĭ �̵�
				}else {
					result=count(currenty,currentx,3,num);//���⸸ ��������
				}
				break;
			case 1://��
				nexty=currenty-1;
				nextx=currentx;
				if(map[nexty][nextx]==0) {//���ʿ� �ڸ��� ���� ���
					result=count(nexty,nextx,0,num+1);//�������� ��ĭ �̵�
				}else {
					result=count(currenty,currentx,0,num);//���⸸ ��������
				}
				break;
			case 2://��
				nexty=currenty;
				nextx=currentx+1;
				if(map[nexty][nextx]==0) {//���ʿ� �ڸ��� ���� ���
					result=count(nexty,nextx,1,num+1);//�������� ��ĭ �̵�
				}else {
					result=count(currenty,currentx,1,num);//���⸸ ��������
				}
				break;
			case 3://��
				nexty=currenty+1;
				nextx=currentx;
				if(map[nexty][nextx]==0) {//���ʿ� �ڸ��� ���� ���
					result=count(nexty,nextx,2,num+1);//�������� ��ĭ �̵�
				}else {
					result=count(currenty,currentx,2,num);//���⸸ ��������
				}
				break;
			}
		}else {//�� ������ ����
			switch(direction) {
			case 0://��
				nexty=currenty+1;
				nextx=currentx;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//����
				else
					return num+1;
				break;
			case 1://��
				nexty=currenty;
				nextx=currentx-1;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//����
				else
					return num+1;
				break;
			case 2://��
				nexty=currenty-1;
				nextx=currentx;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//����
				else
					return num+1;
				break;
			case 3://��
				nexty=currenty;
				nextx=currentx+1;
				if(map[nexty][nextx]==2)
					result=count(nexty,nextx,direction,num);//����
				else
					return num+1;
				break;
			}
		}
		
		return result;
	}
}