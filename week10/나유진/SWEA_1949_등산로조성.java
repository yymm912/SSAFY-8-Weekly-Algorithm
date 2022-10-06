package STUDY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1949_등산로조성 {
	static int T, N, K, ans;
	static int[][] map;
	static boolean[][] visit;
	static boolean isCut; // true : 깎을 수 있음. false : 이미 다른데서 깎음
	static ArrayList<Node> list;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			list = new ArrayList<>();
			ans = Integer.MIN_VALUE;

			int max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > max)
						max = map[i][j];
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max)
						list.add(new Node(i, j, 1));
				}
			}

			simul();

			System.out.println("#" + t + " " + ans);

		}

	}

	private static void simul() {
		int idx = 0;
		visit = new boolean[N][N];
		while (list.size() != idx) {
			Node node = list.get(idx++);
			visit[node.y][node.x] = true;
			List<Integer> route = new ArrayList<>();
			dfs(node, true, route);
			visit[node.y][node.x] = false;
		}
		// dfs 탐색
		// 탐색 정지되면 max 갱신
		// 탐색에서 나오면서 등산로 깍기
		// 깍은 경우로 다시 탐색
		// 반복
	}

	private static void dfs(Node node, boolean iscut, List<Integer> route) {
		ans = Math.max(ans, node.d);
		route.add(map[node.y][node.x]);
		for (int i = 0; i < 4; i++) {
			int ny = node.y + dy[i];
			int nx = node.x + dx[i];

			if (!check(ny, nx))
				continue;

			if (visit[ny][nx])
				continue;

			if (map[node.y][node.x] > map[ny][nx]) {
				visit[ny][nx] = true;
				dfs(new Node(ny, nx, node.d + 1), iscut, route);
				visit[ny][nx] = false;
			}

			if (iscut) {
				for (int k = 1; k <= K; k++) {
					if (map[node.y][node.x] > (map[ny][nx] - k)) {
						visit[ny][nx] = true;
						map[ny][nx] -= k;
						dfs(new Node(ny, nx, node.d + 1), false, route);
						map[ny][nx] += k;
						visit[ny][nx] = false;
					}
				}
			}
		}
		route.remove(route.size() - 1);
	}

	static boolean check(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < N;
	}

	static class Node {
		int y, x, d;

		public Node(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", d=" + d + "]";
		}

	}
}
