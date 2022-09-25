import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1463_1로만들기 {
	static int[] dp;
	static int num;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		num = Integer.parseInt(br.readLine());

		dp = new int[1000001];

		/**
		 * 4 : 4 -> 2 -> 1  / '2'
		 * 5 : 5 -> 4 -> 2 -> 1 / '3'
		 * 8 : 8 -> 4 -> 2 -> 1 / '3'
		 * 6 : 6 -> 2 -> 1  /  '2'
		 * 7 : 7 -> 6 -> 2 -> 1 / '3' 
		 * 
		 * 
		 * 
		 *  num-1을 1로 만들기 위한 최소횟수 + 1
		 *  2나3으로 나누어 떨어지면, 남은 몫의 경우의 연산 + 1
		 */
		for (int i = 2; i <= num; i++) {
			dp[i] = dp[i - 1] + 1;

			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}
		
		System.out.println(dp[num]);

	}
}
