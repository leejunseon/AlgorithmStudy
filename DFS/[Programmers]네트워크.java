/* https://programmers.co.kr/learn/courses/30/lessons/43162 */
//주어진 computers를 바탕으로 Graph만듦(computers[i][j]==1 이면 i와 j 연결)
//Graph의 연결요소 갯수 return (dfs 사용했음. bfs사용해도 무방)

import java.util.*;

public class Solution {
	public static ArrayList<ArrayList<Integer>> Graph;
	public static int[] check;

	public static void main(String[] args) {
		int[][] computers= {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		System.out.println(solution(3,computers));
	}

	public static int solution(int n, int[][] computers) {
		int answer=0;
        Graph=new ArrayList<ArrayList<Integer>>();
        check=new int[n];
        for(int i=0;i<n;i++) {
        	Graph.add(new ArrayList<Integer>());
        }
        for(int i=0;i<n;i++) {
        	for(int j=0;j<n;j++) {
        		if(i==j)
        			continue;
        		if(computers[i][j]==1)
        			Graph.get(i).add(j);
        	}
        }

        for(int i=0;i<n;i++) {
        	if(check[i]==0) {
        		answer++;
        		dfs(i);
        	}
        }

        return answer;
    }

	public static void dfs(int n) {
		check[n]=1;
		for(int next:Graph.get(n)) {
			if(check[next]==0)
				dfs(next);
		}
	}
}
