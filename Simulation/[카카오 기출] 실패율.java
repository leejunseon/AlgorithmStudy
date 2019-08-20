/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */

import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		int[] one= {1,2,3};
		int[] result=solution(9,one);
		
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}
	
	public static int[] solution(int N, int[] stages) {
		int total=stages.length;
		int[] count=new int[N+1];
		int[] sum=new int[N+1];
		List<Pair> result=new ArrayList<Pair>();
		for(int i=0;i<stages.length;i++) {
			if(stages[i]<=N)
				count[stages[i]]++;
		}
		for(int i=1;i<=N;i++) {
			sum[i]=sum[i-1]+count[i];
		}
		for(int i=1;i<=N;i++) {
			if(total-sum[i-1]==0)
				result.add(new Pair(i,0));
			else
				result.add(new Pair(i,(double)count[i]/(total-sum[i-1])));
		}
		
		Collections.sort(result);
		
		int[] r=new int[N];
		for(int i=0;i<result.size();i++) {
			r[i]=result.get(i).idx;
		}
		
		return r;
	}
}

class Pair implements Comparable<Pair>{
	int idx;
	double value;
	Pair(int idx,double value) {
		this.idx=idx;
		this.value=value;
	}

	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		if(this.value!=o.value)
			return o.value>this.value?1:-1;
		else
			return this.idx-o.idx;
	}
}