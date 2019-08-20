/* http://tech.kakao.com/2018/09/21/kakao-blind-recruitment-for2019-round-1/ */

import java.util.*;
import java.io.*;

public class Main {
	public static Map<String,String> userList;
	public static List<Process> processes;
	public static void main(String[] args) throws IOException{
		
		String[] record= {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
		String[] result=solution(record);
				
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i]);
		}
	}
	
	public static String[] solution(String[] record) {
		userList=new HashMap<String,String>();
		processes=new ArrayList<Process>();
		for(int len=0;len<record.length;len++) {
			StringTokenizer st=new StringTokenizer(record[len]," ");
			String status="";
			String id="";
			String name="";
			for(int i=0;i<3;i++) {
				switch(i) {
				case 0:
					status=st.nextToken();
					break;
				case 1:
					id=st.nextToken();
					break;
				case 2:
					if(!status.equals("Leave"))
						name=st.nextToken();
					break;
				}
			}
			if(!status.equals("Leave"))
				userList.put(id, name);
			if(status.equals("Enter")||status.equals("Leave"))
				processes.add(new Process(status,id));
		}
		
		String[] result=new String[processes.size()];
		int n=0;
		for(Process p:processes) {
			String name=userList.get(p.id);
			switch(p.action) {
			case "Enter":
				result[n++]=name+"´ÔÀÌ µé¾î¿Ô½À´Ï´Ù.";
				break;
			case "Leave":
				result[n++]=name+"´ÔÀÌ ³ª°¬½À´Ï´Ù.";
				break;
			}
		}
		
		return result;
	}
}

class Process{
	String action;
	String id;
	Process(String action,String id){
		this.action=action;
		this.id=id;
	}
}