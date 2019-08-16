/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int t;
	public static int m;
	public static List<Integer> crews;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//첫차 : 540
		//막차 : 540+(n-1)*t
	
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());//셔틀 운행 횟수
		t=Integer.parseInt(st.nextToken());//셔틀 운행 간격
		m=Integer.parseInt(st.nextToken());//한 셔틀 탈 수 있는 최대 인원
		
		st=new StringTokenizer(br.readLine(),"[“”, ]");
		crews=new ArrayList<Integer>();
		while(st.hasMoreTokens()) {
			String input=st.nextToken();
			int hour=Integer.parseInt(input.substring(0,2));
			int minute=Integer.parseInt(input.substring(3,5));
			int time=time(hour,minute);
			crews.add(time);
		}
		Collections.sort(crews);
		
		for(int i=0;i<crews.size();i++) {
			if(crews.get(i)>540+(n-1)*t) {
				crews.remove(i);
				i--;
			}
		}
		
		System.out.println(clock(simulate()));
	}
	
	public static int simulate() {
		int result=0;
		
		int remain=0;
		int previous=0;
		int time=540;
		for(int i=0;i<n;i++) {
			remain=m;
			int waiting=0;
			for(int j=0;j<crews.size();j++) {
				int crew=crews.get(j);
				if(crew>time)
					break;
				if(previous<=crew&&crew<=time) {//대기인원 몇명인지 파악
					waiting++;
				}
			}
			if(waiting>=remain) {//웨이팅이 더 많으면
				result=crews.get(waiting-1)-1;
			}else {//다 탈 수 있으면
				result=time;
			}
			int remove=waiting<remain?waiting:remain;
			for(int j=0;j<remove;j++) {
				crews.remove(0);
			}
			previous=time;
			time+=t;
		}
		
		return result;
	}
	
	//00:00부터의 시간 반환
	public static int time(int hour,int minute) {
		return hour*60+minute;
	}
	
	public static String clock(int time) {
		String result="";
		
		int hour=time/60;
		if(hour<10)
			result="0"+Integer.toString(hour);
		else
			result=Integer.toString(hour);
		
		int minute=time%60;
		if(minute<10)
			result+=":"+"0"+Integer.toString(minute);
		else
			result+=":"+Integer.toString(minute);
		
		return result;
	}
}