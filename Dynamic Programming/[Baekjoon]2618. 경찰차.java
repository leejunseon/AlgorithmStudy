/* https://www.acmicpc.net/problem/2618 */

import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int w;
	static int[][] cache;
	static Pair[] Incident;
	static int[] Dist;
	static int max=987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=null;

		n=Integer.parseInt(br.readLine());//n입력
		w=Integer.parseInt(br.readLine());//w입력
		cache=new int[w+1][w+1];
		for(int j=0;j<=w;j++) {
			Arrays.fill(cache[j], -1);
		}

		Incident=new Pair[w+1];
		for(int i=0;i<w;i++) {
			st=new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			Incident[i]=new Pair(y,x);
		}

		System.out.println(minDistance(0,0));
		reconstruct(0,0,0);
	}

	public static int dist(Pair start,Pair end) {
		//두 점간 거리 구하는 메소드
		return Math.abs(start.y-end.y)+Math.abs(start.x-end.x);
	}

	public static int minDistance(int Police1, int Police2) {
		if(Police1==w||Police2==w)
			return 0;

		if(cache[Police1][Police2]!=-1)
			return cache[Police1][Police2];

		int next=Math.max(Police1, Police2)+1;
		int nextone=0;//1을 선택할 시 1이 움직여야 할 거리
		int nexttwo=0;//2를 선택할 시 2가 움직여야 할 거리
		if(Police1==0)
			nextone=dist(new Pair(1,1),Incident[next-1]);//(1,1)에서 다음 사건까지의 거리
		else
			nextone=dist(Incident[Police1-1],Incident[next-1]);//Police1위치에서 다음 사건까지의 거리
		if(Police2==0)
			nexttwo=dist(new Pair(n,n),Incident[next-1]);//(n,n)에서 다음 사건까지의 거리
		else
			nexttwo=dist(Incident[Police2-1],Incident[next-1]);//Police2위치에서 다음 사건까지의 거리

		cache[Police1][Police2]=max;
		cache[Police1][Police2]=Math.min(cache[Police1][Police2],minDistance(next,Police2)+nextone);//1을 선택했을 경우
		cache[Police1][Police2]=Math.min(cache[Police1][Police2],minDistance(Police1,next)+nexttwo);//2를 선택했을 경우
		return cache[Police1][Police2];
	}

	public static void reconstruct(int num,int Police1,int Police2) {
		if(num==w)
			return;

		int next=Math.max(Police1, Police2)+1;
		int nextone=0;//1을 선택할 시 1이 움직여야 할 거리
		int nexttwo=0;//2를 선택할 시 2가 움직여야 할 거리
		if(Police1==0)
			nextone=dist(new Pair(1,1),Incident[next-1]);//(1,1)에서 다음 사건까지의 거리
		else
			nextone=dist(Incident[Police1-1],Incident[next-1]);//Police1위치에서 다음 사건까지의 거리
		if(Police2==0)
			nexttwo=dist(new Pair(n,n),Incident[next-1]);//(n,n)에서 다음 사건까지의 거리
		else
			nexttwo=dist(Incident[Police2-1],Incident[next-1]);//Police2위치에서 다음 사건까지의 거리

		if(minDistance(next,Police2)+nextone>=minDistance(Police1,next)+nexttwo) {
			System.out.println(2);
			reconstruct(num+1,Police1,next);
		}else {
			System.out.println(1);
			reconstruct(num+1,next,Police2);
		}
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
