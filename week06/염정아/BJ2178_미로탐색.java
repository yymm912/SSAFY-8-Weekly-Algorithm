package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ2178_미로탐색 {
	static int ans, N, M;
	static int[][] map;
	static boolean[][] visit;

	static Queue<int[]> q = new ArrayDeque<>();

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		ans = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) map[i][j] = line.charAt(j) - '0';
		}

		// dfs(0, 0, 1); // 시간초과
		bfs();

		// for (int i = 0; i < N; i++) {
		// System.out.println(Arrays.toString(map[i]));
		// }

		// 가장 빨리 도착하면 이미 visit 처리가 되므로
		System.out.println(map[N - 1][M - 1]);

	} // end main


	private static void bfs() {
		q.offer(new int[] { 0, 0 });
		visit[0][0] = true;

		while (!q.isEmpty()) {
			int[] yx = q.poll();
			int y = yx[0];
			int x = yx[1];

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (visit[ny][nx]) continue;
				if (map[ny][nx] == 0) continue;

				map[ny][nx] = map[y][x] + 1;
				visit[ny][nx] = true;
				q.offer(new int[] { ny, nx });
			}

		}

	} // end bfs


	// 칸을 한칸씩 이동하는 함수
	private static void dfs(int y, int x, int n) {
		// 기저 조건
		if (y == N - 1 && x == M - 1) {
			ans = Math.min(ans, n);
			return;
		}

		// 할 일
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if (visit[ny][nx]) continue;
			if (map[ny][nx] == 0) continue;

			// 다음 재귀 호출
			visit[ny][nx] = true;
			dfs(ny, nx, n + 1);
			visit[ny][nx] = false;
		}

	} // end dfs
}
