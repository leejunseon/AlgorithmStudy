/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWL6LhM6A60DFAUY */
//���̺귯������� �����Ǿ��־ ������ �������� �������� �׽�Ʈ���̽��� ���

import java.io.*;
import java.util.*;

public class Solution {
	public static int n;
	public static int[][] map;
	public static int[][] check;
	public static List<Integer> up;
	public static List<Integer> down;
	public static List<Integer> left;
	public static List<Integer> right;
	public static List<Integer> curved;
	public static List<Integer> straighten;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		up=new ArrayList<Integer>();
		down=new ArrayList<Integer>();
		left=new ArrayList<Integer>();
		right=new ArrayList<Integer>();
		curved=new ArrayList<Integer>();
		straighten=new ArrayList<Integer>();
		up.add(2);
		up.add(3);
		up.add(4);
		down.add(2);
		down.add(5);
		down.add(6);
		left.add(1);
		left.add(3);
		left.add(6);
		right.add(1);
		right.add(4);
		right.add(5);
		for(int i=1;i<=2;i++)
			straighten.add(i);
		for(int i=3;i<=6;i++)
			curved.add(i);
		
		
		int t=Integer.parseInt(br.readLine());
		for(int test=1;test<=t;test++) {
			
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			check=new int[n][n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//0:��
			//1:��
			//2:��
			//3:��
			System.out.println("#"+test+" "+Optimal(2,new Pair(0,0),1,map,check));
			
		}
	}
	
	//�������̸� ��ȯ
	public static int Optimal(int in,Pair start,int num,int[][] map,int[][] check) {
		//��������
		if(start.y==n-1&&start.x==n-1) {
			if(in==0) {
				if(curved.contains(map[start.y][start.x])) {
					return num;
				}
			}
			if(in==2) {
				if(straighten.contains(map[start.y][start.x])) {
					return num;
				}
			}
		}
		
		int result=987654321;
		int[][] clone=new int[n][n];
		int[][] chclone=new int[n][n];
		for(int i=0;i<n;i++) {
			clone[i]=map[i].clone();
			chclone[i]=check[i].clone();
		}
		chclone[start.y][start.x]=1;
		
		for(int i=0;i<4;i++) {
			if(i==in)
				continue;
			int nexty=start.y+dy[i];
			int nextx=start.x+dx[i];
			if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n&&clone[nexty][nextx]!=0&&chclone[nexty][nextx]==0) {
				switch(i) {
				case 0://���� 
					switch(in) {
					case 1://�Ա��Ʒ���
						if(straighten.contains(clone[start.y][start.x]))
							result=Math.min(result, Optimal(1,new Pair(nexty,nextx),num+1,clone,chclone));
						break;
					case 2://�Ա�����
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=5;
							result=Math.min(result, Optimal(1,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 3://�Ա�������
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=6;
							result=Math.min(result, Optimal(1,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					}
					break;
				case 1://�Ʒ���
					switch(in) {
					case 0://�Ա�����
						if(straighten.contains(clone[start.y][start.x]))
							result=Math.min(result, Optimal(0,new Pair(nexty,nextx),num+1,clone,chclone));
						break;
					case 2://�Ա�����
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=4;
							result=Math.min(result, Optimal(0,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 3://�Ա�������
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=3;
							result=Math.min(result, Optimal(0,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					}
					break;
				case 2://���ʿ�
					switch(in) {
					case 0://�Ա�����
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=5;
							result=Math.min(result, Optimal(3,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 1://�Ա��Ʒ���
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=4;
							result=Math.min(result, Optimal(3,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 3://�Ա�������
						if(straighten.contains(clone[start.y][start.x]))
							result=Math.min(result, Optimal(3,new Pair(nexty,nextx),num+1,clone,chclone));
						break;
					}
					break;
				case 3://�����ʿ�
					switch(in) {
					case 0://�Ա�����
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=6;
							result=Math.min(result, Optimal(2,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 1://�Ա��Ʒ���
						if(curved.contains(clone[start.y][start.x])) {
							clone[start.y][start.x]=3;
							result=Math.min(result, Optimal(2,new Pair(nexty,nextx),num+1,clone,chclone));
						}
						break;
					case 2://�Ա�����
						if(straighten.contains(clone[start.y][start.x]))
							result=Math.min(result, Optimal(2,new Pair(nexty,nextx),num+1,clone,chclone));
						break;
					}
					break;
				}
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