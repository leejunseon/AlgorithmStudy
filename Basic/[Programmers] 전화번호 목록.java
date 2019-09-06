/* https://programmers.co.kr/learn/courses/30/lessons/42577# */
//사전순으로 배열
//첫번째 요소부터 size-1번째 요소까지 차례로 탐색하면서
//다음요소보다 길이가 작을 경우 -> 다음 요소가 현재요소로 시작되는 문자열인지 파악 (접두사인지 파악하는 과정)
//접두사 확인되면 break, false 반환

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String[] phone_book= {"119","97674223","1195524421"};
		System.out.println(solution(phone_book));
	}
	
    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Arrays.sort(phone_book,new Comparator<String>() {

			@Override
			public int compare(String arg0, String arg1) {
				// TODO Auto-generated method stub
				return arg0.compareTo(arg1);
			}
        	
        });
        
        for(int i=0;i<phone_book.length-1;i++) {
        	int now=phone_book[i].length();
        	int next=phone_book[i+1].length();
        	if(now<next) {
        		if(phone_book[i+1].startsWith(phone_book[i])) {
        			answer=false;
        			break;
        		}
        	}
        }
        
        return answer;
    }
}
