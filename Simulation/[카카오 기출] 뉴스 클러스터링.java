/* https://programmers.co.kr/learn/courses/30/lessons/17677 */
//Multiset의 합집합, 교집합 구현

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Solution sol=new Solution();

		String str1="aa1+aa2";
		String str2="AAAA12";

		System.out.println(sol.solution(str1,str2));
	}

	public int solution(String str1, String str2) {
		List<String> one=new ArrayList<String>();
		List<String> two=new ArrayList<String>();

		str1=str1.toLowerCase();
		str2=str2.toLowerCase();

		//97 ~ 122
		for(int i=0;i<str1.length()-1;i++) {
			String sub=str1.substring(i,i+2);
			boolean flag=true;
			for(int j=0;j<sub.length();j++) {
				char c=sub.charAt(j);
				if((int)c<97||(int)c>122||c==' ') {
					flag=false;
					break;
				}
			}
			if(flag) {
				one.add(sub);
			}
		}

		for(int i=0;i<str2.length()-1;i++) {
			String sub=str2.substring(i,i+2);
			boolean flag=true;
			for(int j=0;j<sub.length();j++) {
				char c=sub.charAt(j);
				if((int)c<97||(int)c>122||c==' ') {
					flag=false;
					break;
				}
			}
			if(flag)
				two.add(sub);
		}

		if(one.size()==0&&two.size()==0)
			return 65536;

		int hap=getHap(one,two);
		int gyo=getGyo(one,two);

		return (int)Math.floor((double)gyo/hap*65536);
	}

	public int getHap(List<String> one,List<String> two) {
		List<String> hap=new ArrayList<String>();

		Collections.sort(one);
		Collections.sort(two);
		Queue<String> o=new LinkedList<String>(one);
		Queue<String> t=new LinkedList<String>(two);

		while(!o.isEmpty()||!t.isEmpty()) {
			if(o.isEmpty()) {
				hap.addAll(t);
				t.clear();
			}else if(t.isEmpty()) {
				hap.addAll(o);
				o.clear();
			}else {
				if(o.peek().equals(t.peek())) {
					hap.add(o.remove());
					t.remove();
				}else {
					if(o.peek().compareTo(t.peek())<0) {
						hap.add(o.remove());
					}else
						hap.add(t.remove());
				}
			}
		}

		return hap.size();
	}

	public int getGyo(List<String> one,List<String> two) {
		List<String> hap=new ArrayList<String>();

		Collections.sort(one);
		Collections.sort(two);
		Queue<String> o=new LinkedList<String>(one);
		Queue<String> t=new LinkedList<String>(two);

		while(!o.isEmpty()&&!t.isEmpty()) {
			if(o.peek().equals(t.peek())) {
				hap.add(o.remove());
				t.remove();
			}else {
				if(o.peek().compareTo(t.peek())<0) {
					o.remove();
				}else
					t.remove();
			}
		}

		return hap.size();
	}
}
