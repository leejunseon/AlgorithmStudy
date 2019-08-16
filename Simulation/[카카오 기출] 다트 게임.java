/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;
import java.io.*;

public class Main {	
	public static Stack<Integer> score;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String input=br.readLine();
		score=new Stack<Integer>();
		int result=0;
		
		for(int i=0;i<input.length();i++) {
			char in=input.charAt(i);
			if(0<=(in-'0')&&(in-'0')<=10) {//숫자일 때
				if(input.length()>i&&input.charAt(i+1)=='0') {
					score.add(10);
					i++;
				}else
					score.add(in-'0');
			}else {//문자일 때
				int n=0;
				switch(in) {
				case 'S':
					n=score.pop();
					n=(int) Math.pow(n, 1);
					score.push(n);
					break;
				case 'D':
					n=score.pop();
					n=(int) Math.pow(n, 2);
					score.push(n);
					break;
				case 'T':
					n=score.pop();
					n=(int) Math.pow(n, 3);
					score.push(n);
					break;
				case '*':
					if(score.size()==1) {
						n=score.pop();
						n*=2;
						score.push(n);
					}else {
						int first=score.pop();
						int second=score.pop();
						first*=2;
						second*=2;
						score.push(second);
						score.push(first);
					}
					break;
				case '#':
					n=score.pop();
					n*=-1;
					score.push(n);
					break;
				}
			}
		}
		
		while(!score.isEmpty()) {
			int n=score.pop();
			result+=n;
		}
		System.out.println(result);
	}
}