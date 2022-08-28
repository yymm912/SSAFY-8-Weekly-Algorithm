package algorithm_assignments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17070_파이프옮기기1 {
	static int[][] map, route;
	static int N, ans;

	static int d; // 0 : 가로, 1: 세로, 2: 대각선

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		route = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pipe();

		System.out.println(route[N - 1][N - 1]);
	}

	static void pipe() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(0, 1, 0));

		while (!queue.isEmpty()) {
			Node n = queue.poll();
			switch (n.d) {
			case 0: // 가로, 대각선 가능
				if (!check(n.y, n.x + 1))
					continue;

				queue.offer(new Node(n.y, n.x + 1, 0));
				route[n.y][n.x + 1]++;

				if (check(n.y + 1, n.x + 1) && map[n.y + 1][n.x] == 0) {
					queue.offer(new Node(n.y + 1, n.x + 1, 2));
					route[n.y + 1][n.x + 1]++;
				}
				break;

			case 1: // 세로, 대각선 가능

				if (!check(n.y + 1, n.x))
					continue;

				queue.offer(new Node(n.y + 1, n.x, 1));
				route[n.y + 1][n.x]++;

				if (check(n.y + 1, n.x + 1) && map[n.y][n.x + 1] == 0) {
					queue.offer(new Node(n.y + 1, n.x + 1, 2));
					route[n.y + 1][n.x + 1]++;
				}
				break;

			case 2: /// 가로, 세로, 대각선 가능
				if (check(n.y, n.x + 1)) {
					queue.offer(new Node(n.y, n.x + 1, 0));
					route[n.y][n.x + 1]++;
				}

				if (check(n.y + 1, n.x)) {
					queue.offer(new Node(n.y + 1, n.x, 1));
					route[n.y + 1][n.x]++;
				}

				if (check(n.y + 1, n.x + 1) && map[n.y + 1][n.x] == 0 && map[n.y][n.x + 1] == 0
						&& map[n.y + 1][n.x + 1] == 0) {
					queue.offer(new Node(n.y + 1, n.x + 1, 2));
					route[n.y + 1][n.x + 1]++;
				}
				break;
			}
		}
	}

	static boolean check(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < N && map[y][x] == 0;
	}

	static class Node {
		int y, x, d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
