/* https://programmers.co.kr/learn/courses/30/lessons/42896 */
//왼쪽 or 둘다 버리는 경우 : +0
//왼쪽이 오른쪽보다 클 때 -> 오른쪽만 버리고, +오른쪽 카드 값
//각 상황에서 선택할 수 있는 경우의 수 : 3가지(왼쪽/둘다/오른쪽)
//3가지 선택 경우의 수 모두 탐색하며 이미 탐색한 곳은 다시 가지 않기 -> cache배열 이용한 memoization
//전체 탐색 중 max값 반환.(기저사례 = 한쪽 카드 다썼을 때)

import java.util.*;

public class Solution {
	public static int[][] cache;

	public static void main(String[] args) {
		int[] left= {3,2,5};
		int[] right= {2,4,1};
		System.out.println(solution(left,right));
	}

	public static int solution(int[] left, int[] right) {
		cache=new int[left.length][right.length];
		for(int i=0;i<cache.length;i++)
			Arrays.fill(cache[i], -1);
        return getMax(left,right,0,0);
    }

	public static int getMax(int[] left,int[] right,int l,int r) {
		if(l>=left.length||r>=right.length)
			return 0;

		if(cache[l][r]!=-1)
			return cache[l][r];

		cache[l][r]=0;
		cache[l][r]=Math.max(cache[l][r], getMax(left,right,l+1,r));
		cache[l][r]=Math.max(cache[l][r], getMax(left,right,l+1,r+1));
		if(left[l]>right[r])
			cache[l][r]=Math.max(cache[l][r], getMax(left,right,l,r+1)+right[r]);

		return cache[l][r];
	}
}
