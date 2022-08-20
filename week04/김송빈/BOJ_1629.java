package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629 {
	static long A,B,C;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		A=Integer.parseInt(st.nextToken());
		B=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		System.out.println(dac(A,B));
	}
	
	static long dac(long a,long b) {
		if(b==1)return a%C;
		
		long tmp=dac(a,b/2);
		
		//a^(b/2)*a^(b/2)
		if(b%2==0)return tmp*tmp%C;
		//a^(b/2)*a^(b/2)*a
		else return (((tmp*tmp)%C)*a)%C;
	}
}
