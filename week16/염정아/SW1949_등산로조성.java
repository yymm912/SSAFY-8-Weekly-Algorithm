package practice.samsung2022.swtest03;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SW1949_등산로조성 {
	static int ans, T, N, K, max;
	static int[][] map;
	static boolean[][] visit;

	static List<int[]> list = new ArrayList<>();

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// 초기화
			map = new int[N][N];
			list.clear();
			ans = Integer.MIN_VALUE;
			max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int m = map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, m);
				}

			}

			// 가장 높은 봉우리의 위치 저장
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) list.add(new int[] { i, j });
				}

			}

			// 탐색
			for (int[] yx : list) {
				visit = new boolean[N][N];
				int sy = yx[0];
				int sx = yx[1];

				visit[sy][sx] = true;
				dfs(1, sy, sx, false);
				visit[sy][sx] = false;
			}

			// 출력
			System.out.println("#" + t + " " + ans);

			// break;

		}

	} // end main


	private static void dfs(int dep, int y, int x, boolean flag) {
		ans = Math.max(ans, dep);

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
			if (visit[ny][nx]) continue;

			if (!flag) { // 만약 아직 칠하지 않았다면

				// 우선적으로 작을 때 먼저 가봐야한다.
				if (map[ny][nx] < map[y][x]) {
					visit[ny][nx] = true;
					flag = false;
					dfs(dep + 1, ny, nx, flag);
					visit[ny][nx] = false;
				} else {
					// 큰데, i만큼 빼면 갈 수 있을 때
					for (int i = 1; i <= K; i++) {
						if (map[ny][nx] - i >= map[y][x]) continue;

						// 만약 i 빼서 현재보다 작다면 가본다.
						visit[ny][nx] = true;
						flag = true;
						map[ny][nx] -= i;

						dfs(dep + 1, ny, nx, flag);

						// 복원해주기
						map[ny][nx] += i;
						flag = false;
						visit[ny][nx] = false;
					}

				}

			} else { // 이미 기회를 써봤다면
				if (map[ny][nx] >= map[y][x]) continue;

				visit[ny][nx] = true;
				flag = true;

				dfs(dep + 1, ny, nx, flag);

				// flag = false;
				visit[ny][nx] = false;
			}

		}

	} // end dfs

}
