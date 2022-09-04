// BOJ 3190번 뱀

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_3190 {

	static int N, K, L, ans;
	static int[] time;
	static char[] dir;
	static int[][] map;

	// 우-상-좌-하
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y - 1][x - 1] = 2;
		}

		L = Integer.parseInt(br.readLine());
		time = new int[L];
		dir = new char[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			time[i] = X;
			dir[i] = C;
		}

		bfs();

		System.out.println(ans);
	}

	static void bfs() {
		Queue<Node> que = new ArrayDeque<>();
		que.add(new Node(0, 0));
		map[0][0] = 1; // 뱀이 있는 자리
		int d = 0;
		int second = 0;
		int px = 0, py = 0;

		while (!que.isEmpty()) {
			// 방향 변환 시간 시 방향 전환
			for (int i = 0; i < L; i++) {
				if (time[i] == second) {
					if (dir[i] == 'L')
						d = d == 3 ? 0 : d + 1; // 현재 방향에서 왼쪽으로
					else if (dir[i] == 'D')
						d = d == 0 ? 3 : d - 1; // 현재 방향에서 오른쪽으로
				}
			}

			int ny = py + dy[d];
			int nx = px + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 1) {
				ans = second + 1;
				return;
			}

			if (map[ny][nx] == 2) { // 사과가 있다.
				map[ny][nx] = 1;
			} else if (map[ny][nx] == 0) { // 빈 공간
				Node tail = que.poll();
				map[tail.y][tail.x] = 0;
			}

			que.offer(new Node(ny, nx));
			map[ny][nx] = 1;

			py = ny;
			px = nx;
			second++;
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
