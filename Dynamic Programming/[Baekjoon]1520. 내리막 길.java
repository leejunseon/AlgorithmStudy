/* https://www.acmicpc.net/problem/1520 */
import java.util.*;

public class Main {
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,-1,0,1};
	static int[][] map,dp;
	static int m,n;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		m=sc.nextInt();
		n=sc.nextInt();
		map=new int[m][n];
		dp=new int[m][n];
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=sc.nextInt();
				dp[i][j]=-1;
			}
		}
		System.out.println(dfs(0,0));
	}
	
	public static int dfs(int y,int x) {
		if(y==m-1&&x==n-1) {
		//끝점 도착
			return 1;
		}
		
		if(dp[y][x]==-1) {//안와본 곳이라면
			dp[y][x]=0;
			for(int i=0;i<4;i++) {
				int nx=x+dx[i];
				int ny=y+dy[i];
				
				if(0<=nx&&nx<n&&0<=ny&&ny<m) {
					if(map[y][x]>map[ny][nx]) {
						dp[y][x]+=dfs(ny,nx);
					}
				}
			}
		}
		
		return dp[y][x];//와본 곳이라면
	}
	
}
