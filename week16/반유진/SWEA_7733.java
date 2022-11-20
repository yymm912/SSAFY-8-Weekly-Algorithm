// SWEA 7733번 치즈 도둑

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_7733 {

	static int T, N, ans;
	static int[][] map;

	static boolean[][] visit;

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int max = 0;

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					max = Math.max(max, map[i][j]);
				}
			}

			ans = 0;
			int cnt = 0;

			for (int d = 0; d <= max; d++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == d) {
							map[i][j] = 0;
						}
					}
				}

				visit = new boolean[N][N];
				cnt = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visit[i][j] && map[i][j] != 0) {
							dfs(i, j);
							cnt++;
						}
					}
				}

				ans = Math.max(ans, cnt);
			}

			System.out.println("#" + t + " " + ans);
		}

	}

	static void dfs(int y, int x) {
		visit[y][x] = true;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] == 0)
				continue;

			dfs(ny, nx);
		}
	}

}
