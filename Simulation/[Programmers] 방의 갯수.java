/* https://programmers.co.kr/learn/courses/30/lessons/49190 */

//오일러 다면체정리 사용
//v-e+f=x (꼭짓점 갯수 - 모서리 갯수 + 면 갯수 = 오일러 계수)
//2차원 평면에서 x는 항상 1.
//이 문제에서는 꼭짓점 갯수와 모서리 갯수만 구하면 됨.
//점 사이에 대각선으로 만날 경우 처리하기 위해 2번씩 이동
//모서리 진행방향이 겹치는 경우 중복 처리하기 위해 모서리 끝점 정렬 후 모서리 저장

//예외경우 찾아내는 게 힘들었음.

import java.util.*;

public class Solution {
	public static Set<String> edge;
	public static Set<String> vertex;
	public static int[] dy= {1,1,0,-1,-1,-1,0,1};
	public static int[] dx= {0,1,1,1,0,-1,-1,-1};

	public static void main(String[] args) {
		int[] arrows= {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0,0,6,0};
		System.out.println(solution(arrows));
	}

	public static int solution(int[] arrows) {
		edge=new HashSet<String>();
		vertex=new HashSet<String>();

		int y=0;
		int x=0;
		vertex.add(new Pair(y,x).toString());
		for(int i=0;i<arrows.length;i++) {
			for(int j=0;j<2;j++) {
				int ny=y+dy[arrows[i]];
				int nx=x+dx[arrows[i]];

				vertex.add(new Pair(ny,nx).toString());
				List<Pair> line=new ArrayList<Pair>();
				line.add(new Pair(y,x));
				line.add(new Pair(ny,nx));
				Collections.sort(line);
				edge.add(line.get(0).toString()+line.get(1).toString());

				y=ny;
				x=nx;
			}
		}

		return 1-vertex.size()+edge.size();

	}
}

class Pair implements Comparable<Pair>{
	int y;
	int x;
	Pair(int y,int x){
		this.y=y;
		this.x=x;
	}

	public String toString() {
		return Integer.toString(this.y)+","+Integer.toString(this.x)+"|";
	}

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		if(this.y!=o.y)
			return this.y-o.y;
		else
			return this.x-o.x;
	}

}
