/* https://programmers.co.kr/learn/courses/30/lessons/43236 */
//거리의 최소값을 start=0, end=distance 로 두고 이분탐색
//mid를 거리의 최소값이라 하고, rocks에서 mid보다 작은 돌 있으면 제거, count
//count가 n보다 많으면 end=mid-1,
//아니면 start=mid+1

import java.io.*;
import java.util.*;

public class Solution {
	public static int Distance;
	public static int num;

	public static void main(String[] args) {
		int distance=25;
		int[] rocks= {2,14,11,21,17};
		int n=2;
		System.out.println(solution(distance,rocks,n));
	}

	public static int solution(int distance, int[] rocks, int n) {
		int answer=0;
        Arrays.sort(rocks);

        int start=0;
        int end=distance;

        while(start<=end) {
        	int mid=(start+end)/2;
        	int dist=0;
            int removeNum=0;
            int prev=0;

        	for(int i=0;i<=rocks.length;i++) {
        		dist=i!=rocks.length?rocks[i]-prev:distance-rocks[i-1];
        		if(dist<mid)
        			removeNum++;
        		else if(i!=rocks.length)
        			prev=rocks[i];
        	}

        	if(removeNum>n)
        		end=mid-1;
        	else {
        		start=mid+1;
        		answer=mid;
        	}
        }

        return answer;
    }
}
