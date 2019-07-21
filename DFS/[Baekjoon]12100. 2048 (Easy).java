/* https://www.acmicpc.net/problem/12100 */

import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		int[][] map=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int result=0;
		for(int i=1;i<=4;i++)
			result=Math.max(result, maxValue(1,i,map));
		
		System.out.println(result);
	}
	
	//1:상
	//2:하
	//3:좌
	//4:우
	public static int maxValue(int num,int direction,int[][] map) {
		int result=0;
		if(num<=5) {
			int[][] mapClone=deepCopy(map);
			move(mapClone,direction);
			result=calcMax(mapClone);
			for(int i=1;i<=4;i++) {
				result=Math.max(result, maxValue(num+1,i,mapClone));
			}
		}
			
		return result;
	}
	
	public static void move(int[][] map,int direction) {
		List<entity> list;
		switch(direction) {
		case 1://상
			for(int i=0;i<n;i++) {
				list=new ArrayList<entity>();
				for(int j=0;j<n;j++) {
					if(map[j][i]==0)
						continue;
					if(list.isEmpty())
						list.add(new entity(map[j][i],true));
					else {
						if(list.get(list.size()-1).add==true) {
							if(list.get(list.size()-1).value==map[j][i]) {
								list.get(list.size()-1).value+=map[j][i];
								list.get(list.size()-1).add=false;
							}
							else
								list.add(new entity(map[j][i],true));
						}else
							list.add(new entity(map[j][i],true));
					}
				}
				for(int j=0;j<n;j++) {
					if(j<=list.size()-1) {
						map[j][i]=list.get(j).value;
					}
					else
						map[j][i]=0;
				}
			}
			break;
		case 2://하
			for(int i=0;i<n;i++) {
				list=new ArrayList<entity>();
				for(int j=n-1;j>=0;j--) {
					if(map[j][i]==0)
						continue;
					if(list.isEmpty())
						list.add(new entity(map[j][i],true));
					else {
						if(list.get(list.size()-1).add==true) {
							if(list.get(list.size()-1).value==map[j][i]) {
								list.get(list.size()-1).value+=map[j][i];
								list.get(list.size()-1).add=false;
							}
							else
								list.add(new entity(map[j][i],true));
						}else
							list.add(new entity(map[j][i],true));
					}
				}
				for(int j=0;j<n;j++) {
					if(j<=list.size()-1) {
						map[j][i]=list.get(j).value;
					}
					else
						map[j][i]=0;
				}
			}
			break;
		case 3://좌
			for(int i=0;i<n;i++) {
				list=new ArrayList<entity>();
				for(int j=0;j<n;j++) {
					if(map[i][j]==0)
						continue;
					if(list.isEmpty())
						list.add(new entity(map[i][j],true));
					else {
						if(list.get(list.size()-1).add==true) {
							if(list.get(list.size()-1).value==map[i][j]) {
								list.get(list.size()-1).value+=map[i][j];
								list.get(list.size()-1).add=false;
							}
							else
								list.add(new entity(map[i][j],true));
						}else
							list.add(new entity(map[i][j],true));
					}
				}
				for(int j=0;j<n;j++) {
					if(j<=list.size()-1) {
						map[i][j]=list.get(j).value;
					}
					else
						map[i][j]=0;
				}
			}
			break;
		case 4://우
			for(int i=0;i<n;i++) {
				list=new ArrayList<entity>();
				for(int j=n-1;j>=0;j--) {
					if(map[i][j]==0)
						continue;
					if(list.isEmpty())
						list.add(new entity(map[i][j],true));
					else {
						if(list.get(list.size()-1).add==true) {
							if(list.get(list.size()-1).value==map[i][j]) {
								list.get(list.size()-1).value+=map[i][j];
								list.get(list.size()-1).add=false;
							}
							else
								list.add(new entity(map[i][j],true));
						}else
							list.add(new entity(map[i][j],true));
					}
				}
				for(int j=0;j<n;j++) {
					if(j<=list.size()-1) {
						map[i][j]=list.get(j).value;
					}
					else
						map[i][j]=0;
				}
			}
			break;
		}
	}
	
	public static int calcMax(int[][] map) {
		int result=0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				result=Math.max(result, map[i][j]);
			}
		}
		return result;
	}
	
	public static int[][] deepCopy(int[][] original){
		int[][] copy=new int[original.length][original[0].length];
		
		for(int i=0;i<original.length;i++) {
			System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
		}
		
		return copy;
	}
}

class entity{
	int value;//값
	boolean add;//합쳐질 수 있는지
	entity(int value,boolean add){
		this.value=value;
		this.add=add;
	}
}