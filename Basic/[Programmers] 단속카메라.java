/* https://programmers.co.kr/learn/courses/30/lessons/42884 */
//도로에 들어오는 시각 순으로 오름차순 정렬
//차례로 list에 넣으면서 들어온 차들 중 가장 빨리 나가는 시간 업데이트
//가장빨리 나가는 시간보다 먼저 들어올 경우 list에 계속 추가
//들어올 차가 없으면, answer++하고 list clear() - 현재까지 list에 들어와있는 차는 하나의 감시카메라로 check 가능.
//위의 과정을 routes전체 반복.

//해결방향에 대해 복잡도가 너무 커진다 싶으면 방향을 틀어볼 것.
//도로에 들어오는 시간 순으로 정렬 후 check 계산하는 아이디어를 생각하지 못했음.
//자료구조가 필요한 부분 파악할 것.

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[][] routes= {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
		System.out.println(solution(routes));
	}

	public static int solution(int[][] routes) {
		int answer=0;
		List<Integer> checked=new ArrayList<Integer>();

		Arrays.sort(routes,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0]-o2[0];
			}

		});

		int index=0;
		int out=30000;
		while(index<routes.length) {
			checked.add(routes[index][0]);
			out=routes[index][1];
			index++;
			while(index<routes.length&&routes[index][0]<=out) {
				out=Math.min(out, routes[index][1]);
				checked.add(routes[index][0]);
				index++;
			}
			if(!checked.isEmpty()) {
				answer++;
				checked.clear();
			}
		}

		return answer;
	}
}
