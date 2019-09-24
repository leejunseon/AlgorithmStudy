/* https://programmers.co.kr/learn/courses/30/lessons/42585 */
//'('나오면 stack에 push
//'()'나오면 answer+=stack사이즈
//')'나오면 stack pop, answer+=1

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String arrangement="()(((()())(())()))(())";
		System.out.println(solution(arrangement));
	}

	public static int solution(String arrangement) {
        int answer = 0;
        Stack<Character> st=new Stack<Character>();
        for(int i=0;i<arrangement.length();i++) {
        	if(i+2<arrangement.length()&&arrangement.substring(i,i+2).equals("()")){
        		answer+=st.size();
        		i++;
        	}else {
        		if(arrangement.charAt(i)=='(')
        			st.push('(');
        		else {
        			st.pop();
        			answer++;
        		}
        	}
        }

        return answer;
    }
}
