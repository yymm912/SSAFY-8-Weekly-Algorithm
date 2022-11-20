// SWEA 8382번 방향 전환

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_8382 {

	static int T, x1, y1, x2, y2, ans;
	static boolean check;

	static boolean[][][] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;

			check = false;
			ans = Integer.MAX_VALUE;
			visit = new boolean[201][201][2];

			if (x1 == x2 && y1 == y2)
				ans = 0;
			else
				bfs();

			System.out.println("#" + t + " " + ans);
		}

	}

	static void bfs() {
		Queue<Node> que = new ArrayDeque<>();

		que.offer(new Node(y1 + 1, x1, 0, 1));
		que.offer(new Node(y1 - 1, x1, 0, 1));

		que.offer(new Node(y1, x1 + 1, 1, 1));
		que.offer(new Node(y1, x1 - 1, 1, 1));

		visit[y1][x1][1] = true;
		visit[y1][x1][0] = true;

		while (!que.isEmpty()) {
			Node node = que.poll();

			if (node.y == y2 && node.x == x2) {
				ans = Math.min(ans, node.cnt);
				continue;
			}

			int ny1 = node.y;
			int nx1 = node.x;
			int ny2 = node.y;
			int nx2 = node.x;
			int nd = node.d;
			if (node.d == 0) { // 세로방향
				nx1 -= 1; // 좌
				nx2 += 1; // 우
				nd = 1;
			} else if (node.d == 1) { // 가로방향
				ny1 -= 1; // 상
				ny2 += 1; // 하
				nd = 0;
			}

			if (0 <= nx1 && nx1 <= 200 && 0 <= ny1 && ny1 <= 200) {
				if (!visit[ny1][nx1][nd]) {
					que.offer(new Node(ny1, nx1, nd, node.cnt + 1));
					visit[ny1][nx1][nd] = true;
				}
			}

			if (0 <= nx2 && nx2 <= 200 && 0 <= ny2 && ny2 <= 200) {
				if (!visit[ny2][nx2][nd]) {
					que.offer(new Node(ny2, nx2, nd, node.cnt + 1));
					visit[ny2][nx2][nd] = true;
				}
			}
		}
	}

	static class Node {
		int y, x, d, cnt;

		public Node(int y, int x, int d, int cnt) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.cnt = cnt;
		}
	}
}
