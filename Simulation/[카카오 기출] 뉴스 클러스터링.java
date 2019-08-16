/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String str1=br.readLine().toLowerCase();
		String str2=br.readLine().toLowerCase();
		
		List<String> whole=new ArrayList<String>();
		
		List<String> one=new ArrayList<String>();
		for(int i=0;i<str1.length()-1;i++) {
			String input=str1.substring(i,i+2);
			boolean flag=true;
			for(int j=0;j<2;j++) {
				char c=input.charAt(j);
				if(c<97||c>122||c==' ') {
					flag=false;
					break;
				}
			}
			if(flag) {
				one.add(input);
				whole.add(input);
			}
		}
		
		List<String> two=new ArrayList<String>();
		for(int i=0;i<str2.length()-1;i++) {
			String input=str2.substring(i,i+2);
			boolean flag=true;
			for(int j=0;j<2;j++) {
				char c=input.charAt(j);
				if(c<97||c>122||c==' ') {
					flag=false;
					break;
				}
			}
			if(flag) {
				two.add(input);
				whole.add(input);
			}
		}
		
		if(one.size()==0&&two.size()==0)
			System.out.println(65536);
		else {
			//교집합
			List<String> a=new ArrayList<String>();
			List<String> b=new ArrayList<String>();
			a.addAll(one);
			b.addAll(two);
			a.retainAll(b);
			int gyo=a.size();
			//합집합
			for(String s:a) {
				if(one.contains(s))
					one.remove(s);
				if(two.contains(s))
					two.remove(s);
			}
			int hap=gyo+one.size()+two.size();
			//결과값
			double result=(double)gyo/(double)hap;
			System.out.println((int)Math.floor(result*65536));
		}
	}
}