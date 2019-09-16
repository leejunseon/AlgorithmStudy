/* https://programmers.co.kr/learn/courses/30/lessons/42842 */
//red의 약수 중 red의 루트값보다 작은 수들 구하고
//그 수를 빨강구간의 세로, red를 그 수로 나눈 결과값을 빨강구간의 가로로 했을 때
//brown의 값이 주어진 brown과 같으면 그 경우의 카펫 크기가 정답

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int brown=24;
		int red=24;
		for(int i=0;i<2;i++) {
			System.out.println(solution(brown,red)[i]);
		}
	}

	public static int[] solution(int brown, int red) {
        int[] answer = new int[2];

        for(int i=1;i<=Math.sqrt(red);i++) {
        	if(red%i==0) {
        		int horizon=red/i;//빨강구간 가로
        		int vertical=i;//빨강구간 세로
        		if(brown==horizon*2+(vertical+2)*2) {
        			answer[0]=horizon+2;
        			answer[1]=vertical+2;
        			break;
        		}
        	}
        }

        return answer;
    }
}
