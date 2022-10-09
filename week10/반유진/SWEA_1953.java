// SWEA 1953번 [모의 SW 역량테스트] 탈주범 검거

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1953 {

	static int T, N, M, R, C, L, cnt;
	static int[][] map;

	static Queue<Node> que;
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
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			que = new ArrayDeque<>();
			visit = new boolean[N][M];
			cnt = 1;

			bfs();

			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void bfs() {
		que.offer(new Node(R, C));
		visit[R][C] = true;

		for (int i = 1; i < L; i++) {
			int size = que.size();

			for (int j = 0; j < size; j++) {
				Node node = que.poll();

				if (map[node.y][node.x] == 1) {
					delta(node.y, node.x, 0); // 상
					delta(node.y, node.x, 1); // 하
					delta(node.y, node.x, 2); // 좌
					delta(node.y, node.x, 3); // 우
				} else if (map[node.y][node.x] == 2) {
					delta(node.y, node.x, 0); // 상
					delta(node.y, node.x, 1); // 하
				} else if (map[node.y][node.x] == 3) {
					delta(node.y, node.x, 2); // 좌
					delta(node.y, node.x, 3); // 우
				} else if (map[node.y][node.x] == 4) {
					delta(node.y, node.x, 0); // 상
					delta(node.y, node.x, 3); // 우
				} else if (map[node.y][node.x] == 5) {
					delta(node.y, node.x, 1); // 하
					delta(node.y, node.x, 3); // 우
				} else if (map[node.y][node.x] == 6) {
					delta(node.y, node.x, 1); // 하
					delta(node.y, node.x, 2); // 좌
				} else if (map[node.y][node.x] == 7) {
					delta(node.y, node.x, 0); // 상
					delta(node.y, node.x, 2); // 좌
				}
			}
		}

	}

	static void delta(int y, int x, int d) {
		int ny = y + dy[d];
		int nx = x + dx[d];

		if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 0)
			return;

		if (d == 0 && (map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 7))
			return;

		else if (d == 1 && (map[ny][nx] == 3 || map[ny][nx] == 5 || map[ny][nx] == 6))
			return;

		else if (d == 2 && (map[ny][nx] == 2 || map[ny][nx] == 6 || map[ny][nx] == 7))
			return;

		else if (d == 3 && (map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 5))
			return;

		visit[ny][nx] = true;
		que.offer(new Node(ny, nx));
		cnt++;
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
