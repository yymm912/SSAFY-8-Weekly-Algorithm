package _4주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ10844_쉬운계단수 {
	static int [][] dp;
	static int N;
	static int mod = 1000000000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[10][N+1];
		for(int i = 1;i<=9;i++) 
			dp[i][1] = 1;
		for(int i = 2;i<=N;i++) {
			for(int j = 0;j<=9;j++) {
				if(!(j == 0)) dp[j][i] += dp[j-1][i-1];
				if(!(j == 9)) dp[j][i] += dp[j+1][i-1];
				dp[j][i] %= mod;
			}
		}
		int answer = 0;
		for(int i = 0;i<=9;i++) {
			answer += dp[i][N];
			answer %= mod;
		}
		
		System.out.println(answer%mod);

	}

}
