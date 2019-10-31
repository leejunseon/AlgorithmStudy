/* https://www.acmicpc.net/problem/2579 */

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] A=new int[n+1];
		for(int i=1;i<=n;i++) {
			A[i]=sc.nextInt();
		}
		int[] D=new int[n+1];
		D[1]=A[1];
		D[2]=A[1]+A[2];
		for(int i=3;i<=n;i++) {
			D[i]=Math.max(D[i-2]+A[i],D[i-3]+A[i-1]+A[i]);
		}
		System.out.println(D[n]);
	}
}
