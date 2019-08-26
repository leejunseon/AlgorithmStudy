/* http://tech.kakao.com/2017/08/11/code-festival-round-1/ */

import java.util.*;
import java.io.*;

public class Solution {
	public static int[][] check;
	public static int[] dy= {-1,1,0,0};
	public static int[] dx= {0,0,-1,1};

	public static void main(String[] args) {
		int[][] picture= {{1, 1, 1, 0},
						  {1, 2, 2, 0},
						  {1, 0, 0, 1},
						  {0, 0, 0, 1},
						  {0, 0, 0, 3},
						  {0, 0, 0, 3}};
		int[] result=solution(picture.length,picture[0].length,picture);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}

	public static int[] solution(int m, int n, int[][] picture) {
	      int numberOfArea = 0;
	      int maxSizeOfOneArea = 0;

	      check=new int[m][n];
	      for(int i=0;i<m;i++) {
	    	  for(int j=0;j<n;j++) {
	    		  if(picture[i][j]!=0&&check[i][j]==0) {
	    			  maxSizeOfOneArea=Math.max(maxSizeOfOneArea, bfs(picture,m,n,new Pair(i,j)));
	    			  numberOfArea++;
	    		  }
	    	  }
	      }

	      int[] answer = new int[2];
	      answer[0] = numberOfArea;
	      answer[1] = maxSizeOfOneArea;
	      return answer;
	  }

	public static int bfs(int[][] picture,int m,int n,Pair p) {
		int result=0;
		Queue<Pair> q=new LinkedList<Pair>();
		q.add(p);
		check[p.y][p.x]=1;
		result++;
		while(!q.isEmpty()) {
			Pair now=q.remove();
			for(int i=0;i<4;i++) {
				int nexty=now.y+dy[i];
				int nextx=now.x+dx[i];
				if(0<=nexty&&nexty<m&&0<=nextx&&nextx<n&&picture[nexty][nextx]==picture[p.y][p.x]&&check[nexty][nextx]==0) {
					q.add(new Pair(nexty,nextx));
					check[nexty][nextx]=1;
					result++;
				}
			}
		}

		return result;
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
