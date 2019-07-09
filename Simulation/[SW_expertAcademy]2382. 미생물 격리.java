/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl */
import java.util.*;

public class Solution {
	public static int n;
	public static int m;
	public static int k;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		for(int testcase=1;testcase<=t;testcase++) {
			n=sc.nextInt();//�� �� ���� ����
			m=sc.nextInt();//�ݸ� �ð�
			k=sc.nextInt();//�̻��� ������ ����
			List<microbe> microbes=new ArrayList<microbe>();
			for(int i=0;i<k;i++) {
				microbes.add(new microbe(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()));
			}
			System.out.println("#"+testcase+" "+Simulate(microbes));
		}
	}
	
	public static int Simulate(List<microbe> microbes) {
		int result=0;
		
		for(int time=1;time<=m;time++) {
			int[][] max=new int[n][n];//�ִ밪 ����
			int[][] sum=new int[n][n];//�� ����
			//�̵�
			for(int i=microbes.size()-1;i>=0;i--) {
				microbe m=microbes.get(i);
				switch(m.direction) {
				case 1:
					m.y--;
					if(m.y==0) {
						m.number/=2;
						m.direction=2;
					}
					max[m.y][m.x]=Math.max(max[m.y][m.x], m.number);
					break;
				case 2:
					m.y++;
					if(m.y==n-1) {
						m.number/=2;
						m.direction=1;
					}
					max[m.y][m.x]=Math.max(max[m.y][m.x], m.number);
					break;
				case 3:
					m.x--;
					if(m.x==0) {
						m.number/=2;
						m.direction=4;
					}
					max[m.y][m.x]=Math.max(max[m.y][m.x], m.number);
					break;
				case 4:
					m.x++;
					if(m.x==n-1) {
						m.number/=2;
						m.direction=3;
					}
					max[m.y][m.x]=Math.max(max[m.y][m.x], m.number);
					break;
				}			
			}
			//�� ���� �𿴴���
			for(int i=microbes.size()-1;i>=0;i--) {
				microbe m=microbes.get(i);
				if(max[m.y][m.x]!=0) {//��ġ�� �̻����� �ְ�,
					if(max[m.y][m.x]!=m.number) {//���� �ִ밡 �ƴϸ�
						sum[m.y][m.x]+=m.number;//�� number ��
						microbes.remove(i);//����
					}else {//���� �ִ��,
						sum[m.y][m.x]+=m.number;//�� number ��
					}
				}
			}
			
			for(int i=microbes.size()-1;i>=0;i--) {
				microbe m=microbes.get(i);
				if(sum[m.y][m.x]!=0)
					m.number=sum[m.y][m.x];
			}
		}
		
		for(int i=0;i<microbes.size();i++) {
			microbe m=microbes.get(i);
			result+=m.number;
		}
		
		return result;
	}
}

class microbe{
	int y;
	int x;
	int number;
	int direction;
	//��:1
	//��:2
	//��:3
	//��:4
	microbe(int y,int x,int number,int direction){
		this.y=y;
		this.x=x;
		this.number=number;
		this.direction=direction;
	}
}