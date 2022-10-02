// BOJ 1149번 RGB거리

package Class.D_0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	static int N, min;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][3];
		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			dp[i][0] = Integer.parseInt(st.nextToken()); // 빨강
			dp[i][1] = Integer.parseInt(st.nextToken()); // 초록
			dp[i][2] = Integer.parseInt(st.nextToken()); // 파랑
		}

		for (int i = 2; i < N + 1; i++) {
			// 앞의 색과 다른 두가지 중 작은 값을 더하기 
			dp[i][0] = dp[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
			dp[i][1] = dp[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
			dp[i][2] = dp[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
		}

		min = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));

		System.out.println(min);
	}

}
