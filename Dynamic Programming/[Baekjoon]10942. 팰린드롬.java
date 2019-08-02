/* https://www.acmicpc.net/problem/10942 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    static int[] num;
    static int[][] cache;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        num = new int[cnt+1];
        cache = new int[cnt+1][cnt+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= cnt; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int cmdCnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= cmdCnt; i++) {
            st = new StringTokenizer(br.readLine());
            if(isPalindrom(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) == 2){
                sb.append("1\n");
            }
            else {
                sb.append("0\n");
            }
        }

        System.out.println(sb.toString());

    }

    public static int isPalindrom(int s,int e) {
    	if(s==e)
    		return 2;
    	if(s+1==e) {
    		if(num[s]==num[e])
    			return 2;
    		else
    			return 1;
    	}
    			
    	if(cache[s][e]>=1)
    		return cache[s][e];
    	
    	if(num[s]!=num[e])
    		cache[s][e]= 1;
    	else
    		cache[s][e]=isPalindrom(s+1,e-1);
    	
    	return cache[s][e];
    }

}
