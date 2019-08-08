/* https://www.acmicpc.net/problem/5373 */

import java.util.*;
import java.io.*;

public class Main {
	public static char[][] U;
	public static char[][] B;
	public static char[][] F;
	public static char[][] L;
	public static char[][] R;
	public static char[][] D;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		U=new char[3][3];
		B=new char[3][3];
		F=new char[3][3];
		L=new char[3][3];
		R=new char[3][3];
		D=new char[3][3];
		
		int test=Integer.parseInt(br.readLine());
		for(int i=0;i<test;i++) {
			for(int j=0;j<3;j++) {
				Arrays.fill(U[j], 'w');
				Arrays.fill(B[j], 'o');
				Arrays.fill(F[j], 'r');
				Arrays.fill(L[j], 'g');
				Arrays.fill(R[j], 'b');
				Arrays.fill(D[j], 'y');
			}
			int num=Integer.parseInt(br.readLine());
			st=new StringTokenizer(br.readLine());
			List<String> direction=new ArrayList<String>();
			for(int j=0;j<num;j++)
				direction.add(st.nextToken());
			
			rotate(num,direction);
			for(int j=0;j<3;j++) {
				for(int k=0;k<3;k++) {
					System.out.print(U[j][k]);
				}
				System.out.println();
			}
		}		
	}
	
	public static void rotate(int num,List<String> direction) {
		String input="";
		for(int i=0;i<num;i++) {
			input=direction.get(i);
			char face=input.charAt(0);
			char clock=input.charAt(1);
			char[] temp=new char[3];
			switch(face) {
			case 'U':
				if(clock=='+') {
					rotate(U,'+');
					for(int j=0;j<3;j++) {
						temp[j]=B[0][j];
					}
					for(int j=0;j<3;j++) {
						B[0][j]=L[0][2-j];
					}
					for(int j=0;j<3;j++) {
						L[0][j]=F[0][j];
					}
					for(int j=0;j<3;j++) {
						F[0][j]=R[0][j];
					}
					for(int j=0;j<3;j++) {
						R[0][j]=temp[2-j];
					}
				}else if(clock=='-') {
					rotate(U,'-');
					for(int j=0;j<3;j++) {
						temp[j]=B[0][j];
					}
					for(int j=0;j<3;j++) {
						B[0][j]=R[0][2-j];
					}
					for(int j=0;j<3;j++) {
						R[0][j]=F[0][j];
					}
					for(int j=0;j<3;j++) {
						F[0][j]=L[0][j];
					}
					for(int j=0;j<3;j++) {
						L[0][j]=temp[2-j];
					}
				}
				break;
			case 'B':
				if(clock=='+') {
					rotate(B,'-');
					for(int j=0;j<3;j++) {
						temp[j]=U[0][j];
					}
					for(int j=0;j<3;j++) {
						U[0][j]=R[j][2];
					}
					for(int j=0;j<3;j++) {
						R[j][2]=D[0][2-j];
					}
					for(int j=0;j<3;j++) {
						D[0][j]=L[j][0];
					}
					for(int j=0;j<3;j++) {
						L[j][0]=temp[2-j];
					}
				}else if(clock=='-') {
					rotate(B,'+');
					for(int j=0;j<3;j++) {
						temp[j]=U[0][j];
					}
					for(int j=0;j<3;j++) {
						U[0][j]=L[2-j][0];
					}
					for(int j=0;j<3;j++) {
						L[j][0]=D[0][j];
					}
					for(int j=0;j<3;j++) {
						D[0][j]=R[2-j][2];
					}
					for(int j=0;j<3;j++) {
						R[j][2]=temp[j];
					}
				}
				break;
			case 'F':
				if(clock=='+') {
					rotate(F,'+');
					for(int j=0;j<3;j++) {
						temp[j]=U[2][j];
					}
					for(int j=0;j<3;j++) {
						U[2][j]=L[2-j][2];
					}
					for(int j=0;j<3;j++) {
						L[j][2]=D[2][j];
					}
					for(int j=0;j<3;j++) {
						D[2][j]=R[2-j][0];
					}
					for(int j=0;j<3;j++) {
						R[j][0]=temp[j];
					}
				}else if(clock=='-') {
					rotate(F,'-');
					for(int j=0;j<3;j++) {
						temp[j]=U[2][j];
					}
					for(int j=0;j<3;j++) {
						U[2][j]=R[j][0];
					}
					for(int j=0;j<3;j++) {
						R[j][0]=D[2][2-j];
					}
					for(int j=0;j<3;j++) {
						D[2][j]=L[j][2];
					}
					for(int j=0;j<3;j++) {
						L[j][2]=temp[2-j];
					}
				}
				break;
			case 'L':
				if(clock=='+') {
					rotate(L,'+');
					for(int j=0;j<3;j++) {
						temp[j]=U[j][0];
					}
					for(int j=0;j<3;j++) {
						U[j][0]=B[2-j][0];
					}
					for(int j=0;j<3;j++) {
						B[j][0]=D[j][0];
					}
					for(int j=0;j<3;j++) {
						D[j][0]=F[2-j][0];
					}
					for(int j=0;j<3;j++) {
						F[j][0]=temp[j];
					}
				}else if(clock=='-') {
					rotate(L,'-');
					for(int j=0;j<3;j++) {
						temp[j]=U[j][0];
					}
					for(int j=0;j<3;j++) {
						U[j][0]=F[j][0];
					}
					for(int j=0;j<3;j++) {
						F[j][0]=D[2-j][0];
					}
					for(int j=0;j<3;j++) {
						D[j][0]=B[j][0];
					}
					for(int j=0;j<3;j++) {
						B[j][0]=temp[2-j];
					}
				}
				break;
			case 'R':
				if(clock=='+') {
					rotate(R,'+');
					for(int j=0;j<3;j++) {
						temp[j]=U[j][2];
					}
					for(int j=0;j<3;j++) {
						U[j][2]=F[j][2];
					}
					for(int j=0;j<3;j++) {
						F[j][2]=D[2-j][2];
					}
					for(int j=0;j<3;j++) {
						D[j][2]=B[j][2];
					}
					for(int j=0;j<3;j++) {
						B[j][2]=temp[2-j];
					}
				}else if(clock=='-') {
					rotate(R,'-');
					for(int j=0;j<3;j++) {
						temp[j]=U[j][2];
					}
					for(int j=0;j<3;j++) {
						U[j][2]=B[2-j][2];
					}
					for(int j=0;j<3;j++) {
						B[j][2]=D[j][2];
					}
					for(int j=0;j<3;j++) {
						D[j][2]=F[2-j][2];
					}
					for(int j=0;j<3;j++) {
						F[j][2]=temp[j];
					}
				}
				break;
			case 'D':
				if(clock=='+') {
					rotate(D,'-');
					for(int j=0;j<3;j++) {
						temp[j]=B[2][j];
					}
					for(int j=0;j<3;j++) {
						B[2][j]=R[2][2-j];
					}
					for(int j=0;j<3;j++) {
						R[2][j]=F[2][j];
					}
					for(int j=0;j<3;j++) {
						F[2][j]=L[2][j];
					}
					for(int j=0;j<3;j++) {
						L[2][j]=temp[2-j];
					}
				}else if(clock=='-') {
					rotate(D,'+');
					for(int j=0;j<3;j++) {
						temp[j]=B[2][j];
					}
					for(int j=0;j<3;j++) {
						B[2][j]=L[2][2-j];
					}
					for(int j=0;j<3;j++) {
						L[2][j]=F[2][j];
					}
					for(int j=0;j<3;j++) {
						F[2][j]=R[2][j];
					}
					for(int j=0;j<3;j++) {
						R[2][j]=temp[2-j];
					}
				}
				break;
			}
		}
	}
	
	public static void rotate(char[][] face,char dir) {
		char[][] temp=new char[3][3];
		switch(dir) {
		case '+':
			for(int i=0;i<3;i++) {
				temp[0][i]=face[2-i][0];
			}
			for(int i=1;i<3;i++) {
				temp[i][2]=face[0][i];
			}
			for(int i=1;i>=0;i--) {
				temp[2][i]=face[2-i][2];
			}
			temp[1][0]=face[2][1];
			temp[1][1]=face[1][1];
			break;
		case '-':
			for(int i=0;i<3;i++) {
				temp[0][i]=face[i][2];
			}
			for(int i=1;i<3;i++) {
				temp[3-i][2]=face[2][i-1];
			}
			for(int i=0;i<2;i++) {
				temp[2][i]=face[i][0];
			}
			temp[1][0]=face[0][1];
			temp[1][1]=face[1][1];
			break;
		}

		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				face[i][j]=temp[i][j];
			}
		}
	}
}