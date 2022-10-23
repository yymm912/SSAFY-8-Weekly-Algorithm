package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_디저트카페_2105 {
	static int N;
	static int[][] map;
	static int[] dr = { 1, 1, -1, -1 }; // 우하 좌하 좌상 우상
	static int[] dc = { 1, -1, -1, 1 };
	static boolean[][] visit;
	static boolean[] dessert;
	static int max = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 양옆과 밑에 2칸이 있어야 사각형 가능
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					visit = new boolean[N][N];
					dessert = new boolean[101];
					visit[i][j] = true;
					dessert[map[i][j]] = true;
					dfs(1, i, j, i, j, 0);
				}
			}

			if (max == 0)
				max = -1;
			System.out.println("#" + tc + " " + max);
		}

	}

	private static void dfs(int cnt, int r, int c, int initR, int initC, int prevD) {
		
		// preD : 이전 방향을 넘겨서 그 방향보다 같거나 다음 방향으로만 향한다. 그 전 방향으로는 이동 X. 
		for (int i = prevD; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				if ((nr == initR) && (nc == initC) && cnt > 2) {

					max = Math.max(max, cnt);
					return;
					// 종료
				}
				if (!visit[nr][nc] && !dessert[map[nr][nc]]) {
					visit[nr][nc] = true;
					dessert[map[nr][nc]] = true;
					dfs(cnt + 1, nr, nc, initR, initC, i);
					visit[nr][nc] = false;
					dessert[map[nr][nc]] = false;
				}

			}

		}
	}

}