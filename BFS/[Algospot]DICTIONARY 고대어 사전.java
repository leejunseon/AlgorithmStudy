/* https://algospot.com/judge/problem/read/DICTIONARY */
//bfs,우선순위 큐로 위상정렬 구현

//위상 정렬(Topological Sort) 구현 방식(BFS를 기준으로 설명)
//1. 이 그래프가 사이클이 있는지 확인한다.
//(보통의 경우 문제를 해결하는 경우에는 이 과정이 있다면 위상 정렬 자체를 시도하지 않을 것이지만)
//2. 현재 정점에 들어오는 간선이 없다면 BFS라면 큐에 담아준다.
//3. 큐에서 front원소를 빼고 front에 연결되어있는 정점들 간의 간선을 모두 삭제해준다.
//이때 해당하는 다음 정점에 들어오는 간선이 없다면 큐에 담아준다.
//이 과정을 정점의 개수만큼 반복한다.
//4. 결국 이 과정을 반복하는 동안 큐에서 빠지는 front 순서대로가 위상 정렬의 결과이다.


import java.util.*;
import java.io.*;

public class practice {
	public static List<ArrayList<Integer>> Graph;
	public static int[] indegree;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		while(n-->0) {
			int m=Integer.parseInt(br.readLine());
			List<String> words=new ArrayList<String>();
			indegree=new int[26];
			Graph=new ArrayList<ArrayList<Integer>>();
			for(int i=0;i<26;i++)
				Graph.add(new ArrayList<Integer>());
			for(int i=0;i<m;i++) {
				words.add(br.readLine());
			}
			List<Integer> result=ts(words);
			if(result.size()!=26)
				System.out.println("INVALID HYPOTHESIS");
			else {
				for(int i:result)
					System.out.print((char)(i+'a'));
			}
			System.out.println();
		}
	}

	public static List<Integer> ts(List<String> words){
		for(int i=1;i<words.size();i++) {
			String end=words.get(i);
			String start=words.get(i-1);
			int min=Math.min(start.length(), end.length());
			for(int j=0;j<min;j++) {
				if(start.charAt(j)!=end.charAt(j)) {
					Graph.get(start.charAt(j)-'a').add(end.charAt(j)-'a');
					indegree[end.charAt(j)-'a']++;
					break;
				}
			}
		}

		return bfs();
	}

	public static List<Integer> bfs() {
		List<Integer> result=new ArrayList<Integer>();
		Queue<Integer> pq=new PriorityQueue<Integer>();
		for(int i=0;i<26;i++) {
			if(indegree[i]==0)
				pq.add(i);
		}

		while(!pq.isEmpty()) {
			int now=pq.remove();
			result.add(now);
			for(int next:Graph.get(now)) {
				indegree[next]--;
				if(indegree[next]==0)
					pq.add(next);
			}
		}

		return result;
	}
}
