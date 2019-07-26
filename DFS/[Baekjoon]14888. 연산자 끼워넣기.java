/* https://www.acmicpc.net/problem/14888 */

import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] numbers;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());//숫자 갯수
		numbers=new int[n];
		int[] op=new int[4];//연산자 배열
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			numbers[i]=Integer.parseInt(st.nextToken());
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			op[i]=Integer.parseInt(st.nextToken());
		}
		
		int[] answer=new int[n-1];//답 연산자
		System.out.println(max(0,op,answer));
		System.out.println(min(0,op,answer));
	}
	
	public static long max(int now,int[] op,int[] answer) {
		if(now==n-1) {
			return calc(answer);
		}
		
		long result=-987654321;
		int[] opcopy=op.clone();
		int[] answercopy=answer.clone();
				
		for(int i=0;i<4;i++) {
			if(opcopy[i]>0) {
				opcopy[i]--;
				answercopy[now]=i;
				result=Math.max(result, max(now+1,opcopy,answercopy));
				opcopy[i]++;
			}
		}
		
		return result;
	}
	
	public static long min(int now,int[] op,int[] answer) {
		if(now==n-1) {
			return calc(answer);
		}
		
		long result=987654321;
		int[] opcopy=op.clone();
		int[] answercopy=answer.clone();
		
		for(int i=0;i<4;i++) {
			if(opcopy[i]>0) {
				opcopy[i]--;
				answercopy[now]=i;
				result=Math.min(result, min(now+1,opcopy,answercopy));
				opcopy[i]++;
			}
		}
		
		return result;
	}
	
	public static long calc(int[] answer) {
		long result=numbers[0];
		
		for(int i=0;i<n-1;i++) {
			switch(answer[i]) {
			case 0:// +
				result+=numbers[i+1];
				break;
			case 1:// -
				result-=numbers[i+1];
				break;
			case 2:// *
				result*=numbers[i+1];
				break;
			case 3:// /
				result/=numbers[i+1];
				break;
			}
		}
		
		return result;
	}
}