package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW2115_벌꿀채취 {
	static int ans, max, T, N, M, C;
	static int[][] map;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			// 초기화
			ans = Integer.MIN_VALUE;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}

			comb();

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb.toString());
	} // end main


	private static void subset(int dep, int y, int x, int sum, int pow) {
		if (sum > C) return;

		if (dep == M) {
			max = Math.max(max, pow);
			return;
		}

		subset(dep + 1, y, x + 1, sum + map[y][x], pow + map[y][x] * map[y][x]);
		subset(dep + 1, y, x + 1, sum, pow);
	} // end subset


	private static void comb() {
		int max1 = 0;
		int max2 = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) {
				max = 0;
				subset(0, i, j, 0, 0);
				max1 = max;

				max = 0;
				max2 = 0;
				for (int j2 = j + M; j2 <= N - M; j2++) {
					subset(0, i, j2, 0, 0);
					max2 = max;
				}

				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) {
						subset(0, i2, j2, 0, 0);
						max2 = Math.max(max2, max);
					}
				}

				ans = Math.max(ans, max1 + max2);
			}

		}

	} // end comb

}
