/* https://www.acmicpc.net/problem/1766 */
//bfs로 위상정렬 구현

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int m;
	public static List<ArrayList<Integer>> Graph;
	public static int[] in;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		Graph=new ArrayList<ArrayList<Integer>>();
		in=new int[n+1];
		for(int i=0;i<=n;i++)
			Graph.add(new ArrayList<Integer>());
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			Graph.get(start).add(end);
			in[end]++;
		}

		ts();
	}

	public static void ts() {
		PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
		for(int i=1;i<=n;i++) {
			if(in[i]==0)
				pq.add(i);
		}

		while(!pq.isEmpty()) {
			int now=pq.remove();
			System.out.println(now);
			for(int next:Graph.get(now)) {
				in[next]--;
				if(in[next]==0) {
					pq.add(next);
				}
			}
		}
	}
}
