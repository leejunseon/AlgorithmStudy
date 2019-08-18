/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;

import java.io.*;

public class Main {
	public static List<Log> logs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine(),"[“”,] s");
		logs=new ArrayList<Log>();
		while(st.hasMoreTokens()) {
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
		
		System.out.println(getMax());
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