// SWEA 1952번 [모의 SW 역량테스트] 수영장

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1952_dp {

	static int T;
	static int[] price, plan;
	static int[] src, tgt;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			price = new int[4];
			plan = new int[13]; // 0 dummy

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());

			}

			int[] dp = new int[12 + 1];
			for (int i = 1; i <= 12; i++) {
				int DAY = dp[i - 1] + price[0] * plan[i]; // 1일
				int MONTH = dp[i - 1] + price[1]; // 1달
				int MON3 = (i >= 3) ? dp[i - 3] + price[2] : Integer.MAX_VALUE; // 3달
				int YEAR = (i == 12) ? price[3] : Integer.MAX_VALUE; // 1년

				dp[i] = Math.min(DAY, Math.min(MONTH, Math.min(MON3, YEAR)));
			}

			sb.append("#").append(t).append(" ").append(dp[12]).append("\n");
		}

		System.out.println(sb.toString());

	}

}
