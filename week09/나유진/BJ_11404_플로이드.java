package BJ.STUDY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11404_플로이드 {
	static int n, m;

	static int[][] city; // 0 이면 갈 수 없는 경로임

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		city = new int[n + 1][n + 1]; // 0 dummy

		for (int i = 1; i <= n; i++) {
			Arrays.fill(city[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			city[a][b] = Math.min(city[a][b], w);
		}
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if(i==j)continue;

					if (city[i][k] != Integer.MAX_VALUE && city[k][j] != Integer.MAX_VALUE)
						city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				sb.append(city[i][j] == Integer.MAX_VALUE ? 0 : city[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
