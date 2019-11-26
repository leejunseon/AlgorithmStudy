/* https://www.acmicpc.net/problem/4781 */
import java.util.*;

public class Main {
	static int n;
	static int m;
	static int[] cache;
	static int[] cost;
	static int[] calorie;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			n=sc.nextInt();//사탕 종류 수
			m=(int)sc.nextDouble()*100;//돈의 양
			if(n==0)
				break;
			cache=new int[m+1];
			Arrays.fill(cache, -1);
			cost=new int[n];
			calorie=new int[n];
			for(int i=0;i<n;i++) {
				calorie[i]=sc.nextInt();
				cost[i]=(int)(sc.nextDouble()*100+0.5);
			}
			System.out.println(maxCalorie(m));
		}
	}

	public static int maxCalorie(int money) {
		if(cache[money]!=-1)
			return cache[money];

		cache[money]=0;
		 for(int i = 0; i < n; i++){
		     if(money>=cost[i]){
		    	 cache[money] = Math.max(cache[money], calorie[i] + maxCalorie(money-cost[i]));
		     }
		 }
		 return cache[money];
	}
}
