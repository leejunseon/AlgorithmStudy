/* https://www.acmicpc.net/source/12733434 */
import java.util.*;

public class Main {
	static int k;
	static int w;//가로
	static int h;//세로
	static int[][][] check;
	static int[][] ground;
	static int[] dy= {0,0,1,-1};
	static int[] dx= {1,-1,0,0};
	static int[] ky= {-2,-1,1,2,2,1,-1,-2};
	static int[] kx= {1,2,2,1,-1,-2,-2,-1};
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		k=sc.nextInt();
		w=sc.nextInt();
		h=sc.nextInt();
		check=new int[h][w][k+1];
		ground=new int[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				ground[i][j]=sc.nextInt();
			}
		}
		System.out.println(bfs());
	}
	
	public static int bfs() {//큐에 넣고 바로 check, dp와 같이 필요한 정보를 queue 알맹이 안에 넣을 수 있음. 최소 경로 구하는 문제는 bfs
		Queue<Pair> q=new LinkedList<Pair>();
		q.add(new Pair(0,0,0,k));
		check[0][0][k]=1;
		
		while(!q.isEmpty()) {
			Pair p=q.remove();
			int y=p.y;
			int x=p.x;
			int Cnt=p.Cnt;
			int HorseCnt=p.HorseCnt;
			check[y][x][HorseCnt]=1;
			
			if(y==h-1&&x==w-1)
				return Cnt;
			
			for(int i=0;i<4;i++) {
				int nextx=x+dx[i];
				int nexty=y+dy[i];
				if(0<=nextx&&nextx<w&&0<=nexty&&nexty<h&&ground[nexty][nextx]!=1&&check[nexty][nextx][HorseCnt]==0) {
					q.add(new Pair(nexty,nextx,Cnt+1,HorseCnt));
					check[nexty][nextx][HorseCnt]=1;
				}
			}
			
			if(HorseCnt>0) {
				for(int i=0;i<8;i++) {
					int nextx=x+kx[i];
					int nexty=y+ky[i];
					if(0<=nextx&&nextx<w&&0<=nexty&&nexty<h&&ground[nexty][nextx]!=1&&check[nexty][nextx][HorseCnt-1]==0) {
						q.add(new Pair(nexty,nextx,Cnt+1,HorseCnt-1));
						check[nexty][nextx][HorseCnt-1]=1;
					}
				}
			}
		}
		
		return -1;
	}
}

class Pair{
	int x;
	int y;
	int Cnt;
	int HorseCnt;
	Pair(int y,int x,int Cnt,int HorseCnt){
		this.x=x;
		this.y=y;
		this.Cnt=Cnt;
		this.HorseCnt=HorseCnt;
	}
}