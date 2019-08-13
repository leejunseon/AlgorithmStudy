/* https://www.acmicpc.net/problem/15685 */

import java.util.*;
import java.io.*;

public class Main {
	public static int r;
	public static int c;
	public static int m;
	public static List<Shark> sharks;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		sharks=new ArrayList<Shark>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());//속력
			int d=Integer.parseInt(st.nextToken());//이동방향
			int z=Integer.parseInt(st.nextToken());//크기
			sharks.add(new Shark(y,x,s,d,z));
		}
		
		Collections.sort(sharks,new Comparator<Shark>() {

			@Override
			public int compare(Shark o1, Shark o2) {
				// TODO Auto-generated method stub
				if(o1.x!=o2.x)
					return o1.x-o2.x;
				else
					return o1.y-o2.y;
			}
			
		});
		
		System.out.println(Simulate());
	}
	
	public static int Simulate() {
		int result=0;
		
		for(int i=1;i<=c;i++) {//낚시꾼이동
						
			//포획
			for(int j=0;j<sharks.size();j++) {
				Shark s=sharks.get(j);
				if(s.x==i) {
					sharks.remove(j);
					j--;
					result+=s.z;
					break;
				}
			}
			
			//상어이동
			for(int j=0;j<sharks.size();j++) {
				Shark s=sharks.get(j);
				switch(s.d) {
				case 1://상
					for(int k=0;k<s.s;k++) {
						if(s.d==1) {
							if(s.y-1>=1)
								s.y--;
							else {
								s.d=2;
								s.y++;
							}
						}else if(s.d==2) {
							if(s.y+1<=r)
								s.y++;
							else {
								s.d=1;
								s.y--;
							}
						}
					}
					break;
				case 2://하
					for(int k=0;k<s.s;k++) {
						if(s.d==2) {
							if(s.y+1<=r)
								s.y++;
							else {
								s.d=1;
								s.y--;
							}
						}else if(s.d==1) {
							if(s.y-1>=1)
								s.y--;
							else {
								s.d=2;
								s.y++;
							}
						}
					}
					break;
				case 3://우
					for(int k=0;k<s.s;k++) {
						if(s.d==3) {
							if(s.x+1<=c)
								s.x++;
							else {
								s.d=4;
								s.x--;
							}
						}else if(s.d==4) {
							if(s.x-1>=1)
								s.x--;
							else {
								s.d=3;
								s.x++;
							}
						}
					}
					break;
				case 4://좌
					for(int k=0;k<s.s;k++) {
						if(s.d==4) {
							if(s.x-1>=1)
								s.x--;
							else {
								s.d=3;
								s.x++;
							}
						}else if(s.d==3) {
							if(s.x+1<=c)
								s.x++;
							else {
								s.d=4;
								s.x--;
							}
						}
					}
					break;
				}
			}	
			
			Collections.sort(sharks,new Comparator<Shark>() {

				@Override
				public int compare(Shark o1, Shark o2) {
					// TODO Auto-generated method stub
					if(o1.x!=o2.x)
						return o1.x-o2.x;
					else
						return o1.y-o2.y;
				}
				
			});
			
			//서열 정리
			for(int j=0;j<sharks.size();j++) {
				Shark s=sharks.get(j);
				if(j+1<sharks.size()) {
					Shark next=sharks.get(j+1);
					if(s.y==next.y&&s.x==next.x) {
						if(s.z<next.z) {
							sharks.remove(j);
						}else {
							sharks.remove(j+1);
						}
						j--;
					}
				}
			}
			
		}
		
		return result;
	}
}

class Shark{
	int y;
	int x;
	int s;
	int d;//1:상, 2:하, 3:우, 4:좌
	int z;
	Shark(int y,int x,int s,int d,int z){
		this.y=y;
		this.x=x;
		this.s=s;
		this.d=d;
		this.z=z;
	}
}