/* https://www.acmicpc.net/problem/15683 */
//가지치기하려고 조건문걸어놓은 부분을 주석처리하니 통과가 되었다.
//주석을 해제해도 문제상의 테스트케이스와 여러 반례들은 모두 통과가 되는데 fail이 뜸. 이유를 모르겠다. 추후 다시 분석해보기.

import java.util.*;
import java.io.*;

public class Main {
	public static final int max=987654321;
	public static int n;
	public static int m;
	public static int[][] map;
	public static List<cctv> cctvs;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		cctvs=new ArrayList<cctv>();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(1<=map[i][j]&&map[i][j]<=5) {
					cctvs.add(new cctv(i,j,map[i][j]));
				}
			}
		}
		
		if(cctvs.size()>0)
			System.out.println(calc(0,map));
		else
			System.out.println(count(map));

	}
	
	public static int calc(int now,int[][] Map) {
		int result=max;
		int[][] copy;
		
		if(now==cctvs.size()) {
			copy=deepCopy(Map);
			return count(copy);
		}
		
		cctv c=cctvs.get(now);
		switch(cctvs.get(now).num) {
		case 1://1번cctv
			copy=deepCopy(Map);
/*			if(c.y-1>=0&&c.y-1!=6) {
*/				process(copy,c.y,c.x,Direction.UP);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if(c.y+1<n&&c.y+1!=6) {
*/				process(copy,c.y,c.x,Direction.DOWN);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if(c.x-1>=0&&c.x-1!=6) {
*/				process(copy,c.y,c.x,Direction.LEFT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/
			copy=deepCopy(Map);
/*			if(c.x+1<m&&c.x+1!=6) {
*/				process(copy,c.y,c.x,Direction.RIGHT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			break;
		case 2://2번cctv
			copy=deepCopy(Map);
/*			if((c.y-1>=0&&c.y-1!=6)||(c.y+1<n&&c.y+1!=6)) {
*/				process(copy,c.y,c.x,Direction.UP);
				process(copy,c.y,c.x,Direction.DOWN);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.x-1>=0&&c.x-1!=6)||(c.x+1<m&&c.x+1!=6)) {
*/				process(copy,c.y,c.x,Direction.LEFT);
				process(copy,c.y,c.x,Direction.RIGHT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			break;
		case 3://3번cctv
			copy=deepCopy(Map);
/*			if((c.y-1>=0&&c.y-1!=6)||(c.x+1<m&&c.x+1!=6)) {
*/				process(copy,c.y,c.x,Direction.UP);
				process(copy,c.y,c.x,Direction.RIGHT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.x+1<m&&c.x+1!=6)||(c.y+1<n&&c.y+1!=6)) {
*/				process(copy,c.y,c.x,Direction.RIGHT);
				process(copy,c.y,c.x,Direction.DOWN);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.y+1<n&&c.y+1!=6)||(c.x-1>=0&&c.x-1!=6)) {
*/				process(copy,c.y,c.x,Direction.DOWN);
				process(copy,c.y,c.x,Direction.LEFT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.x-1>=0&&c.x-1!=6)||(c.y-1>=0&&c.y-1!=6)) {
*/				process(copy,c.y,c.x,Direction.LEFT);
				process(copy,c.y,c.x,Direction.UP);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			break;
		case 4://4번cctv
			copy=deepCopy(Map);
/*			if((c.x-1>=0&&c.x-1!=6)||(c.y-1>=0&&c.y-1!=6)||(c.x+1<m&&c.x+1!=6)) {
*/				process(copy,c.y,c.x,Direction.LEFT);
				process(copy,c.y,c.x,Direction.UP);
				process(copy,c.y,c.x,Direction.RIGHT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.y-1>=0&&c.y-1!=6)||(c.x+1<m&&c.x+1!=6)||(c.y+1<n&&c.y+1!=6)) {
*/				process(copy,c.y,c.x,Direction.UP);
				process(copy,c.y,c.x,Direction.RIGHT);
				process(copy,c.y,c.x,Direction.DOWN);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.x+1<m&&c.x+1!=6)||(c.y+1<n&&c.y+1!=6)||(c.x-1>=0&&c.x-1!=6)) {
*/				process(copy,c.y,c.x,Direction.RIGHT);
				process(copy,c.y,c.x,Direction.DOWN);
				process(copy,c.y,c.x,Direction.LEFT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			
			copy=deepCopy(Map);
/*			if((c.y+1<n&&c.y+1!=6)||(c.x-1>=0&&c.x-1!=6)||(c.y-1>=0&&c.y-1!=6)) {
*/				process(copy,c.y,c.x,Direction.DOWN);
				process(copy,c.y,c.x,Direction.LEFT);
				process(copy,c.y,c.x,Direction.UP);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			break;
		case 5://5번cctv
			copy=deepCopy(Map);
/*			if((c.y+1<n&&c.y+1!=6)||(c.x-1>=0&&c.x-1!=6)||(c.y-1>=0&&c.y-1!=6)||(c.x+1<m&&c.x+1!=6)) {
*/				process(copy,c.y,c.x,Direction.UP);
				process(copy,c.y,c.x,Direction.DOWN);
				process(copy,c.y,c.x,Direction.LEFT);
				process(copy,c.y,c.x,Direction.RIGHT);
				result=Math.min(result, calc(now+1,copy));
/*			}
*/			break;
		}
		
		return result;
	}
	
	public static int[][] deepCopy(int[][] original){
		int[][] copy=new int[original.length][original[0].length];
		
		for(int i=0;i<original.length;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, original[0].length);
		}
		
		return copy;
	}
	
	public static void process(int[][] map,int y,int x,Direction direction) {
		switch(direction) {
		case UP:
			for(int i=y-1;i>=0;i--) {
				if(map[i][x]==6)
					break;
				if(map[i][x]==0) {
					map[i][x]=9;
				}
			}
			break;
		case DOWN:
			for(int i=y+1;i<n;i++) {
				if(map[i][x]==6)
					break;
				if(map[i][x]==0) {
					map[i][x]=9;
				}
			}
			break;
		case LEFT:
			for(int i=x-1;i>=0;i--) {
				if(map[y][i]==6)
					break;
				if(map[y][i]==0) {
					map[y][i]=9;
				}
			}
			break;
		case RIGHT:
			for(int i=x+1;i<m;i++) {
				if(map[y][i]==6)
					break;
				if(map[y][i]==0) {
					map[y][i]=9;
				}
			}
			break;
		}
	}
	
	public static int count(int[][] M) {
		int count=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(M[i][j]==0)
					count++;
			}
		}
		return count;
	}
}

class cctv{
	int y;
	int x;
	int num;
	cctv(int y,int x,int num){
		this.y=y;
		this.x=x;
		this.num=num;
	}
}

enum Direction{
	UP,DOWN,LEFT,RIGHT
}