/* https://www.acmicpc.net/problem/16235 */
//Tree 리스트를 LinkedList로 했을 때는 시간초과 발생. ArrayList썼을 때랑 실행시간 두배 가까이 차이남.
//리스트 요소가 엄청 많고, .get()이 자주 나올때는 무조건 ArrayList, 삽입 삭제가 더 자주일어날 땐 LinkedList (디폴트로는 ArrayList쓸 것)

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int m;
	public static int k;
	public static int[][] energy;
	public static int[][] a;//추가되는 양분 정보
	public static List<ArrayList<List<Tree>>> trees;
	public static int[] dy= {-1,-1,-1,0,0,1,1,1};
	public static int[] dx= {-1,0,1,-1,1,-1,0,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());//n*n크기의 땅
		m=Integer.parseInt(st.nextToken());//m개의 나무
		k=Integer.parseInt(st.nextToken());//k년 지난 후
		
		energy=new int[n][n];
		for(int i=0;i<n;i++) {
			Arrays.fill(energy[i], 5);
		}
		
		a=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				a[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		trees=new ArrayList<ArrayList<List<Tree>>>();
		for(int i=0;i<n;i++) {
			trees.add(new ArrayList<List<Tree>>());
			for(int j=0;j<n;j++) {
				trees.get(i).add(new ArrayList<Tree>());
			}
		}
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());//나무의 위치 x
			int y=Integer.parseInt(st.nextToken());//나무의 위치 y
			int z=Integer.parseInt(st.nextToken());//나무의 나이
			trees.get(x-1).get(y-1).add(new Tree(z,false));
		}
		
		System.out.println(Simulate());
	}
	
	public static int Simulate() {
		int result=0;
		
		List<Tree> list;
		for(int year=1;year<=k;year++) {
			
			//봄
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					list=trees.get(i).get(j);
					for(int k=list.size()-1;k>=0;k--) {
						Tree t=list.get(k);
						if(t.age<=energy[i][j]) {
							energy[i][j]-=t.age;
							t.age++;
						}
						else {
							t.death=true;
						}
					}
				}
			}
			
			//여름
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					list=trees.get(i).get(j);
					for(int k=0;k<list.size();k++) {
						Tree t=list.get(k);
						if(t.death) {
							energy[i][j]+=t.age/2;
							list.remove(k);
							k--;
						}
					}
				}
			}
			
			//가을
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					list=trees.get(i).get(j);
					for(int l=0;l<list.size();l++) {
						Tree t=list.get(l);
						if(t.age%5==0) {
							for(int k=0;k<8;k++) {
								int nexty=i+dy[k];
								int nextx=j+dx[k];
								if(0<=nexty&&nexty<n&&0<=nextx&&nextx<n) {
									trees.get(nexty).get(nextx).add(new Tree(1,false));
								}
							}
						}
					}
				}
			}
			
			//겨울
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					energy[i][j]+=a[i][j];
				}
			}
			
		}
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				result+=trees.get(i-1).get(j-1).size();
			}
		}
		
		return result;
	}
}

class Tree {
	int age;
	boolean death;
	
	Tree(int age,boolean death){
		this.age=age;
		this.death=death;
	}
}