/* https://www.acmicpc.net/problem/16637 */

import java.util.*;
import java.io.*;

public class Main {
   public static String Input;
   public static List<Integer> high;

   public static void main(String[] args) throws IOException{
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

      int n=Integer.parseInt(br.readLine());
      Input=br.readLine();

      int[] use=new int[n/2];
      System.out.println(getMax(use,-1));
   }

   public static int getMax(int[] use,int last) {
      int result=Integer.MIN_VALUE;
      int[] copy=use.clone();

      high=new ArrayList<Integer>();
      result=Math.max(result, calc(copy));

      for(int i=last+1;i<copy.length;i++) {
         if(i>=1&&copy[i-1]==1)
            continue;
         copy[i]=1;
         result=Math.max(result, getMax(copy,i));
         copy[i]=0;
      }

      return result;
   }

   public static int calc(int[] use) {
      List<Character> list=new ArrayList<Character>();

      for(int i=0;i<use.length;i++) {
         if(use[i]==1)
            high.add(2*i+1);
      }

      //후위표기식으로 만들기
      Stack<Character> addop=new Stack<Character>();
      for(int i=0;i<Input.length();i++) {
         if('0'<=Input.charAt(i)&&Input.charAt(i)<='9') {
            list.add(Input.charAt(i));//숫자
         }else {
            if(addop.isEmpty())
               addop.add(Input.charAt(i));
            else {
               if(high.contains(i)){
                  addop.push(Input.charAt(i));
               }else {
                  while(!addop.isEmpty()){
                     list.add(addop.pop());
                  }
                  addop.push(Input.charAt(i));
               }
            }
         }
      }
      while(!addop.isEmpty()) {
         list.add(addop.pop());
      }

      //연산
      Stack<Integer> calc=new Stack<Integer>();
      for(int i=0;i<list.size();i++) {
         if('0'<=list.get(i)&&list.get(i)<='9') {
            calc.add(list.get(i)-'0');
         }else {
            int b=calc.pop();
            int a=calc.pop();
            switch(list.get(i)) {
            case '+':
               calc.push(a+b);
               break;
            case '-':
               calc.push(a-b);
               break;
            case '*':
               calc.push(a*b);
               break;
            }
         }
      }

      return calc.pop();
   }
}
