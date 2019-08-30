/* https://www.acmicpc.net/problem/1038 */

import java.util.*;
import java.io.*;

public class Main {
	static int[][] nums;//첫째자리 구하기 위한 배열
	static int[] nums2;//자릿수 구하기 위한 배열
	static int n;//입력값
	static int d;//자릿수
	static int[] cache;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		if(n>1022)
			System.out.println(-1);
		else {
			cache=new int[n+1];
			Arrays.fill(cache, -1);
			nums();
			d=digit(n);//자릿수 구함
			String ans="";
			System.out.println(Dec(d,n-nums2[d-1],ans));
		}
	}

	public static void nums() {
		//[자릿수][첫자리]
		nums=new int[11][10];
		nums2=new int[11];

		for(int i=0;i<10;i++)
			nums[1][i]=1;
		for(int i=2;i<11;i++)
			for(int j=1;j<10;j++)
				nums[i][j]=nums[i-1][j-1]+nums[i][j-1];

		nums[1][0]=0;
		for(int i=1;i<11;i++) {
			if(i!=1)
				nums[i][1]+=nums[i-1][9];
			for(int j=1;j<10;j++) {
				nums[i][j]+=nums[i][j-1];
			}
		}
		for(int i=1;i<11;i++)
			nums2[i]=nums[i][9];

		for(int i=2;i<11;i++) {
			for(int j=1;j<10;j++) {
				nums[i][j]-=nums2[i-1];
			}
		}
	}

	public static int digit(int n) {
		int res=0;
		for(int i=1;i<=10;i++) {
			if(n<=nums2[i]) {
				res=i;
				break;
			}
		}
		return res;
	}

	public static long Dec(int d,int n,String ans) {//자릿수, 번째, 답
		String res="";
		long resi=0;

		if(ans.equals("")&&d==1)
			return n;

		if(d==1) {
			res=ans+Integer.toString(n-1);
			return Long.parseLong(res);
		}

		for(int i=1;i<10;i++) {
			if(nums[d][i]>=n) {
				res=ans+Integer.toString(i);
				resi=Dec(d-1,n-nums[d][i-1],res);
				break;
			}
		}

		return resi;
	}
}
