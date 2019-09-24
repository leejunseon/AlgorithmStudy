/* https://programmers.co.kr/learn/courses/30/lessons/42583 */
//큐 두개(기다리는 트럭, 다리 지나는 트럭) 사용. 

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		int bridge_length=2;
		int weight=10;
		int[] truck_weights= {7,4,5,6};
		System.out.println(solution(bridge_length,weight,truck_weights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Truck> waiting=new LinkedList<Truck>();
        Queue<Truck> bridge=new LinkedList<Truck>();

        for(int i=0;i<truck_weights.length;i++) {
        	waiting.add(new Truck(truck_weights[i],bridge_length));
        }

        int wholeWeight=0;
        while(true) {
        	for(Iterator<Truck> it=bridge.iterator();it.hasNext();) {
        		Truck t=it.next();
        		t.remain--;
        		if(t.remain==0) {
        			wholeWeight-=t.weight;
        			it.remove();
        		}
        	}

        	if(!waiting.isEmpty()&&wholeWeight+waiting.peek().weight<=weight) {
        		wholeWeight+=waiting.peek().weight;
        		bridge.add(waiting.remove());
        	}

        	answer++;
        	if(bridge.size()==0)
        		break;
        }

        return answer;
    }
}

class Truck{
	int weight;
	int remain;
	Truck(int weight,int remain){
		this.weight=weight;
		this.remain=remain;
	}
}
