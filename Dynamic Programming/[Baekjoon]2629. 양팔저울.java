/* https://www.acmicpc.net/problem/2629 */

import java.util.*;

public class Main {	
	static int[][] cache;
	static int Sinker_n;
	static int[] Sinker_w;
	static int Ball_n;
	static int[] Ball_w;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		Sinker_n=sc.nextInt();//���� ����
		Sinker_w=new int[Sinker_n];//���� ����
		for(int i=0;i<Sinker_n;i++) {
			Sinker_w[i]=sc.nextInt();
			sum+=Sinker_w[i];
		}
		Ball_n=sc.nextInt();//������ ����
		Ball_w=new int[Ball_n];//������ ����
		for(int i=0;i<Ball_n;i++) {
			Ball_w[i]=sc.nextInt();
		}
		
		for(int i=0;i<Ball_n;i++) {
			cache=new int[Sinker_n+1][Ball_w[i]+sum+1];
			for(int j=0;j<=Sinker_n;j++) {
				Arrays.fill(cache[j], -1);
			}
			int ans=Equals(0,Ball_w[i]);
			if(ans==1)
				System.out.print('Y');
			else
				System.out.print('N');
			if(i!=Ball_n-1)
				System.out.print(" ");
		}
	}
	
	public static int Equals(int now,int diff) {
		if(diff==0)
			return 1;
		if(now==Sinker_n&&diff!=0)
			return 0;
		
		if(cache[now][diff]!=-1)
			return cache[now][diff];
		
		if(diff>Sinker_w[now]) {//diff�� ���� ���� ���Ժ��� �� Ŭ ��
			if(Equals(now+1,diff-Sinker_w[now])==1||Equals(now+1,diff+Sinker_w[now])==1||Equals(now+1,diff)==1) {
				cache[now][diff]=1;
				return cache[now][diff];
			}
		}else {//diff�� ���� ���� ���Ժ��� �۰ų� ���� ��
			if(Equals(now+1,Sinker_w[now]-diff)==1||Equals(now+1,diff+Sinker_w[now])==1||Equals(now+1,diff)==1) {
				cache[now][diff]=1;
				return cache[now][diff];
			}
		}
		cache[now][diff]=0;
		return cache[now][diff];
	}
}