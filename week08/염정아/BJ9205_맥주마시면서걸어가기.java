package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ9205_맥주마시면서걸어가기 {
	static int ans, T, N;
	static int sy, sx, ey, ex;
	static int[][] pos;
	static boolean[] visit;

	static List<int[]> list = new ArrayList<>();
	static Queue<int[]> q = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			// 초기화
			ans = 0;
			q.clear();
			list.clear();
			pos = new int[N][2];
			visit = new boolean[N];

			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				if (i == 0) {
					sy = y;
					sx = x;
				} else if (i == N + 1) {
					ey = y;
					ex = x;
				} else list.add(new int[] { y, x });

			}

			System.out.println(bfs() ? "happy" : "sad");

		}

	} // end main


	private static boolean bfs() {
		q.add(new int[] { sy, sx });

		while (!q.isEmpty()) {
			int[] yx = q.poll();
			int y = yx[0];
			int x = yx[1];

			// 현재 지점과 도착지점이 1000보다 작다면 바로 이동 가능하기 true return
			if (extent(ey, ex, y, x) <= 1000) return true;

			for (int i = 0; i < N; i++) {
				if (visit[i]) continue;

				int ny = list.get(i)[0];
				int nx = list.get(i)[1];

				int dis = extent(ny, nx, y, x);
				if (dis <= 1000) {
					visit[i] = true;
					q.add(new int[] { ny, nx });
				}

			}

		}

		return false;

	} // end bfs


	private static int extent(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	} // end extent
}
