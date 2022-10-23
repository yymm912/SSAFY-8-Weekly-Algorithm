package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BJ4485_녹색옷입은애가젤다지 {
	static final int INF = Integer.MAX_VALUE;

	static int ans, T, N;
	static int[][] map, cost;
	static boolean[][] visit;

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;

			map = new int[N][N];
			cost = new int[N][N];
			visit = new boolean[N][N];
			pq.clear();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}

			// 가중치 배열 초기화
			for (int i = 0; i < N; i++) Arrays.fill(cost[i], INF);

			dijkstra();

			sb.append("Problem " + ++T + ": " + cost[N - 1][N - 1] + "\n");

		}

		System.out.println(sb.toString());

	} // end main


	private static void dijkstra() {
		// 1. 시작 노드 초기화
		cost[0][0] = map[0][0];
		pq.offer(new Node(0, 0, cost[0][0]));

		while (!pq.isEmpty()) {
			// 2. 가중치가 가장 작은 노드부터 꺼내기
			Node node = pq.poll();
			int y = node.y;
			int x = node.x;
			int w = node.w;

			// 3. visit 체크
			if (visit[y][x]) continue;
			if (map[y][x] > cost[y][x]) continue;

			visit[y][x] = true;

			// 4. ncost 체크
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (visit[ny][nx]) continue;

				int ncost = w + map[ny][nx];
				if (ncost < cost[ny][nx]) {
					cost[ny][nx] = ncost;
					pq.offer(new Node(ny, nx, cost[ny][nx]));
				}

			}

		}

	} // end dijkstra


	static class Node {
		int y, x;
		int w;


		public Node(int y, int x, int w) {
			super();
			this.y = y;
			this.x = x;
			this.w = w;
		}

	} // end Node
}
