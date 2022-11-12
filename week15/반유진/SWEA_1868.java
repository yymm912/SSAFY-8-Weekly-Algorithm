// SWEA 1868번 파핑파핑 지뢰찾기 

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class No_1868 {

	static int T, N;
	static char[][] map, cnt_map;

	static int[] src, tgt;
	static boolean[] select;

	// 상 부터 시계방향으로 8방
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new char[N][N];
			cnt_map = new char[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();

				for (int j = 0; j < N; j++) {
					cnt_map[i][j] = map[i][j];
				}
			}

			cntMark();

			int click = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cnt_map[i][j] == '0') {
						bomb(i, j);
						click++;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						click++;
					}
				}
			}

			System.out.println("#" + t + " " + click);
		}

	}

	static void bomb(int y, int x) {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(y, x));

		while (!que.isEmpty()) {
			Node node = que.poll();

			for (int d = 0; d < 8; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == '*')
					continue;

				if (cnt_map[node.y][node.x] == '0')
					que.offer(new Node(ny, nx));
			}

			cnt_map[node.y][node.x] = 'x';
			map[node.y][node.x] = 'x';
		}

	}

	static void cntMark() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;

				if (map[i][j] == '.') {
					for (int d = 0; d < 8; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];

						if (ny < 0 || nx < 0 || ny >= N || nx >= N)
							continue;

						if (map[ny][nx] == '*')
							cnt++;
					}

					cnt_map[i][j] = (char) (cnt + '0');
				}
			}
		}
	}

	static boolean successCheck() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.')
					return false;
			}
		}

		return true;
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
