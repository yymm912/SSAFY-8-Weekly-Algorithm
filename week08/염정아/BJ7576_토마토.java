package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ7576_토마토 {
	static int ans, M, N;
	static int[][] map;
	static boolean[][] visit;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static Queue<Node> q = new ArrayDeque<>();
	static List<Node> list = new ArrayList<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int m = Integer.parseInt(st.nextToken());
				if (m == 1) {
					q.add(new Node(i, j, 0));
					visit[i][j] = true;
				}

				map[i][j] = m;
			}

		}

		// 초기화
		ans = Integer.MIN_VALUE;

		// 탐색
		bfs();

		// 출력
		if (check()) ans = -1;
		System.out.println(ans);

	} // end main


	private static void bfs() {

		while (!q.isEmpty()) {
			Node node = q.poll();
			ans = node.d;

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (visit[ny][nx]) continue;
				if (map[ny][nx] == -1) continue;

				q.offer(new Node(ny, nx, node.d + 1));
				map[ny][nx] = node.d + 1;
				visit[ny][nx] = true;
			}

		}

	} // end bfs


	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) if (map[i][j] == 0) return true;
		}

		return false;
	}


	static class Node {
		int y, x;
		int d;


		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}


		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", d=" + d + "]";
		}

	} // end Node

}
