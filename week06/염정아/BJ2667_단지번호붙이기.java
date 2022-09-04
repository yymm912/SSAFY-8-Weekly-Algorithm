package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BJ2667_단지번호붙이기 {
	static int ans, cnt, N;
	static int[][] map;
	static List<Integer> cnts = new ArrayList<>();

	// 상하좌우
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) map[i][j] = line.charAt(j) - '0';
			// System.out.println(Arrays.toString(map[i]));
		}

		// 초기화
		cnt = 0;

		// 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ans = 0;
				if (map[i][j] == 1) {
					dfs(i, j);
					cnt++;
					cnts.add(ans);
				}

			}

		}

		// for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));

		System.out.println(cnt);
		Collections.sort(cnts);
		for (int i = 0; i < cnts.size(); i++) {
			System.out.println(cnts.get(i));
		}

	} // end main


	private static void dfs(int y, int x) {

		// 할일
		for (int d = 0; d < 5; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
			if (map[ny][nx] == 0) continue;

			map[ny][nx] = 0; // visit 처리
			ans++;
			dfs(ny, nx);
		}

	} // end dfs
}
