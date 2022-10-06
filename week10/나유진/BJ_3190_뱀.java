package STUDY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_3190_뱀 {
	static int N, K, L;
	static int[][] map; // 1은 뱀 위치(몸통까지), 2는 사과

	static int[] head = { 1, 1 }; // head x,y좌표
	static int[] tail = { 1, 1 }; // tail x,y좌표
	static int time = 0;
	static int d = 1; // 뱀 머리 방향
	static int len = 1; // 뱀 몸 길이

	static int[] dx = { 0, 1, 0, -1 }; // 상-우-하-좌 (시계방향)
	static int[] dy = { -1, 0, 1, 0 };

	static Queue<op> op_queue = new ArrayDeque<>();
	static Queue<Integer> queue = new ArrayDeque<>(); // 꼬리 없애기 방향 큐

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1]; // 0 dummy

		StringTokenizer st;
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);

			op_queue.offer(new op(t, c));
		}

		queue.offer(d);

		map[1][1] = 1;

		dummy();

		System.out.println(time);

		// 머리 한칸 움직이기
		// 몸과 부딫혔는지 판단
		// map[y][x] 가 1 이면 부딫혀서 종료
		// map[y][x] 가 2 이면 사과 먹고 몸길이 변경
		// map[y][x] 가 0 이면 몸길이 유지

		// 몸 길이 변경 : 꼬리 그대로
		// 몸 길이 유지 : 꼬리도 한칸 움직이기

		// op idx 0 번과 time이 같으면 op 수행 후 list에서 op삭제

	}

	private static void dummy() {
		while (true) {
			time++;

			head[0] += dy[d];
			head[1] += dx[d];

			if (!check(head[0], head[1]))
				return;

			if (map[head[0]][head[1]] == 1)
				return;

			else if (map[head[0]][head[1]] == 2) {
				map[head[0]][head[1]] = 1;
				len++;
			}

			else if (map[head[0]][head[1]] == 0) {
				map[head[0]][head[1]] = 1;
				int dir = queue.poll();
				map[tail[0]][tail[1]] = 0;
				tail[0] += dy[dir];
				tail[1] += dx[dir];
			}

			if (!op_queue.isEmpty()) {
				if (time == op_queue.peek().time) {
					if (op_queue.poll().dir == 'L') {
						d--;
						if (d == -1)
							d = 3;
					} else {
						d++;
						if (d == 4)
							d = 0;
					}
				}
			}

			queue.offer(d);

		}
	}

	private static boolean check(int y, int x) {
		return y >= 1 && y <= N && x >= 1 && x <= N;
	}

	static class op {
		int time;
		char dir;

		public op(int time, char dir) {
			super();
			this.time = time;
			this.dir = dir;
		}

	}
}
