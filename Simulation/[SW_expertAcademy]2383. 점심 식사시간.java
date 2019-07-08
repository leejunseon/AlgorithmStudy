/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl */
import java.util.*;

public class Solution {
	public static int[][] map;
	public static List<Pair> stairs;//��� ��ġ �����
	public static List<Integer> stairLen;//��� ���� �����
	public static List<Person> stairDoor1;//ù��° ��� �Ա�
	public static List<Person> stairDoor2;//�ι�° ��� �Ա�
	public static List<Person> stair1;//ù��° ���
	public static List<Person> stair2;//�ι�° ���
	public static int minTime=987654321;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int t=sc.nextInt();
		
		for(int test_case=1;test_case<=t;test_case++) {
			int n=sc.nextInt();
			List<Person> people=new LinkedList<Person>();
			stairs=new LinkedList<Pair>();
			stairLen=new LinkedList<Integer>();
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					int input=sc.nextInt();
					if(input==1) {
						people.add(new Person(new Pair(i,j),0,0,-1));
					}
					if(input>1) {
						stairs.add(new Pair(i,j));
						stairLen.add(input);
					}
				}
			}
			
			//1���� 2�� ������� ���� �ο� ������
			int min=Math.min(calc(0,1,people), calc(0,2,people));
			
			System.out.println("#"+test_case+" "+min);
		}
	}
	
	public static int calc(int order,int stnum,List<Person> people) {
		people.get(order).destination=stnum;
		if(order==people.size()-1) {
			stairDoor1=new LinkedList<Person>();//ù��° ��� �Ա�
			stairDoor2=new LinkedList<Person>();//�ι�° ��� �Ա�
			stair1=new LinkedList<Person>();//ù��° ���
			stair2=new LinkedList<Person>();//�ι�° ���
			for(int i=0;i<people.size();i++) {
				Person p=people.get(i);
				p.remainDist=distance(p.first,stairs.get(p.destination-1));
			}
			return checkMin(people);
		}
		int result=minTime;
		result=Math.min(result, calc(order+1,1,people));
		result=Math.min(result, calc(order+1,2,people));
		return result;
	}
	
	public static int checkMin(List<Person> people) {//�ִܽð� ����ϴ� �޼ҵ�
		List<Person> runner=new LinkedList<Person>();//������� ���� �����
		runner.addAll(people);

		int time=0;
		
		while(!(runner.isEmpty()&&stair1.isEmpty()&&stair2.isEmpty()&&stairDoor1.isEmpty()&&stairDoor2.isEmpty())) {
			time++;
			
			//��ܿ� �ִ� ����� ����
			for(int i=stair1.size()-1;i>=0;i--) {
				Person p=stair1.get(i);
				p.remainStairs--;
				if(p.remainStairs==0) {
					stair1.remove(i);
					continue;
				}
			}
			for(int i=stair2.size()-1;i>=0;i--) {
				Person p=stair2.get(i);
				p.remainStairs--;
				if(p.remainStairs==0) {
					stair2.remove(i);
					continue;
				}
			}
			
			//�Ա��� ������� ����� ��� ����
			for(int i=stairDoor1.size()-1;i>=0;i--) {
				stairDoor1.get(i).remainStairs=stairLen.get(0);
				if(stair1.size()<3)
					stair1.add(stairDoor1.remove(i));
			}
			for(int i=stairDoor2.size()-1;i>=0;i--) {
				stairDoor2.get(i).remainStairs=stairLen.get(1);
				if(stair2.size()<3)
					stair2.add(stairDoor2.remove(i));
			}
			
			//����� �Ÿ� ����
			for(int i=runner.size()-1;i>=0;i--) {
				Person p=runner.get(i);
				p.remainDist--;//��ĭ �̵�			
			}
			
			//�Ա������� ����� �Ա� ��� ����
			for(int i=runner.size()-1;i>=0;i--) {
				Person p=runner.get(i);
				if(p.remainDist==0) {//�������� �ٴٶ�����
					if(p.destination==1)
						stairDoor1.add(runner.remove(i));//�Ա��� ��
					if(p.destination==2)
						stairDoor2.add(runner.remove(i));//�Ա��� ��
				}
			}
		}
		
		return time;
	}
	
	public static int distance(Pair start,Pair end) {
		int result=0;
		result+=Math.abs(start.x-end.x);
		result+=Math.abs(start.y-end.y);
		return result;
	}
}

class Person{
	Pair first;
	int destination;//������ : 1,2
	int remainDist;//���������� ���� �Ÿ�
	int remainStairs;//��� ���� �ð�
	Person(Pair first,int destination, int remainDist,int remainStairs){
		this.first=first;
		this.destination=destination;
		this.remainDist=remainDist;
		this.remainStairs=remainStairs;
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