// BOJ 16929번 Two Dots

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_16929 {

	static int N, M, sy, sx;
	static boolean result;
	static char[][] map;
	static int[][] intMap;
	static char ch;
	static boolean[][] visit;
	static Queue<Node> que = new ArrayDeque<>();

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		visit = new boolean[N][M];
		intMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {
					ch = map[i][j];
					visit[i][j] = true;
					intMap[i][j] = 1;

					sy = i;
					sx = j;
					dfs(i, j, 1, 0);
				}
				if (result) {
					System.out.println("Yes");
					return;
				}
			}
		}

		System.out.println("No");
	}

	static void dfs(int y, int x, int k, int dir) {
		if (result)
			return;

		int d = dir;
		for (int a = 0; a < 4; a++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			d = d == 0 ? 3 : d - 1;

			if (k >= 4 && sy == ny && sx == nx) {
				result = true;
				return;
			}

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] != ch)
				continue;

			visit[ny][nx] = true;
			intMap[ny][nx] = k + 1;

			dfs(ny, nx, k + 1, d);
			visit[ny][nx] = false;
		}
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}