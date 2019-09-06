/* https://programmers.co.kr/learn/courses/30/lessons/42577# */

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
