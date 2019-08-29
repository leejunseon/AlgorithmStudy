/* https://www.acmicpc.net/problem/17135 */

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int m;
	public static int d;
	public static int[][] map;
	public static List<Enemy> enemies;
	public static int dy[]= {0,-1,0};
	public static int dx[]= {-1,0,1};

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				if(Integer.parseInt(st.nextToken())==1) {
					map[i][j]=1;
				}
			}
		}

		int[] archers=new int[m];
		System.out.println(Max(archers,0,-1));
	}

	public static int Max(int[] archers,int num,int last) {
		int result=0;
		int[] copy=archers.clone();

		if(num==3) {
			//적 위치 초기화
			enemies=new ArrayList<Enemy>();
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==1) {
						enemies.add(new Enemy(i,j));
					}
				}
			}
			//최대 값 계산
			return calc(copy);
		}

		//archer위치 정하기 - dfs
		for(int i=last+1;i<m;i++) {
			copy[i]=1;
			result=Math.max(result, Max(copy,num+1,i));
			copy[i]=0;
		}

		return result;
	}

	public static int calc(int[] archers) {
		int result=0;

		//시뮬레이션에 쓸 맵
		int[][] copyMap=new int[n][m];
		for(int i=0;i<n;i++)
			System.arraycopy(map[i], 0, copyMap[i], 0, m);

		while(true) {
			//각 아처 당 가장 가까이에 있는 적 죽이기 -> bfs
			for(int i=0;i<m;i++) {
				if(archers[i]==1) {
					bfs(new Archer(n,i,d),copyMap);
				}
			}

			//죽은 적,map 넘어간 적 계산
			for(int i=0;i<enemies.size();i++) {
				Enemy e=enemies.get(i);
				if(e.death==true) {
					result++;
					enemies.remove(i);
					i--;
				}else {
					e.y++;
					if(e.y>=n) {
						enemies.remove(i);
						i--;
					}
				}
			}

			//map update
			if(!enemies.isEmpty()) {
				copyMap=new int[n][m];
				for(Enemy e:enemies)
					copyMap[e.y][e.x]=1;
			}else//적 모두 없어졌으면 끝
				break;
		}
		return result;
	}

	public static void bfs(Archer archer,int[][] Map) {
		//가장 왼쪽에 있는 적 -> 우선순위 큐
		Queue<Archer> q=new LinkedList<Archer>();
		q.add(archer);
		boolean flag=false;
		while(!q.isEmpty()) {
			Archer a=q.remove();
			for(int i=0;i<3;i++) {
				int nexty=a.y+dy[i];
				int nextx=a.x+dx[i];
				if(a.dist>0&&0<=nexty&&nexty<n&&0<=nextx&&nextx<m) {
					if(Map[nexty][nextx]==1) {
						for(Enemy e:enemies) {
							if(e.y==nexty&&e.x==nextx) {
								e.death=true;
								flag=true;
								break;
							}
						}
						break;
					}
					q.add(new Archer(nexty,nextx,a.dist-1));
				}
			}
			//한마리만 죽일 수 있음
			if(flag)
				break;
		}
	}

}

class Archer {
	int y;
	int x;
	int dist;
	Archer(int y,int x,int dist){
		this.y=y;
		this.x=x;
		this.dist=dist;
	}
}

class Enemy{
	int y;
	int x;
	boolean death;
	Enemy(int y,int x){
		this.y=y;
		this.x=x;
		death=false;
	}
}
