/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWvQZmdKUoEDFASy */
//�׽�Ʈ���̽� 99/100 ���. ���� ã�ƺ���

import java.io.*;
import java.util.*;

public class Solution {
	public static List<String> member;//��� ����Ʈ
	public static List<List<Integer>> Synergy;//�ó��� ����
	public static List<String> a;
	public static List<String> b;
	public static String[][] input;//��ǲ���� ����
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T=Integer.parseInt(br.readLine());
		for(int test=1;test<=T;test++) {
			
			Synergy=new ArrayList<List<Integer>>();
			member=new ArrayList<String>();
			a=new ArrayList<String>();
			b=new ArrayList<String>();
			
			int k=Integer.parseInt(br.readLine());
			input=new String[k][2];
			
			/////////////�ó������� ����///////////////////
			for(int i=0;i<k*2;i++) {
				Synergy.add(new ArrayList<Integer>());
			}
			
			for(int i=0;i<k;i++) {
				st=new StringTokenizer(br.readLine());
				String in="";
				int s=0;
				int e=0;
				for(int j=0;j<2;j++) {
					in=st.nextToken();
					if(!member.contains(in)) {
						member.add(in);
					}
					
					if(j==0)
						s=member.indexOf(in);
					else if(j==1)
						e=member.indexOf(in);
					
					input[i][j]=in;
				}
				Synergy.get(s).add(e);
				Synergy.get(e).add(s);
			}
			//////////////////////////////////////////
			
			
			boolean flag=true;
			
			for(int i=0;i<k;i++) {
				String one=input[i][0];
				String two=input[i][1];
			
				List<Integer> oneSynergy=Synergy.get(member.indexOf(one));//one�� �ó������迡 �ִ� ������� ��ȣ
				List<Integer> twoSynergy=Synergy.get(member.indexOf(two));//two�� �ó������迡 �ִ� ������� ��ȣ
				boolean aone=false;
				boolean btwo=false;
				boolean bone=false;
				boolean atwo=false;
				
				for(int j=0;j<a.size();j++) {//a�� one�� �ó������迡 �ִ� ����� �ִ��� Ž��
					if(oneSynergy.contains(member.indexOf(a.get(j)))){//�ִٸ�
						aone=true;
						break;
					}
				}
				
				for(int j=0;j<b.size();j++) {//b�� two�� �ó������迡 �ִ� ����� �ִ��� Ž��
					if(twoSynergy.contains(member.indexOf(b.get(j)))){//�ִٸ�
						btwo=true;
						break;
					}
				}
				
				for(int j=0;j<a.size();j++) {//a�� two�� �ó������迡 �ִ� ����� �ִ��� Ž��
					if(twoSynergy.contains(member.indexOf(a.get(j)))){//�ִٸ�
						atwo=true;
						break;
					}
				}
				
				for(int j=0;j<b.size();j++) {//b�� one�� �ó������迡 �ִ� ����� �ִ��� Ž��
					if(oneSynergy.contains(member.indexOf(b.get(j)))){//�ִٸ�
						bone=true;
						break;
					}
				}
			
				if(aone) {//a�� one �� ����-->a�� two �־�ߵ�
					if(atwo) {//a�� two �� ����
						flag=false;//a�� �ƹ��͵� ���� �� ����
						break;
					}else {
						if(bone) {//one�� �ƹ����� �� ����
							flag=false;
							break;
						}else {//b�� one ���� �� ����
							if(!a.contains(two))
								a.add(two);
							if(!b.contains(one))
								b.add(one);
						}
					}
				}else {//a�� one ���� �� ����
					if(btwo) {//b�� two �� ����-->b�� one �־�ߵ�
						if(atwo) {//a�� two ������
							flag=false;//two�� �ƹ����� ���� �� ����
							break;
						}else {//a�� two ���� �� ����
							if(bone) {//b�� one ���� �� ����
								flag=false;//b�� �ƹ��͵� ���� �� ����
								break;
							}else {//b�� one ���� �� ����
								if(!a.contains(two))
									a.add(two);
								if(!b.contains(one))
									b.add(one);									
							}
						}
					}else {//b�� two ���� �� ����
						if(!a.contains(one))
							a.add(one);
						if(!b.contains(two))
							b.add(two);
					}
				}
			}
			
			if(flag) {
				System.out.println("#"+test+" "+"Yes");
			}else {
				System.out.println("#"+test+" "+"No");
			}	
			
		}
	}
}