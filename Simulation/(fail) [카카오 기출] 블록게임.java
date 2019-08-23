/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */
//테스트케이스 하나 못맞춤. 반례 찾아볼 것

import java.util.*;
import java.io.*;

public class Solution {
	public static List<pair> blocks;
	public static List<Integer> no;//블록 종류 number
	public static Map<Integer,Boolean> Removable;

	public static void main(String[] args) {
		int[][] board= {{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0},
						{0,0,9,0,0,0,4,0,0,0,0},
						{0,7,9,0,0,0,4,0,0,0,0},
						{0,7,9,9,3,4,4,0,0,0,0},
						{7,7,0,2,3,0,0,0,5,5,0},
						{1,2,2,2,3,3,0,0,0,5,0},
						{1,1,1,6,0,0,0,0,0,5,0},
						{0,0,6,6,6,0,0,0,0,0,0}};
		System.out.println(solution(board));
	}

	public static int solution(int[][] board) {
        int answer = 0;
        blocks=new ArrayList<pair>();
        no=new ArrayList<Integer>();
        no.add(1);
        no.add(2);
        no.add(5);
        no.add(8);
        no.add(10);
        no.add(11);
        no.add(12);
        for(int i=0;i<board.length;i++) {
        	for(int j=0;j<board[0].length;j++) {
        		if(board[i][j]!=0) {
        			blocks.add(new pair(board[i][j],i,j));
        		}
        	}
        }
        Collections.sort(blocks);

        //각 블록이 제거 가능한 블록인지 파악
        Removable=new HashMap<Integer,Boolean>();
        for(int i=0;i<=blocks.size()-4;i+=4) {
        	pair one=blocks.get(i);
        	pair two=blocks.get(i+1);
        	pair three=blocks.get(i+2);
        	pair four=blocks.get(i+3);
        	if(no.contains(shape(one,two,three,four))) {
        		one.removable=false;
        		two.removable=false;
        		three.removable=false;
        		four.removable=false;
        		Removable.put(one.number, false);
        	}else {
        		Removable.put(one.number, true);
        	}
        }

        int idx=0;
        while(!blocks.isEmpty()) {
        	pair one=blocks.get(idx);
        	pair two=blocks.get(idx+1);
        	pair three=blocks.get(idx+2);
        	pair four=blocks.get(idx+3);
        	//블록의 종류
        	int num=shape(one,two,three,four);
        	//블록의 빈칸
        	pair blankone=null;
        	pair blanktwo=null;

        	//제거 가능하다면
        	if(one.removable) {
        		switch(num) {
        		case 3:
        			blankone=new pair(one.number,one.y,one.x+1);
        			blanktwo=new pair(one.number,one.y,one.x+2);
        			break;
        		case 4:
        			blankone=new pair(one.number,one.y,one.x-1);
        			blanktwo=new pair(one.number,one.y-1,one.x-1);
        			break;
        		case 6:
        			blankone=new pair(one.number,one.y,one.x+1);
        			blanktwo=new pair(one.number,one.y+1,one.x+1);
        			break;
        		case 7:
        			blankone=new pair(one.number,one.y,one.x-2);
        			blanktwo=new pair(one.number,one.y,one.x-1);
        			break;
        		case 9:
        			blankone=new pair(one.number,one.y,one.x-1);
        			blanktwo=new pair(one.number,one.y,one.x+1);
        			break;
        		}
        		//빈칸위쪽을 탐색하며 제거 가능한지 파악
        		boolean obstacle=false;//장애물이 있는지
        		boolean removable=true;//장애물이 제거 가능한지
        		for(int i=blankone.y;i>=0;i--) {
        			if(board[i][blankone.x]!=0) {
        				obstacle=true;
        				if(!Removable.get(board[i][blankone.x])) {
        					removable=false;
        				}
        				break;
        			}
        		}
        		if(removable) {//이전 빈칸에서 장애물이 제거 가능했다면
        		for(int i=blanktwo.y;i>=0;i--) {
        			if(board[i][blanktwo.x]!=0) {
    					obstacle=true;
    					if(!Removable.get(board[i][blanktwo.x])) {
        					removable=false;
        				}
        				break;
        			}
        		}
        		}

        		if(obstacle==false) {//위에 아무것도 없으면
        			answer++;
        			for(int i=0;i<4;i++) {
        				board[blocks.get(idx).y][blocks.get(idx).x]=0;
            			blocks.remove(idx);
        			}
        		}else {//머가 있으면
        			if(removable==false) {//제거 불가면
        				Removable.put(one.number, false);
        				for(int i=0;i<4;i++)
                			blocks.remove(idx);
        			}else {//제거 가능하면
        				idx+=4;
        			}
        		}
        	}else {//제거 불가
        		for(int i=0;i<4;i++)
        			blocks.remove(idx);
        	}

        	if(idx>=blocks.size())
				idx=0;
        }

        return answer;
    }

	public static int shape(pair one,pair two,pair three,pair four){
		if(two.y==one.y&&two.x==one.x+1) {
			if(three.y==one.y&&three.x==one.x+2) {
				if(four.y==one.y+1&&four.x==one.x+2) {
					return 1;
				}else if(four.y==one.y+1&&four.x==one.x) {
					return 5;
				}else if(four.y==one.y+1&&four.x==one.x+1) {
					return 11;
				}
			}else if(three.y==one.y+1&&three.x==one.x) {
				return 2;
			}else if(three.y==one.y+1&&three.x==one.x+1) {
				return 8;
			}
		}else if(two.y==one.y+1&&two.x==one.x) {
			if(three.y==one.y+1&&three.x==one.x+1) {
				if(four.y==one.y+1&&four.x==one.x+2) {
					return 3;
				}else if(four.y==one.y+2&&four.x==one.x) {
					return 10;
				}
			}else if(three.y==one.y+2&&three.x==one.x-1) {
				return 4;
			}else if(three.y==one.y+2&&three.x==one.x) {
				return 6;
			}
		}else if(two.y==one.y+1&&two.x==one.x-2) {
			return 7;
		}else if(two.y==one.y+1&&two.x==one.x-1) {
			if(four.y==one.y+1&&four.x==one.x+1) {
				return 9;
			}else if(four.y==one.y+2&&four.x==one.x) {
				return 12;
			}
		}
		return 0;
	}

}

class pair implements Comparable<pair>{
	int number;
	boolean removable;
	int y;
	int x;
	pair(int number,int y,int x){
		this.number=number;
		this.removable=true;
		this.y=y;
		this.x=x;
	}
	@Override
	public int compareTo(pair arg0) {
		// TODO Auto-generated method stub
		if(this.number!=arg0.number)
			return this.number-arg0.number;
		else {
			if(this.y!=arg0.y)
				return this.y-arg0.y;
			else
				return this.x-arg0.x;
		}
	}
}
