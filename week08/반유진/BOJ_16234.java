// BOJ 16234번 인구 이동 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_16234 {

	static int N, L, R, day, size, check;
	static int[][] map;
	static boolean[][] visit;
	static List<Node> list;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visit = new boolean[N][N];
			check = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						bfs(i, j);
						check++;
					}
				}
			}

			if (check == N * N) {
				break;
			}

			day++;
		}

		System.out.println(day);
	}

	static void bfs(int y, int x) {
		Queue<Node> que = new ArrayDeque<>();
		list = new ArrayList<>();

		list.add(new Node(y, x, map[y][x]));
		que.offer(new Node(y, x, map[y][x]));
		visit[y][x] = true;

		size = 1; // 리스트 크기

		while (!que.isEmpty()) {
			Node node = que.poll();

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx]) {
					continue;
				}

				// 옆 나라와의 인구 수 차이가 L ~ R 이어야 함
				if (L > Math.abs(map[node.y][node.x] - map[ny][nx])
						|| Math.abs(map[node.y][node.x] - map[ny][nx]) > R) {
					continue;
				}

				// 연합 list에 추가
				list.add(new Node(ny, nx, map[ny][nx]));
				size++;

				que.offer(new Node(ny, nx, map[ny][nx]));
				visit[ny][nx] = true;
			}
		}

		// 각 나라에 연합 인구 수 계산하여 인구 이동 시키기
		int sum = 0;
		for (int i = 0; i < size; i++) {
			Node n = list.get(i);
			sum += map[n.y][n.x];
		}
		sum /= size;

		for (int i = 0; i < size; i++) {
			Node n = list.get(i);
			map[n.y][n.x] = sum;
		}
	}

	static class Node {
		int y;
		int x;
		int p; // 인구 수

		public Node(int y, int x, int p) {
			this.y = y;
			this.x = x;
			this.p = p;
		}
	}
}
