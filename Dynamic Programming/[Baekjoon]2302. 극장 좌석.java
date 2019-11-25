/* https://www.acmicpc.net/problem/2302 */
import java.util.*;

public class Main {
	static int[] cache;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int m=sc.nextInt();
		cache=new int[n+1];
		Arrays.fill(cache, -1);
		int[] sits=new int[m+2];
		sits[0]=0;
		sits[m+1]=n+1;
		for(int i=1;i<=m;i++)
			sits[i]=sc.nextInt();
		int ans=1;
		for(int i=0;i<sits.length-1;i++) {
			if(sits[i+1]-sits[i]-1>=2)
				ans*=fibo(sits[i+1]-sits[i]-1);
		}
		System.out.println(ans);
	}

	public static int fibo(int n) {
		if(n==1)
			return 1;
		if(n==2)
			return 2;

		if(cache[n]!=-1)
			return cache[n];

		cache[n]=fibo(n-1)+fibo(n-2);
		return cache[n];
	}
}
