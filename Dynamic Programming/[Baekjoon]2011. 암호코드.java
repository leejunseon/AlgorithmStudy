/* https://www.acmicpc.net/problem/2011 */
import java.util.*;

public class Main {	
	static String input;
	static int[] cache;
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		input=sc.nextLine();
		cache=new int[input.length()];
		Arrays.fill(cache, -1);
		System.out.println(Passwd(0));
	}
	
	public static int Passwd(int start) {
		if(start==(input.length()))//다 센 경우
			return 1;
		if(start>(input.length()))//초과된 경우
			return 0;
		if(input.charAt(start)=='0')
			return 0;
		
		if(cache[start]!=-1)
			return cache[start];
		
		if(start==input.length()-1) {//마지막 전 글자인 경우
			cache[start]=Passwd(start+1)%1000000;
		}else {
			int first=Integer.parseInt(input.substring(start, start+2));
			if(0<first&&first<=26)
				cache[start]=(Passwd(start+1)+Passwd(start+2))%1000000;
			else if(first>26)
				cache[start]=Passwd(start+1)%1000000;
			else
				return 0;
		}
		
		return cache[start]%1000000;
	}
}