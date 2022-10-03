package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW2105_디저트카페 {
	static int ans, T, N, sy, sx;
	static int[][] map;
	static boolean[][] visit;
	static boolean[] tasted; // 이미 맛본 디저트

	// 우상 -> 우하 -> 좌하 -> 좌상
	static int[] dy = { -1, 1, 1, -1 };
	static int[] dx = { 1, 1, -1, -1 };

	static int[][] dyx = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
				// System.out.println(Arrays.toString(map[i]));
			}

			// 초기화
			ans = -1;
			visit = new boolean[N][N];
			tasted = new boolean[101]; // 0 dummy

			// 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sy = i;
					sx = j;
					dfs(i, j, 0, 0);
				}

			}

			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());

	} // end main


	private static void dfs(int y, int x, int dir, int dep) {
		if (sy == y && sx == x && dir == 3) {
			ans = Math.max(ans, dep);
			return;
		}

		for (int d : dyx[dir]) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
			if (visit[ny][nx]) continue;
			if (tasted[map[ny][nx]]) continue;

			visit[ny][nx] = true;
			tasted[map[ny][nx]] = true;
			dfs(ny, nx, d, dep + 1);
			visit[ny][nx] = false;
			tasted[map[ny][nx]] = false;
		}

	} // end dfs

}
