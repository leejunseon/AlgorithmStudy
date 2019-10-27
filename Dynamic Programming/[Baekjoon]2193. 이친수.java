/* https://www.acmicpc.net/problem/2193 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
    	Scanner sc=new Scanner(System.in);
    	int n=sc.nextInt();
    	if(n!=1&&n!=2) {
	    	long[] d=new long[n+1];
	    	d[1]=1;
	    	d[2]=1;
	    	for(int i=3;i<=n;i++) {
	    		d[i]=d[i-1]+d[i-2];
	    	}
	    	System.out.println(d[n]);
    	}else if(n==1) {
    		System.out.println(1);
    	}else if(n==2) {
    		System.out.println(1);
    	}
    }
}
