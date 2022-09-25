package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1로만들기_1463 {
	
	static int N;
	
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		dp = new int[N + 1];
		
		if (N == 1) System.out.println(0);
		else if (N == 2) System.out.println(1);
		else if (N == 3) System.out.println(1);
		else {
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[0] = 0;
			dp[1] = 0;
			dp[2] = 1;
			dp[3] = 1;
			
			for (int i = 4; i <= N; i++) {
				if (i % 3 == 0) dp[i] = dp[i / 3] + 1;
				if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
				dp[i] = Math.min(dp[i], dp[i - 1] + 1);
			}
			
			System.out.println(dp[N]);
		}
	}
}
