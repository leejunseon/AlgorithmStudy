/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */

import java.util.*;
import java.io.*;

public class Solution {
	public static List<String> Candidates;
	public static Set<String> set;
	
	public static void main(String[] args) {
		String[][] relation= {{"100","ryan","music","2"},
							{"200","apeach","math","2"},
							{"300","tube","computer","3"},
							{"400","con","computer","4"},
							{"500","muzi","music","3"},
							{"600","apeach","music","2"}};
		Candidates=new ArrayList<String>();
		System.out.println(solution(relation));
	}
	
	public static int solution(String[][] relation) {
        int answer = 0;
        
        for(int i=1;i<=relation[0].length;i++) {
        	int[] used=new int[relation[0].length];//후보키에 사용한 열
        	answer+=use(relation,used,i,-1,"");
        }
        
        return answer;
    }
	
	//relation 중 num개의 열 사용.
	public static int use(String[][] relation,int[] used,int num,int last,String key) {
		int answer=0;
		int[] copy=used.clone();
			
		if(num==0) {
			boolean check=check(relation,copy,key);
			if(check) {
				Candidates.add(key);
				return 1;
			}
			else
				return 0;
		}
		
		for(int i=last+1;i<copy.length;i++) {
			copy[i]=1;
			answer+=use(relation,copy,num-1,i,key+String.valueOf(i));
			copy[i]=0;
		}
		
		return answer;
	}
	
	//used열 사용했을 때 후보키 여부
	public static boolean check(String[][] relation,int[] used,String key) {
		for(String s:Candidates) {
			int n=0;
			for(int i=0;i<s.length();i++) {
				if(key.contains(Character.toString(s.charAt(i))))
					n++;
			}
			if(n==s.length())
				return false;
		}
		int row=relation.length;
		set=new HashSet<String>();
		for(int i=0;i<relation.length;i++) {
			String s="";
			for(int j=0;j<used.length;j++) {
				if(used[j]==1) {
					s+=relation[i][j];
				}
			}
			set.add(s);
		}
		if(set.size()==row)
			return true;
		else
			return false;
	}
}