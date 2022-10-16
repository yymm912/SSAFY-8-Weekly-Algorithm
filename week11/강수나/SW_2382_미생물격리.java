

import java.io.*;
import java.util.*;

public class SW_2382_미생물격리 {

	static PriorityQueue<Cell> q;
	static Cell map[][];
	static int N, M, K, total;
	static int turn[] = { 0, 2, 1, 4, 3 };
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			total = 0;
			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new Cell[N][N];
			q = new PriorityQueue<Cell>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());

				q.add(new Cell(r, c, num, dir));
			}

			sb.append(move()).append("\n");
		}
		System.out.println(sb);
	}

	static int move() {
		int time = M;
		total = 0;
		while (time > 0) {
			Cell head;
			while (!q.isEmpty()) {
				head = q.poll();
				int nr = head.x += dx[head.way];
				int nc = head.y += dy[head.way];
				// 가장자리면 감소하고 턴 한다.
				if (nr == 0 || nc == 0 || nr == N - 1 || nc == N - 1) {
					head.num /= 2;
					if (head.num == 0)
						continue;
					head.way = turn[head.way];
				}

				if (map[nr][nc] == null) {
					map[nr][nc] = head;
				} else {
					map[nr][nc].num += head.num;
				}

			}
			total = reset();
			time--;
		}

		return total;
	}

	private static int reset() {
		int temp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != null) {
					q.offer(map[i][j]);
					temp += map[i][j].num;
					map[i][j] = null;
				}
			}
		}
		return temp;
	}

	static class Cell implements Comparable<Cell> {
		int x, y, num, way;

		public Cell(int y, int x, int num, int way) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.way = way;
		}

		@Override
		public int compareTo(Cell o) {
			return o.num - this.num; // 내림차순
		}

	}
}
