/* http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/ */

import java.util.*;

import java.io.*;

public class Main {
	public static Map<String,Integer> dictionary;
	public static List<Integer> result;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		String input=br.readLine();
		dictionary=new HashMap<String,Integer>();
		result=new ArrayList<Integer>();
		for(int i=1;i<=26;i++)
			dictionary.put(Character.toString((char)(64+i)),i);
		
		for(int i=0;i<input.length();i++) {
			for(int j=i+1;j<=input.length();j++) {
				String sub=input.substring(i,j);//���� �Է�
				if(j+1<=input.length()) {
					if(dictionary.containsKey(input.substring(i,j+1))) {//���� ���������ص� ������
						continue;
					}
					else{//���� ���ڱ��� ������
						result.add(dictionary.get(sub));
						dictionary.put(input.substring(i,j+1), dictionary.size()+1);
						input=input.substring(sub.length(),input.length());
						i--;
						break;
					}
				}else {
					result.add(dictionary.get(sub));
					input=input.substring(sub.length(),input.length());
				}
			}
		}
		
		for(int n:result)
			System.out.println(n);
	}
}