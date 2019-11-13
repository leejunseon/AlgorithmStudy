/* https://programmers.co.kr/learn/courses/30/lessons/17679 */

import java.util.*;
import java.io.*;

public class Solution {
	public static int m;
	public static int n;
	public static Pair[][] map;

	public static void main(String[] args) {
		String[] board={"CCBDE", "AAADE", "AAABF", "CCBBF"};
		System.out.println(solution(4,5,board));
	}

	public static int solution(int M, int N, String[] board) {
		m=M;
		n=N;
		map=new Pair[m][n];
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=new Pair(board[i].charAt(j),false);
			}
		}
	      return simulate();
	}

	public static int simulate() {
		int result=0;

		while(true) {
			int add=removeBlock();
			if(add==0)
				break;
			else
				result+=add;
			down();
			update();
		}

		return result;
	}

	public static int removeBlock() {
		int result=0;
		for(int i=0;i<m-1;i++) {
			for(int j=0;j<n-1;j++) {
				if(map[i][j].c==map[i][j+1].c&&
					map[i][j+1].c==map[i+1][j].c&&
					map[i+1][j].c==map[i+1][j+1].c) {
					if(map[i][j].remove==false) {
						map[i][j].remove=true;
						result++;
					}
					if(map[i][j+1].remove==false) {
						map[i][j+1].remove=true;
						result++;
					}
					if(map[i+1][j].remove==false) {
						map[i+1][j].remove=true;
						result++;
					}
					if(map[i+1][j+1].remove==false) {
						map[i+1][j+1].remove=true;
						result++;
					}
				}
			}
		}
		return result;
	}

	public static void down() {
		for(int i=m-1;i>=0;i--) {
			for(int j=0;j<n;j++) {
				if(map[i][j].remove==false) {
					int nexty=i;
					for(int k=i+1;k<m;k++) {
						if(map[k][j].remove==true)
							nexty++;
						else
							break;
					}
					if(nexty!=i) {
						map[nexty][j]=map[i][j].clone();
						map[i][j].remove=true;
					}
				}
			}
		}
	}

	public static void update() {
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j].remove==true) {
					map[i][j].c=0;
				}
			}
		}
	}
}

class Pair implements Cloneable{
	char c;
	boolean remove;
	Pair(char c,boolean remove){
		this.c=c;
		this.remove=remove;
	}

	@Override
	public Pair clone() {
		return new Pair(this.c,this.remove);
	}
}
