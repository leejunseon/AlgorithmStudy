/* https://programmers.co.kr/learn/courses/30/lessons/42897 */

//0 번째 집을 털었을 때
//기저사례 : 0번째까지의 최대값 : 0번째 집 값 / 1번째까지의 최대값 : 0번째 집 값 (인접한 집 털 수 없으므로)

//0 번째 집을 안털었을 때
//기저사례 : 0번째까지의 최대값 : 0 / 1번째까지의 최대값 : 1번째 집 값

//n번째 까지의 최대값 = Math.max(n-2까지의 최대값 + 현재 값, n-1까지의 최대값)


//처음 풀 때 점화식을 복잡하게 짰었음. 일반 테스트케이스는 모두 풀렸지만 효율성 테스트케이스에서 계속 실패뜸.
//밑의 bottomup코드는 다 해결되는데 같은 로직의 tombottom코드는 왜 효율성 테스트케이스가 다 실패뜨는지 모르겠음..

import java.util.*;

public class Solution {
	public static int[][] cache;
	public static int size;

	public static void main(String[] args) {
		int[] money= {1,6,3,4,2,5};
		System.out.println(solution(money));
	}

	public static int solution(int[] money) {
		cache=new int[2][money.length];
		for(int i=0;i<2;i++)
			Arrays.fill(cache[i], -1);

		cache[0][0]=money[0];
		cache[0][1]=money[0];
		for(int i=2;i<money.length-1;i++)
			cache[0][i]=Math.max(cache[0][i-1], cache[0][i-2]+money[i]);

		cache[1][0]=0;
		cache[1][1]=money[1];
		for(int i=2;i<money.length;i++)
			cache[1][i]=Math.max(cache[1][i-1], cache[1][i-2]+money[i]);

		return Math.max(cache[0][money.length-2], cache[1][money.length-1]);
		//return Math.max(getMax(money,0,money.length-2), getMax(money,1,money.length-1));

	}


	/*
	 * topbottom 코드
	public static int getMax(int[] money,int flag,int start) {
		switch(flag) {
		case 0://첫집 o -> 막집 x
			if(start==0)
				return money[0];
			if(start==1)
				return money[0];

			if(cache[flag][start]!=-1)
				return cache[flag][start];

			cache[flag][start]=0;
			cache[flag][start]=Math.max(cache[flag][start], getMax(money,flag,start-1));
			cache[flag][start]=Math.max(cache[flag][start], getMax(money,flag,start-2)+money[start]);
			break;
		case 1://첫집 x -> 막집 o
			if(start==0)
				return 0;
			if(start==1)
				return money[1];

			if(cache[flag][start]!=-1)
				return cache[flag][start];

			cache[flag][start]=0;
			cache[flag][start]=Math.max(cache[flag][start], getMax(money,flag,start-1));
			cache[flag][start]=Math.max(cache[flag][start], getMax(money,flag,start-2)+money[start]);
			break;
		}

		return cache[flag][start];
	}*/
}
