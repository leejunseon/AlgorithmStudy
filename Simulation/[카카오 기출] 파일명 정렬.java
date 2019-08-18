/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;
import java.io.*;

public class Main {
	public static List<File> files;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine(),"[¡°¡±,]");
		files=new LinkedList<File>();
		while(st.hasMoreTokens()) {
			String file=st.nextToken();
			if(file.equals(" "))
				continue;
			String head="";
			String number="";
			String tail="";
			
			for(int i=0;i<file.length();i++) {
				char c=file.charAt(i);
				if(48<=(int)c&&(int)c<=57) {
					head=file.substring(0,i);
					file=file.substring(i,file.length());
					break;
				}
			}
			
			for(int i=0;i<file.length();i++) {
				char c=file.charAt(i);
				if((int)c<48||(int)c>57) {
					number=file.substring(0,i);
					if(file.length()!=0) {
						file=file.substring(i,file.length());
						tail=file;
					}
					break;
				}else {
					if(i==file.length()-1);
						number=file;
				}
			}
			
			files.add(new File(head,number,tail));
		}
		
		Collections.sort(files);
		
		for(File f:files) {
			System.out.println(f.head+f.number+f.tail);
		}
	}
}

class File implements Comparable<File>{
	String head;
	String number;
	String tail;
	File(String head,String number,String tail){
		this.head=head;
		this.number=number;
		this.tail=tail;
	}
	
	@Override
	public int compareTo(File o) {
		// TODO Auto-generated method stub
		if(!this.head.toLowerCase().equals(o.head.toLowerCase()))
			return this.head.compareToIgnoreCase(o.head);
		else {
			int This=0;
			int That=0;
			
			if(this.number.charAt(0)!='0')
				This=Integer.parseInt(this.number);
			else {
				for(int i=0;i<this.number.length();i++) {
					if(this.number.charAt(i)=='0') {
						This=Integer.parseInt(this.number.substring(i,this.number.length()));
						break;
					}
				}
			}
			
			if(o.number.charAt(0)!='0')
				That=Integer.parseInt(o.number);
			else {
				for(int i=0;i<o.number.length();i++) {
					if(o.number.charAt(i)=='0') {
						That=Integer.parseInt(o.number.substring(i,o.number.length()));
						break;
					}
				}
			}
			
			return This-That;
		}
	}
}