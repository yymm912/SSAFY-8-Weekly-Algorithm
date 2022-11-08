package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class SW1767_프로세서연결하기 {
	private static int ans, T, N, size, coreMax, lineCnt;
	private static int[][] map;

	private static ArrayList<Core> list = new ArrayList<>();

	// 좌우상하
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			// 초기화
			coreMax = 0;
			ans = Integer.MAX_VALUE;
			list.clear();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int m = map[i][j] = Integer.parseInt(st.nextToken());
					if (m == 1) {
						if ((i == 0 || i == N - 1 || j == 0 || j == N - 1)) continue;
						list.add(new Core(i, j));
					}

				}

			}

			size = list.size();

			// 탐색
			subset(0, 0, 0);

			// 출력
			System.out.println("#" + t + " " + ans);

			// break;
		} // end tc

	} // end main


	// depth
	// coreCnt: 코어의 개수
	// sum: 현재까지의 전선 라인 길이의 총합
	private static void subset(int dep, int coreCnt, int sum) {
		if (dep == size) {
			if (coreMax < coreCnt) {
				coreMax = coreCnt;
				ans = sum;
			} else if (coreMax == coreCnt) {
				if (ans > sum) ans = sum;
			}

			return;
		}

		for (int d = 0; d < 4; d++) {
			Core core = list.get(dep);
			int y = core.y;
			int x = core.x;

			// 현재 코어 위치에서 해당 방향으로 연결이 가능한지 확인
			if (check(y, x, d)) {
				setLine(y, x, d, 2); // 전선 놓기
				subset(dep + 1, coreCnt + 1, sum + lineCnt);
				setLine(y, x, d, 0); // 놓은 전선 제거하기
			}

		}

		subset(dep + 1, coreCnt, sum); // 코어 선택 안함
	} // end dfs


	private static void setLine(int y, int x, int d, int op) {
		int ny = y + dy[d];
		int nx = x + dx[d];

		lineCnt = 0;
		while (true) {
			if (ny < 0 || ny >= N || nx < 0 || nx >= N) break;
			map[ny][nx] = op;
			lineCnt++;

			ny += dy[d];
			nx += dx[d];
		}

	} // end setLine


	private static boolean check(int y, int x, int d) {
		int ny = y + dy[d];
		int nx = x + dx[d];

		while (true) {
			if (ny < 0 || ny >= N || nx < 0 || nx >= N) break;
			if (map[ny][nx] != 0) return false;

			ny += dy[d];
			nx += dx[d];
		}

		return true;
	} // end check


	static class Core {
		int y, x;


		public Core(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}


		@Override
		public String toString() {
			return "Core [y=" + y + ", x=" + x + "]";
		}

	} // end Core
}
