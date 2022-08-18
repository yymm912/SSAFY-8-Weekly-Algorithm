package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_10844_쉬운계단수 {
	static int N;
	static long[][] ans = new long[101][10];
	static long result, mod = 1_000_000_000L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dp();

		for (int i = 0; i <= 9; i++) {
			result = (result + ans[N][i]) % mod;
		}
		System.out.println(result);
	}

	static void dp() {
		for (int i = 1; i <= 9; i++) {
			ans[1][i] = 1;
		}
		for (int i = 2; i <= N; i++) {
			ans[i][0] = ans[i - 1][1];
			for (int j = 1; j < 9; j++) {
				ans[i][j] = ((ans[i - 1][j - 1] % mod) + (ans[i - 1][j + 1] % mod));
			}
			ans[i][9] = ans[i - 1][8];
		}
	}
}
