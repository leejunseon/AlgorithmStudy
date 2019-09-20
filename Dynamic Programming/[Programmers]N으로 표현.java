/* https://programmers.co.kr/learn/courses/30/lessons/42895 */
//N을 n개 사용해서 만들 수 있는 수들을 Nn이라고 했을 때,
//N1 : N
//N2 : NN, N1 사칙연산 N1
//N3 : NNN, N1 사칙연산 N2, N2 사칙연산 N1
//N4 : NNNN, N1 사칙연산 N3, N2 사칙연산 N2, N3 사칙연산 N1
//....
//N8 : NNNNNNNN, N1 사칙연산 N7, ... ,N7 사칙연산 N1
//이다.
//일부분의 해를 사용해서 전체 해를 구하므로 DP문제라고 할 수 있음.
//접근이 힘들었던 문제..
//초기에 Nn에 NNN...NNN을 미리 넣어놓는 아이디어를 떠올리지 못햇음

import java.util.*;

public class Solution {
	public static List<HashSet<Integer>> cache;

	public static void main(String[] args) {
		System.out.println(solution(5,26));
	}

	public static int solution(int N, int number) {
        cache=new ArrayList<HashSet<Integer>>();
        for(int i=0;i<=8;i++) {
        	cache.add(new HashSet<Integer>());
        	if(i>=1) {
        		cache.get(i).add(getNum(N,i));
        	}
        }

        for(int i=1;i<=8;i++) {
        	for(int j=1;j<i;j++) {
        		int left=j;
        		int right=i-j;
        		for(int l:cache.get(left)) {
        			for(int r:cache.get(right)) {
        				//더하기
        				cache.get(i).add(l+r);
        				//빼기
        				cache.get(i).add(l-r);
        				//곱하기
        				cache.get(i).add(l*r);
        				//나누기
        				if(r!=0)
        					cache.get(i).add(l/r);
        			}
        		}
        	}
        	if(cache.get(i).contains(number))
        		return i;
        }

        return -1;
    }

	public static int getNum(int N,int num) {
		int answer=N;
		for(int i=1;i<num;i++) {
			answer=answer*10+N;
		}
		return answer;
	}
}
