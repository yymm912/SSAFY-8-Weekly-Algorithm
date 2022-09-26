package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj_9251 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] a = br.readLine().toCharArray();
		char[] b = br.readLine().toCharArray();
		int lena = a.length;
		int lenb = b.length;

		int[][] dp = new int[lena + 1][lenb + 1];

		for (int i = 1; i <= lena; i++) {
			for (int j = 1; j <= lenb; j++) {
				
				if (i == 1 && j != 1) {
					if (a[i - 1] == b[j - 1]) {
						dp[i][j] = 1;
					} else
						dp[i][j] = dp[i][j - 1];
				} else if (j == 1 && i != 1) {
					if (a[i - 1] == b[j - 1]) {
						dp[i][j] = 1;
					} else
						dp[i][j] = dp[i - 1][j];

				} else if (j == 1 && i == 1) {
					if (a[0] == b[0])
						dp[1][1] = 1;
					else
						dp[1][1] = 0;
				} else {
					if (a[i - 1] == b[j - 1]) {
						dp[i][j]=dp[i-1][j-1]+1;
					} else
						dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

				}
			}
		}
		System.out.println(dp[lena][lenb]);
	}
}
