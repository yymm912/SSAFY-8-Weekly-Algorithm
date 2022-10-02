package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2589 {
	static Queue<int[]> q;
	static int n, m;
	static char[][] map;
	static boolean[][] visited;
	static int[] dy = { 0, 0, 1, -1 }, dx = { 1, -1, 0, 0 };
	static int[][] cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new boolean[n][m];

		cnt = new int[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().trim().toCharArray();
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					for (int k = 0; k < n; k++)
						Arrays.fill(visited[k], false);
					q = new ArrayDeque<>();
					int res = bfs(i, j);
					ans = Math.max(ans, res);
				}
			}
		}
		System.out.println(ans);
	}

	static int ry, rx;

	static int bfs(int y, int x) {

		q.offer(new int[] { y, x, 0 });
		int cnt = 0;
		visited[y][x] = true;
		while (!q.isEmpty()) {

			int[] a = q.poll();

			int ay = a[0];
			int ax = a[1];
			cnt = a[2];

			for (int i = 0; i < 4; i++) {

				int ny = ay + dy[i];
				int nx = ax + dx[i];

				if (ny < n && nx < m && ny >= 0 && nx >= 0) {

					if (!visited[ny][nx] && map[ny][nx] == 'L') {

						visited[ny][nx] = true;
						q.offer(new int[] { ny, nx, cnt+1 });
					}
				}
			}
		}
		return cnt;
	}
}
