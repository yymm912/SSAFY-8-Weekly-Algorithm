package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3190_뱀 {
	static int N, K;
	static Queue<int[]> que;
	static int[][] map;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		que = new ArrayDeque<>();
		map = new int[N][N];

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = -1;
		}

		map[0][0] = 1;

		int move = Integer.parseInt(br.readLine());
		int ans = 0, row = 0, col = 0, dir = 0;

		for (int stop = 0; stop < move; stop++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);

			while (true) {
				if (ans == time)
					break;

				int nr = row + dr[dir], nc = col + dc[dir];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] > 0) {
					System.out.println(ans + 1);
					return;
				}

				que.add(new int[] { row, col });

				if (map[nr][nc] == 0) {
					int[] rc = que.poll();
					map[rc[0]][rc[1]] = 0;
				}

				map[nr][nc] = 1;
				row = nr;
				col = nc;
				ans++;

			}

			switch (c) {
			case 'L':
				if (--dir < 0)
					dir = 3;
				break;
			case 'D':
				if (++dir > 3)
					dir = 0;
				break;
			}

		}

		while (true) {
			int nr = row + dr[dir], nc = col + dc[dir];

			if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] > 0) {
				System.out.println(ans + 1);
				return;
			}

			que.add(new int[] { row, col });

			if (map[nr][nc] == 0) {
				int[] rc = que.poll();
				map[rc[0]][rc[1]] = 0;
			}

			map[nr][nc] = 1;
			row = nr;
			col = nc;
			ans++;

		}

	}
}
