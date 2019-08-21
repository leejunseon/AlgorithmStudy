/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */
//불필요한 연산 줄이기

import java.util.*;
import java.io.*;

public class Solution {
	public static List<food> foods;
	
	public static void main(String[] args) {
		int[] food_times= {5, 6, 2, 5,2};
		int k=14;
		System.out.println(solution(food_times,k));
	}
	
	public static int solution(int[] food_times, long k) {
		foods=new ArrayList<food>();
		for(int i=0;i<food_times.length;i++) {
			foods.add(new food(i+1,food_times[i]));
		}
		Collections.sort(foods);
		int size=foods.size();
		
		int pre=0;
		int i=0;
		for(food f:foods) {
			long diff=f.time-pre;
			if(diff!=0) {
				long spend=diff*size;
				if(spend<=k) {
					k-=spend;
					pre=f.time;
				}else {
					k%=size;
					foods.subList(i, food_times.length).sort(new Comparator<food>() {

						@Override
						public int compare(food o1, food o2) {
							// TODO Auto-generated method stub
							return o1.number-o2.number;
						}
						
					});
					return foods.get(i+(int)k).number;
				}
			}
			i++;
			size--;
		}
		
		return -1;
	}
}

class food implements Comparable<food>{
	int number;
	int time;
	food(int number,int time){
		this.number=number;
		this.time=time;
	}
	@Override
	public int compareTo(food o) {
		// TODO Auto-generated method stub
		if(this.time!=o.time)
			return this.time-o.time;
		else
			return this.number-o.number;
	}
}
