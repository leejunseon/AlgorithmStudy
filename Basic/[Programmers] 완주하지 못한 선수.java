/* https://programmers.co.kr/learn/courses/30/lessons/42576 */
//HashMap통해 참가인원 파악 -> 완주인원 빼고 남은인원 = answer

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String[] participant= {"mislav","stanko","mislav","ana"};
		String[] completion= {"stanko","ana","mislav"};
		System.out.println(solution(participant,completion));
	}
	
	 public static String solution(String[] participant, String[] completion) {
	        String answer = "";
	        
	        Map<String,Integer> map=new HashMap<String,Integer>();
	        for(int i=0;i<participant.length;i++) {
	        	if(map.get(participant[i])!=null) {
	        		map.put(participant[i], map.get(participant[i])+1);
	        	}else {
	        		map.put(participant[i], 1);
	        	}
	        }
	        
	        for(int i=0;i<completion.length;i++) {
	        	map.put(completion[i], map.get(completion[i])-1);
	        }
	        
	        for(String key:map.keySet()) {
	        	if(map.get(key)!=0) {
	        		answer=key;
	        		break;
	        	}
	        }
	        
	        return answer;
	 }
}
