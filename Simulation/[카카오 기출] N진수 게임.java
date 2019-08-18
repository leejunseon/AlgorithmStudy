/* http://tech.kakao.com/2017/11/14/kakao-blind-recruitment-round-3/ */
//Stack�� ����� ���� ��ȯ

import java.util.*;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());//����
		int t=Integer.parseInt(st.nextToken());//���� ������ ����
		int m=Integer.parseInt(st.nextToken());//�����ο�
		int p=Integer.parseInt(st.nextToken());//�� ����
		
		int totalLength=t*m;
		String Base=calc(n,totalLength);//n���� string�� ��ü ���̸�ŭ �����
		String result="";
		for(int i=p-1;i<totalLength;i+=m)
			result+=Base.charAt(i);
		System.out.println(result);
	}
	
	public static String calc(int n,int length) {
		String result="0";
		
		for(int i=1;;i++) {
			result+=change(i,n);
			if(result.length()>length)
				break;
		}
		
		return result.substring(0,length);
	}
	
	public static String change(int number,int n) {//number�� n��������
		String result="";
		
		Stack<String> stack=new Stack<String>();
		while(number/n>=n) {
			int remain=number%n;
			stack.push(Remain(remain));
			number/=n;
		}
		stack.push(Remain(number%n));
		stack.push(Remain(number/n));
		
		while(!stack.empty()) {
			result+=stack.pop();
		}
		
		for(int i=0;i<result.length();i++) {
			if(result.charAt(i)=='0')
				result=result.substring(1,result.length());
			else
				break;
		}
		return result;
	}
	
	public static String Remain(int number) {
		if(number<10)
			return Integer.toString(number);
		else {
			char c=(char)(55+number);
			return Character.toString(c);
		}
	}
}