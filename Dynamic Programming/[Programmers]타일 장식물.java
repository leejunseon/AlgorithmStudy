/* https://programmers.co.kr/learn/courses/30/lessons/43104 */
//N번째 타일의 변을 N변 이라고 하면,
//N변=(N-1)변+(N-2)변
//이다.
//그리고 N번째타일까지의 직사각형의 둘레는
//N변*2+(N변+(N-1)변)*2
//이다.
//N번째 변 구하는 점화식 사용해서 DP구현

import java.util.*;

public class Solution {
	public static long[] cache;

	public static void main(String[] args) {
		System.out.println(solution(6));
	}

	public static long solution(int N) {
        cache=new long[N+1];
        Arrays.fill(cache, -1);

        return side(N)*2+(side(N)+side(N-1))*2;
    }

	public static long side(int N) {
		if(N==1||N==2)
			return 1;

		if(cache[N]!=-1)
			return cache[N];

		cache[N]=0;
		cache[N]=side(N-1)+side(N-2);
		return cache[N];
	}
}
