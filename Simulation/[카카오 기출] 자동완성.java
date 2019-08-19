/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;
import java.io.*;

public class Main {
	public static List<String> words;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine(),"[“”,] ");
		words=new ArrayList<String>();
		while(st.hasMoreTokens()) {
			words.add(st.nextToken());
		}
		
		Collections.sort(words,new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareToIgnoreCase(o2);
			}
			
		});
		
		System.out.println(calc());
	}
	
	public static int calc() {
		int result=0;
		
		for(int i=0;i<words.size();i++) {
			String s=words.get(i);
			int size=1;
			if(i-1>=0) {
				String compare=words.get(i-1);
				int n=0;
				for(int j=1;j<=s.length();j++) {
					if(j>compare.length())
						break;
					if(s.substring(0,j).equals(compare.substring(0,j))) {
						n=j;
						if(j!=s.length())
							n++;
					}else
						break;
					size=Math.max(size, n);
				}
			}
			if(i+1<words.size()) {
				String compare=words.get(i+1);
				int n=0;
				for(int j=1;j<=s.length();j++) {
					if(j>compare.length())
						break;
					if(s.substring(0,j).equals(compare.substring(0,j))) {
						n=j;
						if(j!=s.length())
							n++;
					}else
						break;
					size=Math.max(size, n);
				}
			}
			result+=size;
		}
		
		return result;
	}
}