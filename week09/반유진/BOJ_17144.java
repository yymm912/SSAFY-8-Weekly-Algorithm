// BOJ 17144번 미세먼지 안녕! 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_17144 {

	static int R, C, T, ans;
	static int[][] map, cost;

	static int[] cleanX = new int[2]; // idx 0: 위쪽 공기청정기, 1: 아래쪽 공기청정기
	static int[] cleanY = new int[2];

	static Queue<Node> que = new ArrayDeque<>();

	// 상-우-하-좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		cost = new int[R][C];

		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;

				if (n == -1) {
					cleanX[idx] = i;
					cleanY[idx++] = j;
				} else if (n > 0) {
					que.offer(new Node(i, j, 0));
				}
			}
		}

		bfs();

		ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;

				if (map[i][j] > 0)
					ans += map[i][j];
			}
		}

		System.out.println(ans);
	}

	static void bfs() {

		for (int time = 1; time <= T; time++) {

			int size = que.size();
			int minusGive = 0;
			for (int i = 0; i < size; i++) {
				Node node = que.poll();

				if (node.t == time)
					break;

				for (int d = 0; d < 4; d++) {
					int ny = node.y + dy[d];
					int nx = node.x + dx[d];

					if (ny < 0 || nx < 0 || ny >= R || nx >= C || map[ny][nx] == -1)
						continue;

					cost[ny][nx] += map[node.y][node.x] / 5;
					minusGive += map[node.y][node.x] / 5;
					que.offer(new Node(ny, nx, node.t + 1));
				}
				cost[node.y][node.x] -= minusGive;
				minusGive = 0;
			}

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += cost[i][j];
					cost[i][j] = 0;
				}
			}

			// 공기청정기 순환 코드
			rotate(); // 위쪽 청정기
			downRotate(); // 아래쪽 청정기

			que.clear();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (map[i][j] > 0) {
						que.offer(new Node(i, j, 0));
					}
				}
			}
		}

	}

	// 반시계 방향 인덱스 확인 용 델타 (우-하-좌-상)
	static int[] dy_R1 = { 0, 1, 0, -1 };
	static int[] dx_R1 = { 1, 0, -1, 0 };

	static void rotate() {
		int x = 0;
		int y = 0;

		int tmp = map[y][x];

		int d = 0;
		while (d < 4) {
			int ny = y + dy_R1[d];
			int nx = x + dx_R1[d];

			if (ny < 0 || nx < 0 || ny > cleanX[0] || nx >= C) {
				d++;
			} else {
				map[y][x] = map[ny][nx];
				y = ny;
				x = nx;
			}
		}
		map[1][0] = tmp;
		map[cleanX[0]][1] = 0;
		map[cleanX[0]][0] = -1;
	}

	// 시계 방향 인덱스 확인 용 델타
	static int[] dy_R2 = { 0, 1, 0, -1 };
	static int[] dx_R2 = { -1, 0, 1, 0 };

	static void downRotate() {

		int x = C - 1;
		int y = cleanX[1];

		int tmp = map[y][x];

		int d = 0;
		while (d < 4) {
			int ny = y + dy_R2[d];
			int nx = x + dx_R2[d];

			if (ny < cleanX[1] || nx < 0 || ny >= R || nx >= C) {
				d++;
			} else {
				map[y][x] = map[ny][nx];
				y = ny;
				x = nx;
			}
		}

		map[cleanX[1] + 1][C - 1] = tmp;
		map[cleanX[1]][1] = 0;
		map[cleanX[1]][0] = -1;
	}

	static class Node {
		int y;
		int x;
		int t;

		public Node(int y, int x, int t) {
			this.y = y;
			this.x = x;
			this.t = t;
		}
	}
}
