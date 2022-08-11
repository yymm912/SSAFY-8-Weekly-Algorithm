package algorithm_assignments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2178_미로탐색 {
	static int N, M;
	static int[] dx = { -1, 0, 1, 0 }; // 상 -우 -하- 좌
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visit; // 방문 체크
	static char[][] arr; // 미로 저장
	static Queue<Node> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		queue.offer(new Node(0, 0));
		int d = 1;
		while (!queue.isEmpty()) { // queue가 빌 때 까지 계속

			int size = queue.size(); // queue size 저장 => distance계산을 위해 같은 단계 건너온 친구들 한번만 d++ 되도록

			for (int i = 0; i < size; i++) {
				Node node = queue.poll();

				if (node.x == N - 1 && node.y == M - 1) { // 도착했는지 확인
					System.out.println(d); // d 출력 후 main 문 종료
					return;
				}

				bfs(node.x, node.y); // 도착 안했으면 다시 탐색
			}

			++d;
		}

	}

	static void bfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!check(nx, ny))
				continue;

			if (arr[nx][ny] == '1' && !visit[nx][ny]) { // 방문 가능하고 1이면 경로 queue에 저장
				queue.offer(new Node(nx, ny));
				visit[nx][ny] = true;
			}
		}

	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	static boolean check(int n, int m) {
		return n >= 0 && n >= 0 && m < N && m < M;
	}
}
