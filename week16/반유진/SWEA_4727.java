// SWEA 4727번 견우와 직녀 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_4727 {

	static int T, N, M, ans;
	static int[][] map;

	static boolean[][] visit;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

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

			ans = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						map[i][j] = M;
						bfs();
						map[i][j] = 0;
					}
				}
			}

			System.out.println("#" + t + " " + ans);
		}
	}

	static void bfs() {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(0, 0, 0, false));

		visit = new boolean[N][N];
		visit[0][0] = true;

		while (!que.isEmpty()) {
			Node node = que.poll();

			if (node.y == N - 1 && node.x == N - 1) {
				ans = Math.min(ans, node.time + 1);
				break;
			}

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				int t = node.time;
				boolean inputTwo = node.two;

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] == 0)
					continue;

				if (map[ny][nx] > 1) {
					if (inputTwo) {
						continue;
					}
					inputTwo = true;

					// t = node.time + ((map[ny][nx] - (node.time % map[ny][nx])) - 1);

					if ((node.time + 1) % map[ny][nx] == 0) {
						t = node.time + 1;
					} else {
						que.offer(new Node(node.y, node.x, t + 1, false));
						continue;
					}

				} else if (map[ny][nx] == 1) {
					t = node.time + 1;
					inputTwo = false;
				}

				visit[ny][nx] = true;
				que.offer(new Node(ny, nx, t, inputTwo));
			}
		}
	}

	static class Node {
		int y, x, time;
		boolean two;

		public Node(int y, int x, int time, boolean two) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.two = two;
		}
	}

}
