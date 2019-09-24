/* https://programmers.co.kr/learn/courses/30/lessons/42586 */
//큐 사용해서 rate 100 넘은 것들 차례로 remove

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int[] progresses= {93,30,55};
		int[] speeds= {1,30,5};
		int[] result=solution(progresses,speeds);
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i]);
		}
	}

	public static int[] solution(int[] progresses, int[] speeds) {
        List<Integer> distribute=new ArrayList<Integer>();
        Queue<Function> progress=new LinkedList<Function>();
        for(int i=0;i<progresses.length;i++) {
        	progress.add(new Function(progresses[i],speeds[i]));
        }

        while(!progress.isEmpty()) {
        	for(Iterator<Function> it=progress.iterator();it.hasNext();) {
        		Function f=it.next();
        		f.rate+=f.speed;
        	}

        	int num=0;
        	while(!progress.isEmpty()&&progress.peek().rate>=100) {
        		num++;
        		progress.remove();
        	}
        	if(num!=0)
        		distribute.add(num);
        }

        int[] answer=new int[distribute.size()];
        for(int i=0;i<distribute.size();i++)
        	answer[i]=distribute.get(i);

        return answer;
    }
}

class Function{
	int rate;
	int speed;
	Function(int rate,int speed){
		this.rate=rate;
		this.speed=speed;
	}
}
