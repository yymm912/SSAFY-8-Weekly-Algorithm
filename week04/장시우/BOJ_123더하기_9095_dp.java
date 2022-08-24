package boj;

import java.util.Scanner;

public class BOJ_123더하기_9095_dp {
	
	static int T, n;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for (int i = 0; i < T; i++) {
			n = sc.nextInt();
			
			dp = new int[n + 1];
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for (int j = 4; j <= n; j++) {
				dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
			}
			
			System.out.println(dp[n]);
		}
	}
}
