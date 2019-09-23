/* https://programmers.co.kr/learn/courses/30/lessons/49188 */
//그래프에서 한길로 빠지는 부분 모두 제거(사이클에 영향을 미치지 않음)
//이후 간선과 노드 갯수 구해서 둘이 같으면 Graph의 전체 노드 더한 값 반환(모든 노드가 답이 될 수 있음)
//이후 사이클 제거할 수 있는 노드 구한 후 모두더한 값 리턴
//(노드 하나 except 설정 -> 그래프에 사이클 없어지면 answer에 +)

import java.util.*;

public class Solution {
	public static Map<Integer,Set<Integer>> Graph;
	public static int unable;
	public static int[] check;

	public static void main(String[] args) {
		int[][] edges= {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7},{7,8},{8,1},{2,7},{3,6},{8,9},{9,10}};
		System.out.println(solution(10,edges));
	}

	public static int solution(int n, int[][] edges) {
        int answer = 0;
        Graph=new HashMap<Integer,Set<Integer>>();
        for(int i=0;i<edges.length;i++) {
        	int start=edges[i][0];
        	int end=edges[i][1];
        	if(!Graph.containsKey(start))
        		Graph.put(start, new HashSet<Integer>());
        	Graph.get(start).add(end);
        	if(!Graph.containsKey(end))
        		Graph.put(end, new HashSet<Integer>());
        	Graph.get(end).add(start);
        }

        diet(n);

				//그래프 정리 후 edge==vertex면 어떤 노드 지워도 사이클 없앨 수 있음.(원형 그래프)
        int edge=0;
        int vertex=0;
        for(int i:Graph.keySet()) {
        	edge+=Graph.get(i).size();
        	vertex++;
        }
        if(vertex==edge/2) {
        	for(int i:Graph.keySet())
        		answer+=i;
        	return answer;
        }

        for(int i=1;i<=n;i++) {
        	unable=i;
        	if(!checkCycle(n,i))
        		answer+=i;
        }

        return answer;
    }

	//한쪽으로만 뻗쳐있는 노드들 다 지우기
		public static void diet(int n) {
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i:Graph.keySet()) {
			if(Graph.get(i).size()==1)
				q.add(i);
		}

		while(!q.isEmpty()) {
			int now=q.remove();
			Set<Integer> nextlist=Graph.remove(now);
			for(int next:nextlist) {
				Set<Integer> removable=Graph.get(next);
				removable.remove(now);
				if(removable.size()==1)
					q.add(next);
			}
		}
	}

	//그래프에 cycle이 있으면 true, 아니면 false 반환
	public static boolean checkCycle(int n,int except) {
		check=new int[n+1];
		for(int i:Graph.keySet()) {
			if(i!=except&&check[i]==0)
				if(cycle(i,0,except))
					return true;
		}
		return false;
	}

	public static boolean cycle(int start,int prev,int except) {
		if(check[start]!=0)
			return true;

		check[start]=1;
		for(int next:Graph.get(start)) {
			if(next!=prev&&next!=except) {
				if(cycle(next,start,except))
					return true;
			}
		}
		return false;
	}
}
