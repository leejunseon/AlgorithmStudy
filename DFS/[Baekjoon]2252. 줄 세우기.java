/* https://www.acmicpc.net/problem/2252 */
//순서 찾는 문제 -> 위상정렬 고려해보기

import java.util.*;
import java.io.*;

public class Main {
	public static List<ArrayList<Integer>> Graph;
	public static List<Integer> result;
	public static int[] check;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Graph=new ArrayList<ArrayList<Integer>>();
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());

		for(int i=0;i<=n;i++)
			Graph.add(new ArrayList<Integer>());
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			Graph.get(start).add(end);
		}

		result=new ArrayList<Integer>();
		check=new int[n+1];
		ts();

		for(int i:result)
			System.out.println(i);
	}

	public static void ts() {
		for(int i=1;i<Graph.size();i++) {
			if(check[i]==0) {
				dfs(i);
			}
		}
		Collections.reverse(result);
	}

	public static void dfs(int start) {
		check[start]=1;
		for(int i:Graph.get(start)) {
			if(check[i]==0)
				dfs(i);
		}
		result.add(start);
	}

}
