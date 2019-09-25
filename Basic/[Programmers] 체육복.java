/* https://programmers.co.kr/learn/courses/30/lessons/42862 */
//suit 1로 초기화 후,
//lost 요소 -1, reserve 요소 +1 해서 초기 suit배열과 수업가능 학생 answer를 완성한다.
//이후 reserve요소 탐색하면서 좌우 순으로 suit이 0이면 자기거 하나 준다.
//하나 줄 때마다 answer++ㅉ

import java.util.*;

public class Solution {
	public static int[] move= {-1,1};

	public static void main(String[] args) {
		int n=5;
		int[] lost= {2, 4};
		int[] reserve= {1, 3, 5};
		System.out.println(solution(n,lost,reserve));
	}

	public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] suit=new int[n+1];
        Arrays.fill(suit, 1);
        for(int i=0;i<lost.length;i++) {
        	suit[lost[i]]=0;
        	answer--;
        }
        for(int i=0;i<reserve.length;i++) {
        	suit[reserve[i]]++;
        	if(suit[reserve[i]]==1)
        		answer++;
        }

        for(int i=0;i<reserve.length;i++) {
        	if(suit[reserve[i]]==2) {
        		for(int j=0;j<2;j++) {
        			int next=reserve[i]+move[j];
        			if(1<=next&&next<=n&&suit[next]==0) {
        				suit[next]++;
        				suit[reserve[i]]--;
        				answer++;
        				break;
        			}
        		}
        	}
        }



        return answer;
    }
}
