// BOJ 11727번 2 x n 타일링 2

package BOJ.Dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_11727 {

	static int N;
	static int[] dp = new int[1001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 2] * 2 + dp[i - 1]) % 10007;
		}

		System.out.println(dp[N]);
	}

}
