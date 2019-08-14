/* https://www.acmicpc.net/problem/17140 */

import java.util.*;
import java.io.*;

public class Main {
	public static int[][] map;
	public static int count;
	public static List<ArrayList<Integer>> sort;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st=new StringTokenizer(br.readLine());
		int r=Integer.parseInt(st.nextToken());
		int c=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		map=new int[3][3];
		for(int i=0;i<3;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		count=0;
		while(count<=100) {
			if(map.length>=r&&map[0].length>=c&&map[r-1][c-1]==k)
				break;
			if(map.length<=100||map[0].length<=100) {
				if(map.length>=map[0].length) {
					map=col();
				}else {
					map=row();
				}				
			}else
				break;
			count++;
		}
		
		if(count<=100)
			System.out.println(count);
		else
			System.out.println(-1);
	}
	
	public static int[][] col() {
		int[][] result;
		sort=new ArrayList<ArrayList<Integer>>();//정렬 결과를 임시적으로 담을 곳
		int max=0;//정렬 결과 최장 길이
		for(int i=0;i<map.length;i++) {//행 탐색
			int[][] input=new int[101][2];//계수 배열
			for(int j=0;j<map[i].length;j++) {//계수
				if(input[map[i][j]][0]==0)
					input[map[i][j]][0]=map[i][j];
				input[map[i][j]][1]++;
			}
			Arrays.sort(input,new Comparator<int[]>() {//등장 횟수 -> 수 크기 순으로 정렬

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[1]!=o2[1])
						return o1[1]-o2[1];
					else
						return o1[0]-o2[0];
				}
				
			});
			sort.add(new ArrayList<Integer>());
			int count=0;
			for(int j=0;j<input.length;j++) {
				if(input[j][0]==0)
					continue;
				sort.get(i).add(input[j][0]);
				sort.get(i).add(input[j][1]);
				count+=2;
			}
			max=Math.max(max,count);
		}
		result=new int[map.length][max];
		for(int i=0;i<sort.size();i++) {
			for(int j=0;j<sort.get(i).size();j++) {
				result[i][j]=sort.get(i).get(j);
			}
		}
		
		return result;
	}
	
	public static int[][] row() {
		int[][] result;
		sort=new ArrayList<ArrayList<Integer>>();//정렬 결과를 임시적으로 담을 곳
		int max=0;//정렬 결과 최장 길이
		for(int i=0;i<map[0].length;i++) {//열 탐색
			int[][] input=new int[101][2];//계수 배열
			for(int j=0;j<map.length;j++) {//계수
				if(input[map[j][i]][0]==0)
					input[map[j][i]][0]=map[j][i];
				input[map[j][i]][1]++;
			}
			Arrays.sort(input,new Comparator<int[]>() {//등장 횟수 -> 수 크기 순으로 정렬

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if(o1[1]!=o2[1])
						return o1[1]-o2[1];
					else
						return o1[0]-o2[0];
				}
				
			});
			sort.add(new ArrayList<Integer>());
			int count=0;
			for(int j=0;j<input.length;j++) {
				if(input[j][0]==0)
					continue;
				sort.get(i).add(input[j][0]);
				sort.get(i).add(input[j][1]);
				count+=2;
			}
			max=Math.max(max,count);
		}
		result=new int[max][map[0].length];
		for(int i=0;i<sort.size();i++) {
			for(int j=0;j<sort.get(i).size();j++) {
				result[j][i]=sort.get(i).get(j);
			}
		}
		
		return result;
	}
}