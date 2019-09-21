/* https://programmers.co.kr/learn/courses/30/lessons/49191 */

//results 토대로 Graph만들고,
//indegree=0인 노드를 bfs 탐색한다.  탐색 할 때마다 해당 노드 indegree--
//탐색하면서 winlose 업데이트
//now가 next를 이기면,
//now가 졌던 선수들은 모두 next를 이기고,
//next는 now가 졌던 선수들에게 모두 진다.


import java.util.*;

public class Solution {
	public static ArrayList<ArrayList<Integer>> Graph;
	public static HashSet<Integer>[][] winlose;
	public static int[] indegree;

	public static void main(String[] args) {
		int[][] results= {{1,2},{2,3},{3,4},{4,5}};
		System.out.println(solution(5,results));
	}

	//n:전체 선수 명
	//result:결과 배열
	public static int solution(int n, int[][] results) {
        int answer = 0;
        Graph=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<=n;i++) {
        	Graph.add(new ArrayList<Integer>());
        }

        indegree=new int[n+1];
        for(int i=0;i<results.length;i++) {
        	int start=results[i][0];
        	int end=results[i][1];
        	Graph.get(start).add(end);
        	indegree[end]++;
        }

        winlose=new HashSet[n+1][2];
        for(int i=1;i<=n;i++) {
        	for(int j=0;j<2;j++) {
        		winlose[i][j]=new HashSet<Integer>();
        	}
        }

        bfs(n);

        for(int i=1;i<=n;i++) {
        	if(winlose[i][0].size()+winlose[i][1].size()==n-1) {
        		answer++;
        	}
        }

        return answer;
    }

	public static void bfs(int n) {
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0)
				q.add(i);
		}

		while(!q.isEmpty()) {
			int now=q.remove();
			for(int next:Graph.get(now)) {
				indegree[next]--;
				winlose[now][0].add(next);//now가 next 이김
				winlose[next][1].add(now);//next가 now한테 짐
				if(!winlose[now][1].isEmpty()) {
					for(int lose:winlose[now][1]) {
						winlose[next][1].add(lose);//next를 이겼으면, next는 now가 진 선수에게 다 짐
						winlose[lose][0].add(next);//next를 이겼으면, now가 진 선수는 모두 next를 이김
					}
				}
				if(indegree[next]==0)
					q.add(next);
			}
		}
	}
}
