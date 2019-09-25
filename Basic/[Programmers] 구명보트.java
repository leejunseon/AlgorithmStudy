/* https://programmers.co.kr/learn/courses/30/lessons/42885 */
//보트 하나에 탈 수 있는 인원이 최대 2명이므로
//무게가 가장 많이 나가는 사람, 가장 적게 나가는 사람
//태우면 가장 효율적
//만약 두 사람의 무게가 limit을 초과하면
//가장 많이 나가는 사람만 태워보냄

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] people= {70, 50, 80};
		int limit=100;
		System.out.println(solution(people,limit));
	}

	public static int solution(int[] people, int limit) {
        int answer = 0;
    	Arrays.sort(people);
    	int big=people.length-1;
    	int small=0;
    	while(small<=big) {
    		if(people[big]+people[small]>limit) {
    			big--;
    		}else {
    			big--;
    			small++;
    		}
    		answer++;
    	}

        return answer;
    }
}
