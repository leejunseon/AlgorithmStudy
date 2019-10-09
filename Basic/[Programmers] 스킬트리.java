/* https://programmers.co.kr/learn/courses/30/lessons/49993 */
//skill에 속해있다면, 스킬순서에 맞는지 확인, 순서에 맞으면 map에 체크
//순서에 안맞으면 break
//순서에 맞으면 count

import java.io.*;
import java.util.*;

public class Solution {
	public static Map<Character,Integer> map;

	public static void main(String[] args) {
		String skill="CBD";
		String[] skill_trees= {"BACDE", "CBADF", "AECB", "BDA","ZVC"};
		System.out.println(solution(skill,skill_trees));
	}

	public static int solution(String skill, String[] skill_trees) {
		int answer=0;
		map=new HashMap<Character,Integer>();

		for(int i=0;i<skill_trees.length;i++) {
			for(int j=0;j<skill.length();j++) {
				map.put(skill.charAt(j),0);
			}
			boolean flag=true;
			for(int j=0;j<skill_trees[i].length();j++) {
				char now=skill_trees[i].charAt(j);
				if(map.containsKey(now)) {
					for(int k=0;k<skill.indexOf(now);k++) {
						if(map.get(skill.charAt(k))!=1){
							flag=false;
							break;
						}
					}
					if(!flag)
						break;
					else
						map.put(now, map.get(now)+1);
				}
			}
			if(flag)
				answer++;
		}

		return answer;
	}

	//skill_trees에서 skill이 아닌 스킬 모두 빼고,
	//남은 글자가 skill에 속해있고, 첫글자부터 포함한다면 count
	//정규식 사용
	/*
	public static int solution(String skill, String[] skill_trees) {
        int answer = 0;
        ArrayList<String> skillTrees = new ArrayList<String>(Arrays.asList(skill_trees));
        Iterator<String> it = skillTrees.iterator();

        while (it.hasNext()) {
        	String s=it.next().replaceAll("[^" + skill + "]", "");
            if (skill.indexOf(s) != 0) {
                it.remove();
            }
        }
        answer = skillTrees.size();
        return answer;
    }
	*/
}
