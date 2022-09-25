import java.util.Scanner;

public class BJ_11727_2xn타일링2 {
	
	/**
	 * n = 1 -> 1
	 * n = 2 -> 3
	 * n = 3 -> 5
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] dp = new int[1001];
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= n; i++)
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;

		System.out.println(dp[n]);

	}

}
