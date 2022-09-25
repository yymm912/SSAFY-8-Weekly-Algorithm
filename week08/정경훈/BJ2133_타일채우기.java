package baekjoon.타일채우기_2133;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		// n = 0 : 1
		// n = 1 : 0
		// n = 2 : 3
		// n = 3 : 0
		// n = 4 : dp[2]*2 + 4개로만드는 유일한 경우의수 2 = 11
		// n = 5 : 0..
		// n = 6 : (4,2) (2,4) (2,2,2)
		// n = 8 : 
		// 홀수는 다 0이구나
		// dp[n] = dp[n-2]*dp[2] + n개로 만드는 유일한 경우의수
		if(N%2 == 1) {
			System.out.println(0);
			return;
		}
		dp[0] = 1;
		dp[2] = 3;
		for (int i = 4; i <= N; i+=2) {
			dp[i] = dp[i-2] * dp[2];
			for (int j = i-4; j >= 0; j-=2) {
				dp[i] += dp[j]*2;
			}
		}
		
		System.out.println(dp[N]);
	}
}
