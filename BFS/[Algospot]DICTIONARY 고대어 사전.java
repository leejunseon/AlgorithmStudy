/* https://algospot.com/judge/problem/read/DICTIONARY */
//bfs,우선순위 큐로 위상정렬 구현

import java.util.*;
import java.io.*;

public class Main {
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
