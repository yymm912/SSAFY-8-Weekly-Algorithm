package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] memoi;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			int[][] memoi = new int[n + 1][n + 1];
			for (int i = 1; i < n + 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < n + 1; j++) {
					memoi[i][j] = Integer.parseInt(st.nextToken()) + memoi[i - 1][j] + memoi[i][j - 1]
							- memoi[i - 1][j - 1];
				}
			}

			int max = 0;
			for (int i = m; i < n + 1; i++) {
				for (int j = m; j < n + 1; j++) {
					max = Math.max(max, memoi[i][j] - memoi[i - m][j] - memoi[i][j - m] + memoi[i - m][j - m]);

				}
			}
			sb.append("#" + t + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}
}
