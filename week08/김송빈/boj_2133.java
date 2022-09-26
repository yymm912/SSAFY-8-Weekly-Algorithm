package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_2133 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		dp[0] = 1;
		if (n >= 2&&n%2==0)
			dp[2] = 3;
		else {
			System.out.println(0);
			return;
		}
		for (int i = 4; i <= n; i += 2) {
			dp[i] = dp[i - 2] * 3;
			for (int j = i - 4; j >= 0; j -= 2) {
				dp[i] += dp[j] * 2;
			}
		}
		System.out.println(dp[n]);
	}
}
