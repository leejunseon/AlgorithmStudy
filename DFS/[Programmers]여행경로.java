/* https://programmers.co.kr/learn/courses/30/lessons/43164 */
//주어진 tickets배열의 내용을 Graph로 만들고
//알파벳 순서가 앞서는 경로를 반환하기 위해 Graph를 사전순 정렬한다.
//ICN부터 시작해 dfs탐색을 시작한다. 처음으로 remain(남은 티켓)이 0이 되는 경우가 정답.

import java.util.*;

public class Solution {
	public static List<String> result;
	public static Map<String,Integer> map;
	public static ArrayList<ArrayList<Place>> Graph;
	public static int[] check;
	public static String begin;
	public static boolean flag=false;

	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
		String[] result=solution(tickets);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}

	public static String[] solution(String[][] tickets) {
        String[] answer = {};
        map=new HashMap<String,Integer>();
        int value=0;
        for(int i=0;i<tickets.length;i++) {
        	for(int j=0;j<2;j++) {
	        	if(!map.containsKey(tickets[i][j]))
	        		map.put(tickets[i][j], value++);
        	}
        }

        Graph=new ArrayList<ArrayList<Place>>();
        for(int i=0;i<map.keySet().size();i++)
        	Graph.add(new ArrayList<Place>());

        for(int i=0;i<tickets.length;i++) {
        	Graph.get(map.get(tickets[i][0])).add(new Place(tickets[i][1]));
        }
        for(int i=0;i<Graph.size();i++) {
        	Collections.sort(Graph.get(i),new Comparator<Place>() {

				@Override
				public int compare(Place o1, Place o2) {
					// TODO Auto-generated method stub
					return o1.name.compareTo(o2.name);
				}

        	});
        }

        List<String> route=new ArrayList<String>();
        route.add("ICN");
        start("ICN",tickets.length,route);

        answer=new String[result.size()];
        for(int i=0;i<answer.length;i++)
        	answer[i]=result.get(i);

        return answer;
    }

	public static void start(String Now,int remain,List<String> route) {
		if(flag)
			return;

		int now=map.get(Now);

		if(remain==0) {
			result=new ArrayList<String>(route);
			flag=true;
			return;
		}

		for(Place p:Graph.get(now)) {
			if(!p.visited) {
				p.visited=true;
				route.add(p.name);
				start(p.name,remain-1,route);
				p.visited=false;
				route.remove(route.size()-1);
			}
		}
	}
}

class Place{
	String name;
	boolean visited;
	Place(String name){
		this.name=name;
		this.visited=false;
	}
}
