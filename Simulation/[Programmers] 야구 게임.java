/* https://programmers.co.kr/learn/courses/30/lessons/42841 */
//list에 123부터 987까지 각 자리의 수가 서로 다른 세자리 수 모두 넣고
//하나씩 baseball조건과 비교(strike,ball 값)

import java.util.*;

public class Solution {
	public static List<Integer> list;

	public static void main(String[] args) {
		int[][] baseball= {{123, 1, 1}, {356, 1, 0}, {327, 2, 0}, {489, 0, 1}};
		System.out.println(solution(baseball));
	}

	public static int solution(int[][] baseball) {
        int answer = 0;
        list=new ArrayList<Integer>();
        //서로 다른 수 집어넣기
        for(int i=1;i<=9;i++) {
        	for(int j=1;j<=9;j++) {
        		if(j==i)
        			continue;
        		for(int k=1;k<=9;k++) {
        			if(k==i||k==j)
        				continue;
        			list.add(100*i+10*j+k);
        		}
        	}
        }

        for(int i=0;i<list.size();i++) {
        	int number=list.get(i);
        	boolean flag=true;
        	for(int j=0;j<baseball.length;j++) {
        		int compare=baseball[j][0];
        		int strike=baseball[j][1];
        		int ball=baseball[j][2];
        		if(strike!=getStrike(number,compare)||ball!=getBall(number,compare)) {
        			flag=false;
        			break;
        		}
        	}
        	if(flag)
        		answer++;
        }

        return answer;
    }

	public static int getStrike(int number,int compare) {
		int answer=0;
		String num=Integer.toString(number);
		String com=Integer.toString(compare);

		for(int i=0;i<3;i++) {
			if(num.charAt(i)==com.charAt(i))
				answer++;
		}

		return answer;
	}

	public static int getBall(int number,int compare) {
		int answer=0;
		String num=Integer.toString(number);
		String com=Integer.toString(compare);

		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(i==j)
					continue;
				if(num.charAt(i)==com.charAt(j)) {
					answer++;
					break;
				}
			}
		}

		return answer;
	}
}
