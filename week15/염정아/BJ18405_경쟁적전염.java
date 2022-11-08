package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ18405_경쟁적전염 {
	static int ans, N, K;
	static int es, ey, ex;
	static int[][] map;

	static List<Virus> list = new ArrayList<>();

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int m = map[i][j] = Integer.parseInt(st.nextToken());
				if (m != 0) list.add(new Virus(i, j, m));
			}

		}

		st = new StringTokenizer(br.readLine());
		es = Integer.parseInt(st.nextToken());
		ey = Integer.parseInt(st.nextToken()) - 1;
		ex = Integer.parseInt(st.nextToken()) - 1;

		Collections.sort(list, (e1, e2) -> e1.k - e2.k);

		// 탐색
		bfs();

		// 출력
		ans = map[ey][ex];
		System.out.println(ans);

	} // end main


	private static void bfs() {
		Queue<Virus> q = new ArrayDeque<>();
		q.addAll(list); // 정렬한 리스트를 큐에 우선 넣는다.

		int dep = 0;
		while (!q.isEmpty()) {
			if (dep == es) return;

			int size = q.size();
			for (int i = 0; i < size; i++) {
				Virus v = q.poll();
				int y = v.y;
				int x = v.x;
				int k = v.k;

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
					if (map[ny][nx] != 0) continue;

					map[ny][nx] = k;
					q.offer(new Virus(ny, nx, k));
				}

			}

			list.clear();
			list.addAll(q); // 이때까지의 큐를 다시 리스트에 넣어 정렬한다.
			Collections.sort(list, (e1, e2) -> e1.k - e2.k);

			dep++;

		}

	} // end bfs


	static class Virus {
		int y, x;
		int k;


		public Virus(int y, int x, int k) {
			super();
			this.y = y;
			this.x = x;
			this.k = k;
		}


		@Override
		public String toString() {
			return "Virus [y=" + y + ", x=" + x + ", k=" + k + "]";
		}

	} // end Virus
}
