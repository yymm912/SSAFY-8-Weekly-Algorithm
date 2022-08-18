package algorithm_assignments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1303_전쟁 {
	static int N, M;
	static char[][] soldier;
	static boolean[][] visit;
	static int[] dx = { -1, 0, 1, 0 }; // 상-우-하-좌
	static int[] dy = { 0, 1, 0, -1 };
	static int B = 0, W = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		soldier = new char[M][N];
		visit = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			soldier[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int n;
				if (soldier[i][j] == 'W' && !visit[i][j]) {
					n = white_dfs(i, j, 1);
					W += n * n;
				}

				else if (soldier[i][j] == 'B' && !visit[i][j]) {
					n = blue_dfs(i, j, 1);
					B += n * n;
				}

			}
		}

		System.out.println(W + " " + B);
	}

	//blue랑 white dfs를 각각 줌
	
	static int blue_dfs(int x, int y, int d) { //deep 을 인자로 줘서 한칸 갈때마다 ++ 됨
		visit[x][y] = true;
		int deep = d;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[nx][ny])
				continue;

			if (soldier[nx][ny] == 'B') {
				deep += blue_dfs(nx, ny, 1);
			}
		}

		return deep; // 최종 deep 반환
	}

	static int white_dfs(int x, int y, int d) {
		visit[x][y] = true;
		int deep = d;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[nx][ny])
				continue;

			if (soldier[nx][ny] == 'W') {
				deep += white_dfs(nx, ny, 1); 
			}
		}

		return deep; 
	}
}
