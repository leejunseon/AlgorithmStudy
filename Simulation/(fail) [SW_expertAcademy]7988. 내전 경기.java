/* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWvQZmdKUoEDFASy */
//테스트케이스 99/100 통과. 예외 찾아볼것

import java.io.*;
import java.util.*;

public class Solution {
	public static List<String> member;//멤버 리스트
	public static List<List<Integer>> Synergy;//시너지 관계
	public static List<String> a;
	public static List<String> b;
	public static String[][] input;//인풋내용 저장
	
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
			
			/////////////시너지관계 저장///////////////////
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
			
				List<Integer> oneSynergy=Synergy.get(member.indexOf(one));//one과 시너지관계에 있는 멤버들의 번호
				List<Integer> twoSynergy=Synergy.get(member.indexOf(two));//two와 시너지관계에 있는 멤버들의 번호
				boolean aone=false;
				boolean btwo=false;
				boolean bone=false;
				boolean atwo=false;
				
				for(int j=0;j<a.size();j++) {//a에 one과 시너지관계에 있는 멤버가 있는지 탐색
					if(oneSynergy.contains(member.indexOf(a.get(j)))){//있다면
						aone=true;
						break;
					}
				}
				
				for(int j=0;j<b.size();j++) {//b에 two와 시너지관계에 있는 멤버가 있는지 탐색
					if(twoSynergy.contains(member.indexOf(b.get(j)))){//있다면
						btwo=true;
						break;
					}
				}
				
				for(int j=0;j<a.size();j++) {//a에 two와 시너지관계에 있는 멤버가 있는지 탐색
					if(twoSynergy.contains(member.indexOf(a.get(j)))){//있다면
						atwo=true;
						break;
					}
				}
				
				for(int j=0;j<b.size();j++) {//b에 one과 시너지관계에 있는 멤버가 있는지 탐색
					if(oneSynergy.contains(member.indexOf(b.get(j)))){//있다면
						bone=true;
						break;
					}
				}
			
				if(aone) {//a에 one 못 넣음-->a에 two 넣어야됨
					if(atwo) {//a에 two 못 넣음
						flag=false;//a에 아무것도 넣을 수 없음
						break;
					}else {
						if(bone) {//one을 아무데도 못 넣음
							flag=false;
							break;
						}else {//b에 one 넣을 수 있음
							if(!a.contains(two))
								a.add(two);
							if(!b.contains(one))
								b.add(one);
						}
					}
				}else {//a에 one 넣을 수 있음
					if(btwo) {//b에 two 못 넣음-->b에 one 넣어야됨
						if(atwo) {//a에 two 못넣음
							flag=false;//two를 아무데도 넣을 수 없음
							break;
						}else {//a에 two 넣을 수 있음
							if(bone) {//b에 one 넣을 수 없음
								flag=false;//b에 아무것도 넣을 수 없음
								break;
							}else {//b에 one 넣을 수 있음
								if(!a.contains(two))
									a.add(two);
								if(!b.contains(one))
									b.add(one);									
							}
						}
					}else {//b에 two 넣을 수 있음
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