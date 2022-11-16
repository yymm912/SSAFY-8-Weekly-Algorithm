package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class SW1824_혁진이의프로그램검증 {
	static int T, R, C, memory;
	static String ans;
	static boolean isOk;
	static boolean[][][][] visit;
	static char[][] map;

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			// 초기화
			ans = "NO";
			memory = 0;
			isOk = false;

			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					char m = map[i][j] = line.charAt(j);
					if (m == '@') isOk = true;
				}

			}

			// 시뮬레이션
			if (isOk) {
				bfs();
				System.out.println("#" + t + " " + ans);
			} else System.out.println("#" + t + " " + "NO");

			// break;

		}

	} // end main


	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		visit = new boolean[R][C][4][16]; // (y, x, 방향, 메모리)

		q.offer(new Node(0, 0, 3));
		visit[0][0][3][0] = true;
		int cnt = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int y = node.y;
			int x = node.x;
			int d = node.d;

			if (cnt > 1000) return;

			if (map[y][x] == '@') {
				ans = "YES";
				return;
			} else if (Character.isDigit(map[y][x])) memory = map[y][x] - '0';
			else if (map[y][x] == '.') d = d;
			else if (map[y][x] == '^') d = 0;
			else if (map[y][x] == 'v') d = 1;
			else if (map[y][x] == '<') d = 2;
			else if (map[y][x] == '>') d = 3;
			else if (map[y][x] == '_') {
				if (memory == 0) d = 3;
				else d = 2;
			} else if (map[y][x] == '|') {
				if (memory == 0) d = 1;
				else d = 0;
			} else if (map[y][x] == '+') {
				if (memory == 15) memory = 0;
				else memory++;
			} else if (map[y][x] == '-') {
				if (memory == 0) memory = 15;
				else memory--;
			} else if (map[y][x] == '?') {
				for (int dir = 0; dir < 4; dir++) {
					int ny = y + dy[dir];
					int nx = x + dx[dir];

					if (ny < 0) ny = R - 1;
					else if (ny >= R) ny = 0;
					else if (nx < 0) nx = C - 1;
					else if (nx >= C) nx = 0;

					if (visit[ny][nx][dir][memory]) continue;

					q.offer(new Node(ny, nx, dir));
					cnt++;
					visit[ny][nx][dir][memory] = true;
					continue;
				}

			}

			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0) ny = R - 1;
			else if (ny >= R) ny = 0;
			else if (nx < 0) nx = C - 1;
			else if (nx >= C) nx = 0;

			q.offer(new Node(ny, nx, d));
			visit[ny][nx][d][memory] = true;
			cnt++;

		}

	} // end bfs


	static class Node {
		int y, x;
		int d;


		public Node(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}

	} // end Node
}
