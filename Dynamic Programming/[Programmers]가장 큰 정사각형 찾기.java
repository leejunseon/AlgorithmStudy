/* https://programmers.co.kr/learn/courses/30/lessons/12905 */
//board의 행이나 열이 1일 경우에는 board에 1이 있을 경우 1, 없을 경우 0을 반환한다.
//행, 열 모두 2이상일 경우에
//board[i][j]=board[i][j]를 오른쪽 밑 모서리로 하는 가장 큰 정사각형의 한 변
//이라고 정의한다면,
//board[i][j]=min(board[i-1][j],board[i-1][j-1],board[i][j-1])+1이 된다.
//이 점화식을 바탕으로 board[1][1]부터 DP(bottomUp)로 각 위치에서의 가장 큰 정사각형의 한 변을 구해놓고
//전체에서 max값의 제곱 을 반환하면된다.

//DP사고 연습하기

import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[][] board= {{1},{1}};
		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
		int answer=0;

		if(board.length==1||board[0].length==1) {
			for(int i=0;i<board.length;i++) {
				for(int j=0;j<board[0].length;j++) {
					if(board[i][j]==1)
						return 1;
				}
			}
			return 0;
		}

		for(int i=1;i<board.length;i++) {
			for(int j=1;j<board[0].length;j++) {
				if(board[i][j]==0)
					continue;
				board[i][j]=Math.min(board[i-1][j-1],Math.min(board[i-1][j], board[i][j-1]))+1;
				answer=Math.max(answer, board[i][j]);
			}
		}

		return answer*answer;
	}
}
