package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class SW1953_탈주범검거 {
	static int ans, dep, T, N, M, R, C, L;
	static int[][] map;
	static boolean[][] visit;

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int[][] dyx = {
	                {}, // dummy
	                { 0, 1, 2, 3 }, // 상하좌우
	                { 0, 1 }, // 상하
	                { 2, 3 }, // 좌우
	                { 0, 3 }, // 상우
	                { 1, 3 }, // 하우
	                { 1, 2 }, // 하좌
	                { 0, 2 }, // 상좌
	};

	static Queue<int[]> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			// 초기화
			q.clear();
			dep = 1;
			ans = 1;
			map = new int[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}

			// 탐색
			bfs(R, C);

			// 출력
			sb.append("#" + t + " " + ans + "\n");
		}

		System.out.println(sb.toString());

	} // end main


	private static void bfs(int sy, int sx) {
		q.offer(new int[] { sy, sx });
		visit[sy][sx] = true;

		while (!q.isEmpty()) {
			int size = q.size();

			if (dep++ == L) return;

			for (int i = 0; i < size; i++) {
				int[] yx = q.poll();
				int y = yx[0];
				int x = yx[1];

				for (int d : dyx[map[y][x]]) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
					if (visit[ny][nx]) continue;
					if (map[ny][nx] == 0) continue;

					boolean flag = false;
					for (int nd : dyx[map[ny][nx]]) {
						// if (nd == (d % 2 == 0 ? d + 1 : d - 1)) flag = true;
						int nny = ny + dy[nd];
						int nnx = nx + dx[nd];

						if (y == nny && x == nnx) flag = true;
					}

					if (flag) {
						q.offer(new int[] { ny, nx });
						visit[ny][nx] = true;
						ans++;
					}

				}

			}

		}

	} // end bfs

}
