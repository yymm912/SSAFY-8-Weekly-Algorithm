package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ3190뱀 {

	static int N, K, L;
	static int r, c;
	static int map[][];
	static int a, b;
	static int X;
	static char C;
	static Deque<Item> q = new ArrayDeque<>();
	static int time = 0;
	static int ans;
	static int dy[] = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int dx[] = { 1, 0, -1, 0 };
	static int d;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 보드의 크기 N
		K = Integer.parseInt(br.readLine()); // 사과의 개수

		map = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; // 사과의 위치
		}

		// X 초까지 움직인 후 C방향으로 전환
		q.offer(new Item(1, 1, time));
		d = 0; // 뱀의 방향은 항상 오른쪽부터 시작

		L = Integer.parseInt(br.readLine()); // 뱀의 방향 전환 횟수
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			C = st.nextToken().charAt(0);

			move();

			if (time == X) {
				// 방향 전환
				if (C == 'L') { // 현재 방향 기준 왼쪽 : 반시계방향
					d--;
					if (d < 0)
						d = 3;

				} else if (C == 'D') { // 현재 방향 기준 오른쪽 : 시계방향 (우하좌상)
					d++;
					if (d > 3)
						d = 0;

				}
			}
			if (i == L - 1) // 마지막에는 X를 최댓값으로 넣고 시도
			{
				X = Integer.MAX_VALUE;
				move();
			}

		}
		// 입력 완

		System.out.println(time + 1);

	}

	static void move() {

		while (time < X) {

			Item e = q.peekLast();
			int py = e.y, px = e.x;

			py = e.y + dy[d];
			px = e.x + dx[d];

			if (py > N || px > N || py <= 0 || px <= 0)
				break; // 벽에 닿으면 종료
			// 만약 큐에 똑같은 py px 좌표가 있다면 break

			for (Item i : q) {
				if (i.x == px && i.y == py) {
					flag = true;
					break;
				}
			}
			if (flag)
				break;

			if (map[py][px] != 1)
				q.remove(); // 사과가 있으면 꼬리 안없앰
			else
				map[py][px] = 0;

			q.offer(new Item(py, px, time));

			time++;
		}

	}

	static class Item {
		int y;
		int x;
		int time;

		public Item(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}

	}
}
