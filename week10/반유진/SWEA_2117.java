// SWEA 2117번 [모의 SW 역량테스트] 홈 방범 서비스

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_2117 {

	static int T, N, M, ans;
	static int[][] map;
	static boolean[][] visit;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(i, j);
				}
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void bfs(int y, int x) {
		Queue<Node> que = new ArrayDeque<>();
		visit = new boolean[N][N];

		que.offer(new Node(y, x));
		visit[y][x] = true;

		int cnt = 0; // 집 개수
		int K = 1;

		while (!que.isEmpty()) {
			int size = que.size();

			for (int i = 0; i < size; i++) {
				Node node = que.poll();

				if (map[node.y][node.x] == 1)
					cnt++;

				for (int d = 0; d < 4; d++) {
					int ny = node.y + dy[d];
					int nx = node.x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx])
						continue;

					que.offer(new Node(ny, nx));
					visit[ny][nx] = true;
				}
			}
			
			if ((M * cnt) - (K * K + (K - 1) * (K - 1)) >= 0)
				ans = Math.max(ans, cnt);

			K++;
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
