/* https://www.acmicpc.net/problem/11055 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] A=new int[n];
		for(int i=0;i<n;i++) {
			A[i]=sc.nextInt();
		}
		int[] D=new int[n];
		for(int i=0;i<n;i++) {
			D[i]=A[i];
			for(int j=0;j<i;j++) {
				if(A[i]>A[j]&&D[i]<D[j]+A[i]) {
					D[i]=D[j]+A[i];
				}
			}
		}
		int max=D[0];
		for(int i=1;i<n;i++) {
			if(max<D[i])
				max=D[i];
		}
		System.out.println(max);
	}
}
