package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11727_2xn타일링 {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		int answer = 1;
		
		for(int i=1; i<=T; i++) {
			if( i%2==0) answer = (answer*2+1)%10007;
			else answer = (answer*2-1)%10007;
		}
		
		System.out.println(answer);
	}
	
}