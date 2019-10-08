/* https://programmers.co.kr/learn/courses/30/lessons/43237 */
//budgets 오름차순 정렬
//부분합 정렬 sum 선언
//budgets에서 이분탐색 실행
//if (max - now까지의 부분합)/나머지 지역 갯수 < budgets[now+1]
//l ~ now 이분탐색
//else
//now+1 ~ r 이분탐색

import java.io.*;
import java.util.*;

public class Solution {
	public static int max;
	public static int[] sum;

	public static void main(String[] args) {
		int[] budgets= {9,8,5,6,7};
		int M=5;
		System.out.println(solution(budgets,M));
	}

	public static int solution(int[] budgets, int M) {
		max=M;
        Arrays.sort(budgets);
        sum=new int[budgets.length];
        sum[0]=budgets[0];
        for(int i=1;i<budgets.length;i++) {
        	sum[i]=sum[i-1]+budgets[i];
        }

        return findAnswer(budgets,0,budgets.length-1);
    }

	public static int findAnswer(int[] budgets,int l,int r) {
		int now=(r+l)/2;//현재 검사할 위치
		int locs=budgets.length-now-1;//그다음 남은 지역
		int remain=max-sum[now];//남은 금액

		if(l==r) {
			if(r==0)
				return max/budgets.length;
			if(locs==0)
				return budgets[budgets.length-1];
			else
				return remain/locs;
		}

		if(remain/locs<budgets[now+1]) {
			return findAnswer(budgets,l,now);
		}else {
			return findAnswer(budgets,now+1,r);
		}
	}
}
