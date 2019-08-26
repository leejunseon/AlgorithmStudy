/* https://www.acmicpc.net/problem/1516 */

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int[] time;
	public static int[] result;
	public static List<ArrayList<Integer>> Graph;
	public static int[] indegree;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Graph=new ArrayList<ArrayList<Integer>>();
		n=Integer.parseInt(br.readLine());
		time=new int[n+1];
		result=new int[n+1];
		indegree=new int[n+1];
		for(int i=0;i<=n;i++)
			Graph.add(new ArrayList<Integer>());
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			time[i]=Integer.parseInt(st.nextToken());
			while(true) {
				int now=i;
				int prev=Integer.parseInt(st.nextToken());
				if(prev==-1)
					break;
				else {
					Graph.get(prev).add(now);
					indegree[now]++;
				}
			}
		}

		ts();
		for(int i=1;i<=n;i++)
			System.out.println(result[i]);
	}

	public static void ts() {
		Queue<Integer> q=new LinkedList<Integer>();
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) {
				q.add(i);
				result[i]=time[i];
			}
		}

		while(!q.isEmpty()) {
			int now=q.remove();
			for(int next:Graph.get(now)) {
				indegree[next]--;
				result[next]=Math.max(result[next], result[now]+time[next]);
				if(indegree[next]==0) {
					q.add(next);
				}
			}
		}
	}
}
