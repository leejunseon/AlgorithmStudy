/* https://programmers.co.kr/learn/courses/30/lessons/42579 */

//HashMap을 이용해 genre별 재생수 구함
//music 객체 모아놓은 list 정렬 -> 1.genre재생수 내림차순 / 2.재생수 내림차순 / 3.고유번호 오름차순
//정렬된 상태에서 차례로 탐색하면서 genre별 2개만 result에 담음.
//문제에서 요구하는 자료구조 파악 중요. 이문제는 HashMap

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		String[] genres= {"classic","pop","classic","classic","pop"};
		int[] plays= {500,600,150,800,2500};

		int[] result=solution(genres,plays);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
	}

	public static int[] solution(String[] genres, int[] plays) {
        Map<String,Integer> map=new HashMap<String,Integer>();
        List<Music> list=new LinkedList<Music>();
        for(int i=0;i<genres.length;i++) {
        	String key=genres[i];
        	if(map.get(key)==null) {
        		map.put(key, plays[i]);
        	}else {
        		map.put(key, map.get(key)+plays[i]);
        	}

        	list.add(new Music(genres[i],plays[i],i));
        }

        Collections.sort(list,new Comparator<Music>() {

			@Override
			public int compare(Music arg0, Music arg1) {
				// TODO Auto-generated method stub
				if(map.get(arg0.genre)!=map.get(arg1.genre)) {
					return map.get(arg1.genre)-map.get(arg0.genre);
				}else {
					if(arg0.plays!=arg1.plays) {
						return arg1.plays-arg0.plays;
					}else {
						return arg0.num-arg1.num;
					}
				}
			}

        });

        List<Integer> result=new ArrayList<Integer>();
        int count=0;
        String prev="";
        for(int i=0;i<list.size();i++) {
        	Music m=list.get(i);

        	if(prev.equals(m.genre)) {
        		count++;
        	}else
        		count=0;

        	if(count<2) {
        		result.add(m.num);
        		prev=m.genre;
        	}else
        		continue;
        }

        int[] answer=new int[result.size()];
        for(int i=0;i<result.size();i++) {
        	answer[i]=result.get(i);
        }
        return answer;
    }
}

class Music{
	String genre;
	int plays;
	int num;
	Music(String genre,int plays,int num){
		this.genre=genre;
		this.plays=plays;
		this.num=num;
	}
}
