/* https://programmers.co.kr/learn/courses/30/lessons/12905 */


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
