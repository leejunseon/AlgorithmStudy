import java.util.*;
 
 
public class Solution {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();//�׽�Ʈ���̽�
         
        for(int test_case = 1; test_case <= t; test_case++) {
            int n=sc.nextInt();//n
            ArrayList<Integer> numbers=new ArrayList<Integer>();//���������� ��� �迭
            int k=sc.nextInt();//k��°
            sc.nextLine();
            String input=sc.nextLine();//input ���ڿ�
            for(int i=0;i<n/4;i++) {//������ Ƚ����ŭ
                for(int j=0;j<4;j++) {//�װ��� ������
                    String split=input.substring(j*n/4,(j+1)*n/4);
                    int Dec=toDecimal(split);
                    if(!numbers.contains(Dec)) {
                        numbers.add(Dec);
                    }
                }
                String rotation=input.substring(0,n-1);
                input=input.substring(n-1)+rotation;
            }
             
            Collections.sort(numbers);
            Collections.reverse(numbers);
            System.out.println("#"+test_case+" "+numbers.get(k-1));
        }
         
         
    }
     
    //16������ 10������
    public static int toDecimal(String in) {
        int result=0;
        String Hex=reverseString(in);
        for(int i=0;i<Hex.length();i++) {
            String s=Hex.substring(i, i+1);
            if(isNumber(s)) {
                result+=Integer.parseInt(s)*Math.pow(16, i);
            }else {
                char c=Hex.charAt(i);
                int num = (int)c-55;
                result+=num*Math.pow(16, i);
            }
        }
         
        return result;
    }
         
    //�������� �ƴ���
    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }   
    }
     
    public static String reverseString(String s){
        return (new StringBuffer(s)).reverse().toString();
    }
}