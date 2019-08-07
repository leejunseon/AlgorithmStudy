/* https://www.acmicpc.net/problem/15686 */

import java.util.*;
import java.io.*;

public class Main {
	public static final int max=987654321;
	public static int[] chickenmap;
	public static List<Pair> houses;
	public static List<Pair> chickens;
	public static int m;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());//지도의 한 변 크기
		m=Integer.parseInt(st.nextToken());//고를 치킨집 수
		houses=new ArrayList<Pair>();
		chickens=new ArrayList<Pair>();
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				int input=Integer.parseInt(st.nextToken());
				if(input==1)
					houses.add(new Pair(i,j));
				else if(input==2)
					chickens.add(new Pair(i,j));
			}
		}
		
		chickenmap=new int[chickens.size()];
		System.out.println(min(0,chickenmap,-1));
	}
	
	public static int min(int num,int[] map,int last) {
		int[] copy=map.clone();
		int result=max;
		
		//치킨거리 계산
		if(num!=0) {
			int distance=0;
			for(int i=0;i<houses.size();i++) {
				int dist=max;
				for(int j=0;j<chickens.size();j++) {
					if(copy[j]==1) {
						dist=Math.min(dist,distance(houses.get(i), chickens.get(j)));
					}
				}
				distance+=dist;
			}
			result=Math.min(result, distance);
		}
		
		if(num<m) {
			for(int i=last+1;i<chickens.size();i++) {
				copy[i]=1;
				result=Math.min(result, min(num+1,copy,i));
				copy[i]=0;
			}
		}
		
		return result;
	}
	
	public static int distance(Pair a,Pair b) {
		return Math.abs(a.x-b.x)+Math.abs(a.y-b.y);
	}
}

class Pair{
	int y;
	int x;
	Pair(int y,int x){
		this.y=y;
		this.x=x;
	}
}