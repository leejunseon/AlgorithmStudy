/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */
//시작점을 기준으로 정렬 -> 뒤에있는 것들과 비교하면서 count (기준점의 end ~ 기준점의 end+999 사이에 있는 것을 count)

import java.util.*;
import java.io.*;

public class Main {
	public static List<Log> logs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st=new StringTokenizer(br.readLine(),"[¡°¡±,] s");
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
		
		Collections.sort(logs,new Comparator<Log>() {

			@Override
			public int compare(Log o1, Log o2) {
				// TODO Auto-generated method stub
				return o1.start-o2.start;
			}
			
		});
		
		System.out.println(getMax());
	}
	
	public static int getMax() {
		int result=0;
		
		for(int i=0;i<logs.size();i++) {
			int num=1;
			int exitTime=logs.get(i).end;
			for(int j=i+1;j<logs.size();j++) {
				int start=logs.get(j).start;
				int end=logs.get(j).end;
				if(start<=exitTime+999&&exitTime<=end)
					num++;
			}
			result=Math.max(result, num);
		}
		
		return result;
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
