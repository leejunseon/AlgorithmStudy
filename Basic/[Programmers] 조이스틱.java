/* https://programmers.co.kr/learn/courses/30/lessons/42860 */

//name의 0번 자리부터 시작해서
//A가 아닌 글자중 가장 가까운 것부터 처리. -> 탐욕법

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String name="AABAAAAAAABBB";
		System.out.println(solution(name));
	}

	public static int solution(String name) {
		int answer = 0;
        int remain=0;
        for(int i=0;i<name.length();i++) {
        	if(name.charAt(i)!='A') {
        		remain++;
        	}
        }

        int now=0;
        while(remain!=0) {
        	if(name.charAt(now)!='A') {
	        	answer+=shortest('A',name.charAt(now));
	        	remain--;
	        	StringBuilder sb=new StringBuilder(name);
	        	sb.setCharAt(now, 'A');
	        	name=sb.toString();
        	}
        	if(remain!=0) {
        		int[] res=getNext(now,name);
        		int next=res[0];
        		answer+=res[1];
        		now=next;
        	}
        }

        return answer;
    }

	//start글자와 end글자 사이의 거리 구하는 함수
	public static int shortest(char start,char end) {
		int dist=Math.abs((int)start-(int)end);
		if(dist<=13) {
			return dist;
		}else{
			char first=(char)Math.min((int)start, (int)end);
			char last=(char)Math.max((int)start, (int)end);
			return ((int)first-65)+(90-(int)last+1);
		}
	}

	//다음 가장 가까운 위치와 움직인 거리 반환
	public static int[] getNext(int now,String name) {
		int front=0;
		int rear=0;

		int index=now;
		while(true) {
			if(index!=name.length()-1)
				index++;
			else
				index=0;

			front++;
			if(name.charAt(index)!='A')
				break;
		}

		index=now;
		while(true) {
			if(index!=0)
				index--;
			else
				index=name.length()-1;

			rear++;
			if(name.charAt(index)!='A')
				break;
		}

		int[] answer=new int[2];
		if(rear>=front) {
			answer[0]=(now+front)%name.length();
			answer[1]=front;
		}else {
			answer[0]=((now-rear)<0)?now-rear+name.length():now-rear;
			answer[1]=rear;
		}
		return answer;
	}
}
