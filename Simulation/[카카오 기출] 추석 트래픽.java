/* https://programmers.co.kr/learn/courses/30/lessons/17676 */

//끝지점으로 정렬되어있는 상태
//logs첫 요소부터 끝 요소까지
//그다음 요소들 중 start지점 겹친 부분, end지점 겹친 부분 중 max값이
//log 중첩된 부분

import java.util.*;
import java.io.*;

public class Solution {
	public static List<Log> logs;

	public static void main(String[] args) throws IOException{
		String[] lines= {"2016-09-15 01:00:04.001 2.0s",
						"2016-09-15 01:00:07.000 2s"};

		System.out.println(solution(lines));
	}

	public static int solution(String[] lines) {
		StringTokenizer st;
		logs=new ArrayList<Log>();
		for(int j=0;j<lines.length;j++) {
			st=new StringTokenizer(lines[j]);
			int endTime=0;
			int sec=0;
			for(int i=0;i<3;i++) {
				String input=st.nextToken();
				switch(i) {
				case 0:
					break;
				case 1:
					String end=input;
					endTime=hourToMili(end);
					break;
				case 2:
					String time=input;
					if(time.length()!=1)
						time=time.substring(0,time.length()-1);
					Double d=Double.parseDouble(time)*1000;
					sec=d.intValue();
					break;
				}
			}
			logs.add(new Log(endTime-sec+1,endTime));
		}

		return getMax();
	}

	public static int getMax() {
		 int maxCnt = 0; // 최대값
	        for(Log sourceVo : logs) {
	            long startDtm = sourceVo.start; // 시작시각
	            long endedDtm = sourceVo.end; // 종료시각
	            int startCnt = 0; // 현재 로그 시작구간에서 실행중이 트랜젝션 갯수
	            int endedCnt = 0; // 현재 로그 종료구간에서 실행중이 트랜젝션 갯수
	            for(Log targetVo : logs) {
	                if(startDtm <= targetVo.end && targetVo.start <=startDtm + 999) {
	                    startCnt++;
	                }
	                if(endedDtm <= targetVo.end && targetVo.start <= endedDtm + 999) {
	                    endedCnt++;
	                }
	            }
	            maxCnt = Math.max(maxCnt, startCnt);
	            maxCnt = Math.max(maxCnt, endedCnt);
	        }
	        return maxCnt;
	}

	public static int hourToMili(String time) {
		int miliSec=0;
		StringTokenizer st=new StringTokenizer(time,":.");
		for(int i=0;i<4;i++) {
			switch(i) {
			case 0:
				int hour=Integer.parseInt(st.nextToken());
				miliSec+=(hour*1000*60*60);
				break;
			case 1:
				int min=Integer.parseInt(st.nextToken());
				miliSec+=(min*1000*60);
				break;
			case 2:
				int sec=Integer.parseInt(st.nextToken());
				miliSec+=(sec*1000);
				break;
			case 3:
				int mili=Integer.parseInt(st.nextToken());
				miliSec+=mili;
				break;
			}
		}

		return miliSec;
	}
}

class Log{
	int start;
	int end;
	Log(int start,int end){
		this.start=start;
		this.end=end;
	}
}
