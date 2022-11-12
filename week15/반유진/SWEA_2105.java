// SWEA 2105번 [모의 SW 역량테스트] 디저트 카페

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_2105 {

	static int T, N, ans, sum, sy, sx;
	static boolean[] same;
	static int[][] map;
	static boolean[][] visit;
	static boolean check;

	// 우하-좌하-좌상-우상
	static int[] dy = { 1, 1, -1, -1 };
	static int[] dx = { 1, -1, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sy = i; // 시작 위치
					sx = j;
					sum = 0;
					check = false;
					same = new boolean[101];
					visit = new boolean[N][N];

					dfs(i, j, 0, 0);

					// System.out.println();
				}
			}

			System.out.println("#" + t + " " + ans);
		}
	}

	static void dfs(int y, int x, int dir, int cnt) {
		// System.out.print("(" + y + ", " + x + "), ");
		// System.out.print(map[y][x] + ", ");

		if (y == sy && x == sx && dir == 3 && cnt >= 4) {
			// System.out.println("끝 !! " + cnt);
			
			ans = Math.max(ans, cnt);
			return;
		}

		for (int d = dir; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || same[map[ny][nx]])
				continue;

			visit[ny][nx] = true;
			same[map[ny][nx]] = true;
			
			dfs(ny, nx, d, cnt + 1);
			
			visit[ny][nx] = false;
			same[map[ny][nx]] = false;
		}

	}
}
