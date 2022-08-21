package add.day220818.problem;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * [주의]
 * - 보안회사이익 > 집들 수 x M (수익) - 운영비용 
 * - 보안회사가 손해보지않고 서비스가 가능한 최대의 집 수를 출력 
 * */


// N: 도시의 크기 
// M: 하나의 집이 지불할 수 있는 비용 
// home: K 서비스 영역 안의 집의 갯수 
// cost: 운영비용 [K*K + (K-1)*(K-1)]
// ans: 홈방범 서비스를 제공받는 집들의 수 
public class SW2117_홈방범서비스 {
	static int T, N, M, ans;
	static int home, cost;
	static int[][] map;
	static boolean[][] visit;
	static Queue<int[]> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("add.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());

				// System.out.println(Arrays.toString(map[i]));
			}

			// 초기화
			q.clear();
			ans = Integer.MIN_VALUE;

			for (int k = 1; k < N + 2; k++) {
				cost = (k * k) + (k - 1) * (k - 1);
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						visit = new boolean[N][N];
						home = 0;
						bfs(i, j, k);
						int benefit = (home * M) - cost;

						// System.out.println("ij: " + i + " " + j + " k: " + k + " home: " + home
						// + " benefit: " + benefit);
						if (benefit >= 0) ans = Math.max(ans, home);
					}

				}

			}

			sb.append("#" + t + " " + ans + "\n");

		}

		System.out.println(sb.toString());

	} // end main


	static void bfs(int sy, int sx, int k) {
		visit[sy][sx] = true;
		q.offer(new int[] { sy, sx });

		if (map[sy][sx] == 1) home++;

		int cnt = 1;
		while (!q.isEmpty()) {
			int[] yx = q.poll();
			int y = yx[0];
			int x = yx[1];

			for (int d = 0; d < 4; d++) {
				if (cnt == (k * k) + (k - 1) * (k - 1)) return;

				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (visit[ny][nx]) continue;

				cnt++;

				// System.out.println(cnt);
				// System.out.println("ny: " + ny + " nx: " + nx);

				if (map[ny][nx] == 1) home++;

				visit[ny][nx] = true;
				q.offer(new int[] { ny, nx });

			}

		}

	} // end bfs
}
