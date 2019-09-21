/* https://programmers.co.kr/learn/courses/30/lessons/49189 */

//edge 내용대로 graph만들고
//bfs탐색하면서 최대거리 찾은 다음
//최대거리인 노드 세어서 반환
//n은 노드의 갯수인데 간선의 갯수로 헷갈려서 삽질했음. 문제 똑바로 읽을 것.

import java.util.*;

public class Solution {
	public static ArrayList<ArrayList<Integer>> Graph;
	public static int[] check;
	public static int[] distance;
	public static int max;

	public static void main(String[] args) {
		int[][] edge= {{1,1},{1,2},{2,3}};
		System.out.println(solution(3,edge));
	}

	public static int solution(int n, int[][] edge) {
		Graph=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<=n;i++)
        	Graph.add(new ArrayList<Integer>());
        for(int i=0;i<edge.length;i++) {
        	int start=edge[i][0];
        	int end=edge[i][1];
        	Graph.get(start).add(end);
        	Graph.get(end).add(start);
        }
        check=new int[n+1];
        distance=new int[n+1];

        getMax(1);

        int count=0;
        for(int i=1;i<=n;i++) {
        	if(distance[i]==max)
        		count++;
        }
        return count;
    }

	public static void getMax(int start) {
		Queue<Integer> q=new LinkedList<Integer>();
		q.add(start);
		check[start]=1;
		while(!q.isEmpty()) {
			int now=q.remove();
			for(int next:Graph.get(now)) {
				if(check[next]==0) {
					q.add(next);
					check[next]=1;
					distance[next]=distance[now]+1;
					max=Math.max(max, distance[next]);
				}
			}
		}
	}
}
