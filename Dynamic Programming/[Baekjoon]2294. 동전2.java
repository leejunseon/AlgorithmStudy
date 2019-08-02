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
      
      if(cache[k]!=-1)//cache�� Max�� �����ع����� ���������� ����� �Ұ��Ͽ� Max�� ����� �ͱ��� �ٽ� �� �����ϰ� ��
         return cache[k];
      
      cache[k]=Max;//Max�� ������ ���⼭
      for(int i=0;i<coin.length;i++) {
         if(k-coin[i]>=0)
            cache[k]=Math.min(cache[k], minValue(k-coin[i])+1);
      }
      return cache[k];
   }
}