/* https://programmers.co.kr/learn/courses/30/lessons/42899 */

//각 상황에서 선택할 수 있는 경우의 수 : 도보, 자전거
//getMax(n,time)을 현재 time시간이고, n번구간부터 시작했을 때 얻을 수 있는 최대 모금액
//이라고 가정하면,
//getMax(n,time)=Math.max(getMax(n+1,time+도보시간)+도보모금액,getMax(n+1,time+자전거시간)+자전거모금액)
//이라고 할 수 있음
//위 점화식 토대로 재귀함수 구현.
//cache배열 이용해서 memoization (전체 구간, 전체 가능타임)

import java.util.*;

public class Solution {
	public static final int min=Integer.MIN_VALUE;
	public static int n;
	public static int[][] cache;
	public static int maxTime;

	public static void main(String[] args) {
		//[도보 이동에 걸리는 시간, 도보 이동 시 얻을 모금액, 자전거 이동에 걸리는 시간, 자전거 이동 시 얻을 모금액]
		int[][] travel= {{500, 200, 200, 100},
						 {800, 370, 300, 120},
						 {700, 250, 300, 90}};
		System.out.println(solution(1650,travel));
	}

	public static int solution(int K, int[][] travel) {
		n=travel.length;
		maxTime=K;
		cache=new int[n][1000001];
		for(int i=0;i<n;i++)
			Arrays.fill(cache[i], -1);
        return getMax(travel,0,0);
    }

	public static int getMax(int[][] travel,int now,int time) {
		if(time>maxTime)
			return min;
		if(now>=n)
			return 0;

		if(cache[now][time]!=-1)
			return cache[now][time];

		cache[now][time]=0;
		cache[now][time]=Math.max(cache[now][time], getMax(travel,now+1,time+travel[now][0])+travel[now][1]);//도보
		cache[now][time]=Math.max(cache[now][time], getMax(travel,now+1,time+travel[now][2])+travel[now][3]);//자전거

		return cache[now][time];
	}
}
