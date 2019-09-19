/* https://programmers.co.kr/learn/courses/30/lessons/43163 */
//getMin()메소드에서 words중 now와 글자 하나만 다른 요소로 이동
//->dfs이용해 완전 탐색. now와 Target이 같으면 이동횟수 반환
//이동횟수 중 min값이 정답

public class Solution {
	public static String Target;
	public static int[] used;

	public static void main(String[] args) {
		String begin="hit";
		String end="hhh";
		String[] words= {"hhh","ggg"};
		System.out.println(solution(begin,end,words));
	}

	public static int solution(String begin, String target, String[] words) {
        Target=target;

        boolean flag=false;
        for(int i=0;i<words.length;i++) {
        	if(target.equals(words[i])) {
        		flag=true;
        		break;
        	}
        }
        if(!flag)
        	return 0;

        used=new int[words.length];
        int result=getMin(begin,words,0);
        if(result==987654321)
        	return 0;
        else
        	return result;
    }

	public static int getMin(String now,String[] words,int num) {
		int answer=987654321;
		if(now.equals(Target))
			return num;

		for(int i=0;i<words.length;i++) {
			if(used[i]==0&&compare(now,words[i])==now.length()-1) {
				used[i]=1;
				answer=Math.min(answer, getMin(words[i],words,num+1));
				used[i]=0;
			}
		}

		return answer;
	}

	public static int compare(String now,String next) {
		int answer=0;
		for(int i=0;i<now.length();i++) {
			if(now.charAt(i)==next.charAt(i))
				answer++;
		}
		return answer;
	}
}
