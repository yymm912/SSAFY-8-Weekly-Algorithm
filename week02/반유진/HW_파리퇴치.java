// SWEA 2001번 파리 퇴치

package HW.D_0803;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2001 {

	static int[][] accu;
	static int N, M, T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			accu = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					accu[i][j] += Integer.parseInt(st.nextToken());
					accu[i][j] += accu[i - 1][j] + accu[i][j - 1] - accu[i - 1][j - 1];
				}
			}

			int max = 0;
			int cnt = 0;
			for (int i = 1; i <= N - M + 1; i++) {
				for (int j = 1; j <= N - M + 1; j++) {
					int from_x = i;
					int from_y = j;
					int to_x = i + M - 1;
					int to_y = j + M - 1;

					if (max < accu[to_x][to_y] - accu[to_x][from_y - 1] - accu[from_x - 1][to_y]
							+ accu[from_x - 1][from_y - 1]) {
						max = accu[to_x][to_y] - accu[to_x][from_y - 1] - accu[from_x - 1][to_y]
								+ accu[from_x - 1][from_y - 1];
					}
				}
			}
			System.out.println("#" + t + " " + max);

		}

	}

}
