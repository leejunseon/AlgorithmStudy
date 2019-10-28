/* https://www.acmicpc.net/problem/1965 */

import java.util.*;

public class Main {
	static int n;
	static int[] a;
	static int[] cache;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		a=new int[n];
		cache=new int[n];
		Arrays.fill(cache, -1);
		for(int i=0;i<n;i++) {
			a[i]=sc.nextInt();
		}
		System.out.println(lis(-1)-1);
	}

	public static int lis(int start) {
		if(start+1==n)
			return 1;

		if(cache[start+1]!=-1)
			return cache[start+1];

		cache[start+1]=1;
		for(int i=start+1;i<n;i++) {
			if(start==-1||a[start]<a[i])
				cache[start+1]=Math.max(cache[start+1], lis(i)+1);
		}

		return cache[start+1];
	}
}
