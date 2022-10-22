// SWEA 1949번 [모의 SW 역량테스트] 등산로 조성 

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1949 {

	static int T, N, K, ans, cnt;
	static int[][] map;

	static Queue<Node> high;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int maxHigh = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHigh = Math.max(maxHigh, map[i][j]);
				}
			}

			high = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHigh) {
						high.offer(new Node(i, j, 1));
					}
				}
			}

			ans = 0;

			while (!high.isEmpty()) {
				Node node = high.poll();

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k <= K; k++) {
							map[i][j] -= k;
							bfs(node);
							map[i][j] += k;
						}
					}
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void bfs(Node start) {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(start);

		cnt = 1;

		while (!que.isEmpty()) {
			Node node = que.poll();

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;

				if (map[node.y][node.x] <= map[ny][nx])
					continue;

				que.offer(new Node(ny, nx, node.len + 1));
			}

			cnt = Math.max(cnt, node.len);
		}

		ans = Math.max(ans, cnt);
	}

	static class Node {
		int y, x, len;

		public Node(int y, int x, int len) {
			this.y = y;
			this.x = x;
			this.len = len;
		}
	}
}
