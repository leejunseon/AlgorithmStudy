/* https://programmers.co.kr/learn/courses/30/lessons/42839 */
//모든 경우 완전탐색하면서 소수는 primes에 집어넣음.
//primes는 HashSet이기 때문에 자동으로 중복된 소수는 걸러짐.
//모든 경우 완전탐색 후 primes의 갯수 return

import java.util.*;

public class Solution {
	public static Set<Integer> primes;

	public static void main(String[] args) {
		String numbers="011";
		System.out.println(solution(numbers));
	}

	public static int solution(String numbers) {
		primes=new HashSet<Integer>();
        int[] use=new int[10];
        for(int i=0;i<numbers.length();i++) {
        	use[numbers.charAt(i)-'0']++;
        }

        for(int i=1;i<=numbers.length();i++) {//사용 숫자 갯수
        	for(int j=0;j<numbers.length();j++) {//첫 글자
        		if(numbers.charAt(j)=='0')
        			continue;
        		String current=Character.toString(numbers.charAt(j));
        		use[numbers.charAt(j)-'0']--;
        		getNumber(current,use,i-1);
        		use[numbers.charAt(j)-'0']++;
        	}
        }

        return primes.size();
    }

	//주어진 첫글자, 사용숫자 갯수 에서의 소수 갯수 구하기
	public static void getNumber(String current,int[] use,int remain) {
		if(remain==0) {//남은 갯수 0
			if(current.equals("1"))
				return;
			if(isPrime(Integer.parseInt(current))) {//소수이면
				primes.add(Integer.parseInt(current));
				return;
			}
		}

		for(int i=0;i<=9;i++) {
			if(use[i]!=0) {
				String add=Integer.toString(i);
				use[i]--;
				getNumber(current+add,use,remain-1);
				use[i]++;
			}
		}

	}

	//에라토스테네스의 체 사용한 소수 판별
	public static boolean isPrime(int num) {
		boolean result=true;
		int[] arr=new int[num+1];
		for(int i=2;i<=num;i++)
			arr[i]=i;

		for(int i=2;i<=Math.sqrt(num);i++) {
			if(arr[i]==0)
				continue;
			for(int j=i+i;j<=num;j+=i) {
				arr[j]=0;
				if(j==num)
					return false;
			}
		}

		return result;
	}
}
