/* https://programmers.co.kr/learn/courses/30/lessons/42861 */
//모든 섬이 연결될 때까지 cost가 작은 것부터 차례로 지어나가면 됨.
//costs를 비용 순으로 오름차순.
//가장 싼 다리를 짓고 그 다리 양쪽의 섬은 연결된 걸로 check
//이후로 다리list를 탐색하면서 [섬이 한쪽만 연결되어있는] 다리만 짓기(오름차순 정렬되어있기 때문에 싼 다리부터 짓게 됨)
//이 과정을 모든 섬이 연결될 때까지 반복하면서 answer 업데이트

//다리를 표현하는 그래프를 만들까 했지만 복잡해짐. 한쪽 섬만 연결상태인 곳의 다리를 놓는 아이디어를 생각 못했음.

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int n=4;
		int[][] costs= {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		System.out.println(solution(n,costs));
	}

	public static int solution(int n, int[][] costs) {
		int answer=0;
		int[] island=new int[n];
		Arrays.sort(costs,new Comparator<int[]>() {

			@Override
			public int compare(int[] arg0, int[] arg1) {
				// TODO Auto-generated method stub
				return arg0[2]-arg1[2];
			}

		});

		answer+=costs[0][2];
		island[costs[0][0]]=1;
		island[costs[0][1]]=1;
		n-=2;
		while(n!=0) {
			for(int i=1;i<costs.length;i++) {
				if(island[costs[i][0]]==1&&island[costs[i][1]]==0) {
					island[costs[i][1]]=1;
					n--;
					answer+=costs[i][2];
					break;
				}
				if(island[costs[i][0]]==0&&island[costs[i][1]]==1) {
					island[costs[i][0]]=1;
					n--;
					answer+=costs[i][2];
					break;
				}
			}
		}

		return answer;
	}
}
