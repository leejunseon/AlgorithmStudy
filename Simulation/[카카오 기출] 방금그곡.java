/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;
import java.io.*;

public class Main {
	public static List<String> input;
	public static List<musicInfo> infos;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//m입력
		st=new StringTokenizer(br.readLine(),"“” ");
		String m=st.nextToken();
		input=new ArrayList<String>();
		for(int i=0;i<m.length();i++) {
			String s=Character.toString(m.charAt(i));
			if(i!=m.length()-1&&m.charAt(i+1)=='#') {
				s+="#";
				i++;
			}
			input.add(s);
		}
		
		//musicrinfos 입력
		st=new StringTokenizer(br.readLine(),"[“,” ]");
		infos=new ArrayList<musicInfo>();
		int count=0;;
		while(st.hasMoreTokens()) {
			String start="";
			String end="";
			String title="";
			String sheets="";
			for(int i=0;i<4;i++) {
				switch(i) {
				case 0:
					start=st.nextToken();
					break;
				case 1:
					end=st.nextToken();
					break;
				case 2:
					title=st.nextToken();
					break;
				case 3:
					sheets=st.nextToken();
					break;
				}
			}
			
			musicInfo mi=new musicInfo(++count);
			mi.time=minute(end)-minute(start);
			mi.title=title;
			
			for(int i=0;i<sheets.length();i++) {
				String s=Character.toString(sheets.charAt(i));
				if(i!=sheets.length()-1&&sheets.charAt(i+1)=='#') {
					s+="#";
					i++;
				}
				mi.sheets.add(s);
			}
			
			infos.add(mi);
		}
		
		String result=getAns();
		if(result.equals(""))
			System.out.println("None");
		else
			System.out.println(result);
	}
	
	public static String getAns() {
		Collections.sort(infos);
		String result="";
		for(musicInfo info:infos) {
			List<String> list=new ArrayList<String>();
			int len=info.sheets.size();
			for(int i=0;i<info.time;i++) {
				list.add(info.sheets.get(i%len));
			}
			
			
			boolean flag=false;
			//list에 sheets가 있는지 확인
			for(int i=0;i<=list.size()-input.size();i++) {
				if(list.get(i).equals(input.get(0))) {//첫 글자가 같다면
					flag=true;
					for(int j=1;j<input.size();j++) {//input 길이만큼 확인
						if(!list.get(i+j).equals(input.get(j))){
							flag=false;
							break;
						}
					}
					if(flag)
						break;
				}
			}
			if(flag) {
				result=info.title;
				break;
			}
		}
		return result;
	}
	
	public static int minute(String time) {
		StringTokenizer st=new StringTokenizer(time,":");
		int hour=Integer.parseInt(st.nextToken());
		int min=Integer.parseInt(st.nextToken());
		return hour*60+min;
	}
}

class musicInfo implements Comparable<musicInfo>{
	int time;
	String title;
	List<String> sheets;
	int count;//입력 순서
	musicInfo(int count){
		this.sheets=new ArrayList<String>();
		this.count=count;
	}

	@Override
	public int compareTo(musicInfo o) {
		// TODO Auto-generated method stub
		if(this.time!=o.time)
			return o.time-this.time;
		else
			return this.count-o.count;
	}
}