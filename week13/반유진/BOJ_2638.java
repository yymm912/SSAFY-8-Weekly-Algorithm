// BOJ 2638번 치즈 (골드 3) 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_2638 {

	static int N, M, ans;
	static int[][] map;

	static Queue<Node> que, cheese;
	static boolean[][] visit;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			if (count()) // 치즈가 남아있는지 확인
				break;

			bfs();

			ans++;
		}

		System.out.println(ans);
	}

	static void bfs() {
		que = new ArrayDeque<>();
		cheese = new ArrayDeque<>();
		visit = new boolean[N][M];

		que.offer(new Node(0, 0));
		visit[0][0] = true;
		map[0][0] = 3;

		while (!que.isEmpty()) {
			Node node = que.poll();

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] == 2)
					continue;

				if (map[ny][nx] == 1) { // 치즈의 가장자리 모두 2로 바꾸고 큐에 넣기
					visit[ny][nx] = true;
					map[ny][nx] = 2;
					cheese.offer(new Node(ny, nx));
				} else if (map[ny][nx] == 0) {
					visit[ny][nx] = true;
					map[ny][nx] = 3; // 외부 공기 다 3으로 변경 (외부 내부 구별하기 위해) 
					que.offer(new Node(ny, nx));
				}
			}
		}

		boolean[][] visit_ch = new boolean[N][M];
		while (!cheese.isEmpty()) {
			Node c = cheese.poll();
			visit_ch[c.y][c.x] = true;

			int cnt = 0;
			for (int d = 0; d < 4; d++) { // 치즈의 가장자리들 중 접촉한 곳이 공기인 곳 칸 수 세기
				int cy = c.y + dy[d];
				int cx = c.x + dx[d];

				if (cy < 0 || cx < 0 || cy >= N || cx >= M || visit_ch[cy][cx])
					continue;

				if (map[cy][cx] == 3) { // 근처가 공기인 수 세기
					visit_ch[c.y][c.x] = true; // 같은 시간에 녹은 치즈 visit 체크
					cnt++;
				}
			}

			if (cnt >= 2) // 주위 공기 칸이 2 이상이면
				map[c.y][c.x] = 3; // 치즈 녹이기
			else // 아니면 치즈 그대로 다시 1로 두기
				map[c.y][c.x] = 1;
		}
	}

	static boolean count() { // 판에 남아있는 치즈 수 세면서 초기화
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 3) // map 초기화
					map[i][j] = 0;
				if (map[i][j] == 1) {
					cnt++;
				}
			}
		}

		if (cnt == 0) // 치즈가 다 녹았으면 true 리턴
			return true;
		return false;
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
