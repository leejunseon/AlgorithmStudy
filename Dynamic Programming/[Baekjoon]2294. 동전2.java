/* https://www.acmicpc.net/problem/2294 */

import java.util.*;

public class Main {
   static int[] cache;
   static int[] coin;
   static int Max=987654321;
   
   public static void main(String[] args) {
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      int k=sc.nextInt();
      cache=new int[k+1];
      Arrays.fill(cache, -1);//-1
      coin=new int[n];
      for(int i=0;i<n;i++)
         coin[i]=sc.nextInt();
      
      int ans=minValue(k);
      if(ans==Max)
         System.out.println(-1);
      else
         System.out.println(ans);
   }
   
   public static int minValue(int k) {
      if(k<=0)
         return 0;
      
      if(cache[k]!=-1)//cache를 Max로 저장해버리면 실행했지만 계산이 불가하여 Max로 저장된 것까지 다시 다 실행하게 됨
         return cache[k];
      
      cache[k]=Max;//Max로 변경을 여기서
      for(int i=0;i<coin.length;i++) {
         if(k-coin[i]>=0)
            cache[k]=Math.min(cache[k], minValue(k-coin[i])+1);
      }
      return cache[k];
   }
}