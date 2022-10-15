// SWEA 7793번 오! 나의 여신님

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_7793 {

	static int T, N, M, cnt;
	static char[][] map;

	static boolean flag;

	static Queue<Node> devil;
	static Queue<Node> que;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			devil = new ArrayDeque<>();
			que = new ArrayDeque<>();

			map = new char[N][];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'S') {
						que.offer(new Node(i, j));
					} else if (map[i][j] == '*') {
						devil.offer(new Node(i, j));
					}
				}
			}

			cnt = 1;
			flag = false;

			bfs();

			if (flag)
				sb.append("#").append(t).append(" ").append(cnt).append("\n");
			else
				sb.append("#").append(t).append(" ").append("GAME OVER").append("\n");
		}

		System.out.println(sb.toString());
	}

	static void bfs() {
		while (!que.isEmpty()) {
			int sSize = que.size();
			for (int i = 0; i < sSize; i++) {
				Node node = que.poll();

				for (int d = 0; d < 4; d++) {
					int ny = node.y + dy[d];
					int nx = node.x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == '*' || map[ny][nx] == 'X'
							|| map[ny][nx] == 'S')
						continue;

					if (map[ny][nx] == 'D') { // 여신님께 도착
						flag = true;
						return;
					}

					map[ny][nx] = 'S';
					que.offer(new Node(ny, nx));
				}
			}
			
			int devilSize = devil.size();
			for (int i = 0; i < devilSize; i++) {
				Node dev = devil.poll();

				for (int d = 0; d < 4; d++) {
					int ny = dev.y + dy[d];
					int nx = dev.x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 'X' || map[ny][nx] == 'D' || map[ny][nx] == '*')
						continue;

					if (map[ny][nx] == 'S') {
						int tSize = que.size();
						for (int k = 0; k < tSize; k++) {
							Node target = que.poll();
							
							if (ny != target.y || nx != target.x) que.add(target);
						}
					}
					map[ny][nx] = '*';
					devil.offer(new Node(ny, nx));
				}
			}

			cnt++;
		}

	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
