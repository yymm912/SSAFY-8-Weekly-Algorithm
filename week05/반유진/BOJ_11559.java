// BOJ 11559번 Puyo Puyo

package BOJ.DfsBfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class No_11559 {

	static int ans;
	static char[][] map = new char[12][];
	static boolean[][] visited;
	static Queue<Node> que, kill, all;

	// 상-하-좌-우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();

		}

		while (true) {
			all = new ArrayDeque<>();
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {
						bfs(i, j);
					}
				}
			}

			if (all.isEmpty())
				break;

			game(all);
		}

		System.out.println(ans);
	}

	static void game(Queue<Node> all) {
		while (!all.isEmpty()) {
			Node node = all.poll();
			map[node.y][node.x] = '.';
		}

		for (int j = 0; j < 6; j++) {
			for (int i = 11; i >= 0; i--) {
				if (map[i][j] != '.') {
					while (true) {
						int ni = i + 1;
						if (ni < 12 && map[ni][j] == '.') {
							map[ni][j] = map[i][j];
							map[i][j] = '.';
							i = ni;
						} else {
							break;
						}
					}
				}
			}
		}

		/*
		 * System.out.println("=========="); for (int k = 0; k < 12; k++) {
		 * System.out.println(map[k]); }
		 */

		ans++;
	}

	static void bfs(int y, int x) {
		que = new ArrayDeque<>();
		kill = new ArrayDeque<>();
		visited = new boolean[12][6];

		int cnt = 1;

		que.add(new Node(y, x));
		visited[y][x] = true;

		while (!que.isEmpty()) {
			Node node = que.poll();

			kill.offer(new Node(node.y, node.x));

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= 12 || nx >= 6 || visited[ny][nx] || map[ny][nx] != map[node.y][node.x]) {
					continue;
				}
				cnt++;

				que.offer(new Node(ny, nx));
				visited[ny][nx] = true;
			}
		}

		if (cnt >= 4) {
			while (!kill.isEmpty()) {
				all.offer(kill.poll());
			}
		}
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "[y=" + y + ",x=" + x + "]";
		}
	}
}
