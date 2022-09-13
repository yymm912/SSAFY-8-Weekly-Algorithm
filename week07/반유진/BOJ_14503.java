// BOJ 14503번 로봇 청소기 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_14503 {

	static int N, M, sum, d, sy, sx;
	static Queue<Node> robotQ = new ArrayDeque<>();
	static int[][] map;

	// 상-우-하-좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken());
		sx = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		robotQ.add(new Node(sy, sx));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		map[sy][sx] = -1;

		sum = 1;
		bfs();

		System.out.println(sum);
	}

	static void bfs() {
		int ny, nx;

		int cnt = 2;

		outer: while (!robotQ.isEmpty()) {
			Node node = robotQ.poll();

			for (int i = 0; i < 4; i++) {
				d = d == 0 ? 3 : d - 1; // 현재 방향에서 왼쪽으로

				ny = node.y + dy[d];
				nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 1 || map[ny][nx] != 0) {
					continue;
				}

				map[ny][nx] = cnt++;
				sum++;

				robotQ.add(new Node(ny, nx));

				continue outer;
			}

			ny = node.y - dy[d];
			nx = node.x - dx[d];
			robotQ.add(new Node(ny, nx));

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 1)
				return;
		}
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

}
