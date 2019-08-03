/* https://www.acmicpc.net/problem/14889 */

import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public final static int max=987654321;
	public static int[][] stat;
	public static List<Integer> team;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		stat=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				stat[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int result=max;
		for(int i=1;i<=n-n/2+1;i++) {
			team=new ArrayList<Integer>();
			team.add(i);
			result=Math.min(result, sub(i,team));
		}
		
		System.out.println(result);
	}
	
	public static int sub(int now,List<Integer> one) {
		int result=max;
		
		if(one.size()==n/2) {//ÆÀ ´Ù Ã¡À¸¸é
			int team1=calcStat(one);
			List<Integer> two=new ArrayList<Integer>();
			for(int i=1;i<=n;i++) {
				if(!one.contains(i)) {
					two.add(i);
				}
			}
			int team2=calcStat(two);
			
			result=Math.abs(team1-team2);
		}else {
			for(int i=now+1;i<=n;i++) {
				one.add(i);
				result=Math.min(result, sub(i,one));
				one.remove(one.size()-1);
			}
		}		
		
		return result;
	}
	
	public static int calcStat(List<Integer> list) {
		int result=0;
		
		for(int i=0;i<list.size();i++) {
			int a=list.get(i);
			for(int j=0;j<list.size();j++) {
				int b=list.get(j);
				if(a==b)
					continue;
				result+=stat[a][b];
			}
		}
		
		return result;
	}
}