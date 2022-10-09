package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거 {
	static int T;
	static int N, M;
	static int R, C;
	static int L;
	static int[][] map;

	static int answer;

	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			/* 입력 끝 */
			bfs(R, C);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j])
						answer++;
				}
			}

			System.out.println("#" + t + " " + answer);

		}

	}

	static boolean check(int ny, int nx) {
		if (ny < 0 || nx < 0 || ny >= N || nx >= M)
			return false;
		if (visited[ny][nx])
			return false;
		if (map[ny][nx] == 0)
			return false;

		return true;
	}

	static void bfs(int R, int C) {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { R, C });
		visited[R][C] = true;
		L--;
		while (!q.isEmpty()) {
			L--;

			if (L < 0)
				break;
			int size = q.size();

			for (int s = 0; s < size; s++) {
				int top[] = q.poll();

				// 터널 번호
				int tunnel = map[top[0]][top[1]];

				int dy[];
				int dx[];

				switch (tunnel) {
				case 1:
					// 상하좌우
					dy = new int[] { -1, 1, 0, 0 };
					dx = new int[] { 0, 0, -1, 1 };

					for (int d = 0; d < 4; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];

						if (check(ny, nx)) {
							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							} else if (d == 2) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							} else if (d == 3) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							}

						}
					}

					break;

				case 2:
					// 상하
					dy = new int[] { -1, 1 };
					dx = new int[] { 0, 0 };

					for (int d = 0; d < 2; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];
						if (check(ny, nx)) {
							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							}

						}
					}
					break;

				case 3:
					// 좌우
					dy = new int[] { 0, 0 };
					dx = new int[] { -1, 1 };

					for (int d = 0; d < 2; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];
						if (check(ny, nx)) {
							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							}
						}
					}

					break;

				case 4:
					// 상우
					dy = new int[] { -1, 0 };
					dx = new int[] { 0, 1 };

					for (int d = 0; d < 2; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];
						if (check(ny, nx)) {

							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							}
						}
					}

					break;

				case 5:
					// 하우
					dy = new int[] { 1, 0 };
					dx = new int[] { 0, 1 };

					for (int d = 0; d < 2; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];
						if (check(ny, nx)) {
							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 6 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							}
						}
					}
					break;

				case 6:
					// 하좌
					dy = new int[] { 1, 0 };
					dx = new int[] { 0, -1 };

					for (int d = 0; d < 2; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];
						if (check(ny, nx)) {
							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 4 || map[ny][nx] == 7) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							}
						}
					}

					break;

				case 7:
					// 상좌
					dy = new int[] { -1, 0 };
					dx = new int[] { 0, -1 };

					for (int d = 0; d < 2; d++) {
						int ny = top[0] + dy[d];
						int nx = top[1] + dx[d];
						if (check(ny, nx)) {
							if (d == 0) {
								if (map[ny][nx] == 1 || map[ny][nx] == 2 || map[ny][nx] == 5 || map[ny][nx] == 6) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
							} else if (d == 1) {
								if (map[ny][nx] == 1 || map[ny][nx] == 3 || map[ny][nx] == 4 || map[ny][nx] == 5) {
									q.offer(new int[] { ny, nx });
									visited[ny][nx] = true;
								}

							}
						}
					}

					break;

				}

			}

		}
	}
}