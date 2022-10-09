package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 악마를 먼저 이동한 후 수연이가 이동 D에 도착하면 answer 값 출력 or 사방 탐색할 때 더이상 갈 곳이 없다면 game over
 */

public class SWEA_7793_오나의여신님 {
	static int T;
	static int N, M;
	static char map[][];
	static int answer;
	static boolean check;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static boolean[][] visited;
	static Queue<Node> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			q.clear();
			answer = 0;
			check = false;
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new char[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			initQueue();
			bfs();
			if (check) {
				System.out.println("#" + t + " " + answer);
			} else {
				System.out.println("#" + t + " " + "GAME OVER");
			}

		}

	}

	static void initQueue() {
		// * 이면 악마
		// S 수연이의 위치
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '*')
					q.offer(new Node(i, j, '*'));
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'S') {
					q.offer(new Node(i, j, 'S'));
					visited[i][j] = true;
				}

			}
		}

	}

	static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Node cur = q.poll();

				if (cur.ch == '*') {
					for (int d = 0; d < 4; d++) {
						int ny = cur.y + dy[d];
						int nx = cur.x + dx[d];

						if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 'X' || map[ny][nx] == 'D'
								|| map[ny][nx] == '*')
							continue;

						map[ny][nx] = '*';
						q.offer(new Node(ny, nx, '*'));

					}
				}

				else if (cur.ch == 'S') {
					for (int d = 0; d < 4; d++) {
						int ny = cur.y + dy[d];
						int nx = cur.x + dx[d];

						if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 'X' || map[ny][nx] == '*'
								|| map[ny][nx] == 'S' || visited[ny][nx]) {

							continue;
						}

						// 다음 위치가 여신이 있는 곳
						if (map[ny][nx] == 'D') {
							answer++;
							check = true;
							return;
						}
						map[cur.y][cur.x] = '.';
						map[ny][nx] = 'S';
						visited[ny][nx] = true;
						q.offer(new Node(ny, nx, 'S'));
					}

			
				}
				
			}
			answer++;

		}

	}

	static class Node {
		int y;
		int x;
		char ch;

		public Node(int y, int x, char ch) {
			this.y = y;
			this.x = x;
			this.ch = ch;
		}
	}
}
