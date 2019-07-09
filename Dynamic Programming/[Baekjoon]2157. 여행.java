/* https://www.acmicpc.net/problem/2157 */
import java.util.*;

public class Main {
	static ArrayList<ArrayList<Pair>> Graph;
	static int n;
	static int m;
	static int k;
	static int[][] cache;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		m=sc.nextInt();
		k=sc.nextInt();
		Graph=new ArrayList<ArrayList<Pair>>();
		for(int i=0;i<=n;i++)
			Graph.add(new ArrayList<Pair>());
		cache=new int[n+1][m+1];
		for(int i=0;i<=n;i++)
			Arrays.fill(cache[i], -1);
		for(int i=0;i<k;i++) {
			int start=sc.nextInt();
			int next=sc.nextInt();
			int score=sc.nextInt();
			Graph.get(start).add(new Pair(next,score));
		}
		System.out.println(MaxScore(1,1));
	}
	
	public static int MaxScore(int city,int num) {
		if(city==n)
			return 0;
		if(num==m)
			return Integer.MIN_VALUE;
		
		if(cache[city][num]!=-1)
			return cache[city][num];
		
		cache[city][num]=0;
		for(Pair p:Graph.get(city)) {
			if(p.next>city)
				cache[city][num]=Math.max(cache[city][num],MaxScore(p.next,num+1)+p.score);
		}
		
		return cache[city][num];
	}
}

class Pair{
	int next;
	int score;
	Pair(int next,int score){
		this.next=next;
		this.score=score;
	}
}