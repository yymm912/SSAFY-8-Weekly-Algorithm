package practice.samsung2022.swtest03;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class SW4727_견우와직녀 {
	static int ans, T, N, M;
	static int[][] map;

	static List<int[]> list = new ArrayList<>();

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			list.clear();

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int m = map[i][j] = Integer.parseInt(st.nextToken());
					if (m == 0) list.add(new int[] { i, j });
				}

			}

			// 초기화
			ans = Integer.MAX_VALUE;

			// 탐색
			// 딱 하나 더만 길 추가해주니까
			for (int[] yx : list) {
				int sy = yx[0];
				int sx = yx[1];

				int temp = map[sy][sx];
				map[sy][sx] = M;

				bfs();

				map[sy][sx] = temp; // 복원
			}

			// 출력
			System.out.println("#" + t + " " + ans);
			// break;

		}

	} // end main


	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][N];

		q.offer(new Node(0, 0, 1));
		visit[0][0] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int y = node.y;
			int x = node.x;
			int time = node.time;

			if (y == N - 1 && x == N - 1) ans = Math.min(ans, time - 1);

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (visit[ny][nx]) continue;
				if (map[ny][nx] == 0) continue;

				if (map[ny][nx] == 1) { // 만약 그냥 지날 수 있는 길이라면 (1 이라면)
					q.offer(new Node(ny, nx, time + 1));
					visit[ny][nx] = true;
				} else if (map[ny][nx] > 1) { // 만약 오작교가 만들어지는 길이라면 (정수 k, m)
					// 오작교를 두번 연속해서 건너지 않도록
					if (map[y][x] > 1) continue;

					// 오작교를 건널 수 있다면
					if ((time % map[ny][nx]) == 0) {
						q.offer(new Node(ny, nx, time + 1));
						visit[ny][nx] = true;

						// 못건넌다면
					} else q.offer(new Node(y, x, time + 1));

				}

			}

		}

	} // end bfs


	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) System.out.println(Arrays.toString(map[i]));
		System.out.println();

	} // end print


	static class Node {
		int y, x;
		int time;


		public Node(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}

	} // end Node
}
