package 이코테;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 음료수얼려먹기 {

	static int N, M, cnt = 0;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dfs(i, j)) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	static boolean dfs(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M) {
			return false;
		}
		// 현재 지점을 방문하지 않았다면
		if (arr[x][y] == 0) {
			arr[x][y] = 1;	// 해당 지점 방문 처리 
			dfs(x, y + 1);
			dfs(x + 1, y);
			dfs(x, y - 1);
			dfs(x - 1, y);

			return true;
		}

		return false;
	}
}
