package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238_스타트택시 {
	// N : map 크기, M : 손님 수, F: 초기 연료 크기
	static int N, M, F;
	static int[][] map;

	// 택시 위치
	static Node taxi;

	// 손님의 도착지 리스트 . 손님 번호로 index 접근
	static Node[] list;

	// 택시 이동 방향
	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	// bfs depth. 연료 사용 량
	static int D;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1]; // 0 dummy

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// 문제의 1 : 벽, 0 : 빈칸 => -1: 벽, -2: 빈칸
				// 0, 1, 2 .. 는 손님번호로 map 표시
				map[i][j] = Integer.parseInt(st.nextToken()) - 2;
			}
		}

		st = new StringTokenizer(br.readLine());
		taxi = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		list = new Node[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// 손님 출발 위치
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());

			// 손님 도착지
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());

			// map 에 손님 출발 위치 표시 ( 문제에서 손님의 출발지는 각가 다르다고 했기 때문에 가능)
			map[sy][sx] = i;

			// 도착지는 list로 따로 저장. i번 손님은 list i번에 도착지 저장
			list[i] = new Node(ey, ex);
		}

		// # 전략
		// 1. simul 함수 손님을 태우고 도착지 내려놓고 반복. 만약 손님을 더이상 찾지 못하는 데 문제에서 주어진 손님 수와 일치 X ->
		// false 반환. 운행 실패로 -1 출력
		// 문제에서 주어진 손님 수와 일치 -> true 반환. 그 때의 연료 출력

		// # simul()
		// searchGuest() 로 택시 위치에서 bfs 하여 가장 가까운 손님 찾음
		// PriorityQueue에 손님을 담아서 가장 가깝고, 왼쪽 상향에 있는 손님을 태움

		// 태울 손님이 있으면 moveguest()
		// 현재의 연료 - 손님을 태울때까지 쓴 연료 하여 0 보다 작으면 실패

		// 실패 X 면 searchDestination()
		// 손님의 위치에서 bfs 하며 list[손님 index] 의 도착지가 나올때까지 반복
		// 도착지로 갈 수 있으면 true 반환. 불가능 하면 false 반환

		// 도착하면 연료 - 도착지까지 쓴 연료 가 0보다 작으면 실패
		// 성공하면 그 두배 적립.

		// moveguest 성공하면 손님을 map에서 지움(빈칸으로 변경). taxi 위치를 손님의 도착지로 갱신.

		if (simul())
			System.out.println(F);
		else
			System.out.println(-1);

	}

	static boolean simul() {
		int cnt = 0;
		while (true) {
			PriorityQueue<Node> guest = searchGuest(taxi);

			Node G = guest.poll();
			if (G == null)
				break;
			cnt++;
			if (!moveguest(G))
				return false;
			map[G.y][G.x] = -2;
			taxi.y = list[G.s].y;
			taxi.x = list[G.s].x;
		}
		if (cnt == M)
			return true;
		else
			return false;
	}

	static PriorityQueue<Node> searchGuest(Node t) {
		boolean[][] visit = new boolean[N + 1][N + 1]; // 0 dummy
		Queue<Node> queue = new ArrayDeque<>();
		PriorityQueue<Node> pq = new PriorityQueue<>((p1, p2) -> p1.y == p2.y ? p1.x - p2.x : p1.y - p2.y);

		visit[t.y][t.x] = true;
		queue.offer(t);

		D = 0; // distance

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node temp = queue.poll();
				if (map[temp.y][temp.x] != -1 && map[temp.y][temp.x] != -2) { // 벽도 아니고 빈칸도 아님 >> 손님 만남
					temp.s = map[temp.y][temp.x];
					pq.offer(temp);
				} else {
					for (int j = 0; j < 4; j++) {
						int ny = temp.y + dy[j];
						int nx = temp.x + dx[j];

						if (!check(ny, nx))
							continue;

						if (map[ny][nx] != -1 && !visit[ny][nx]) {
							queue.offer(new Node(ny, nx));
							visit[ny][nx] = true;
						}
					}
				}
			}

			if (!pq.isEmpty())
				break;
			D++;
		}

		return pq;
	}

	static boolean searchDestination(Node guest) {
		boolean[][] visit = new boolean[N + 1][N + 1]; // 0 dummy
		Queue<Node> queue = new ArrayDeque<>();
		visit[guest.y][guest.x] = true;
		queue.offer(guest);
		D = 0; // distance

		int gy = list[guest.s].y;
		int gx = list[guest.s].x;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node temp = queue.poll();
				if (temp.y == gy && temp.x == gx) { // 목적지 도착
					return true;
				} else {
					for (int j = 0; j < 4; j++) {
						int ny = temp.y + dy[j];
						int nx = temp.x + dx[j];

						if (!check(ny, nx))
							continue;

						if (map[ny][nx] != -1 && !visit[ny][nx]) {
							queue.offer(new Node(ny, nx));
							visit[ny][nx] = true;
						}
					}
				}
			}
			D++;
		}

		return false;

	}

	private static boolean check(int y, int x) {
		return y > 0 && y <= N && x > 0 && x <= N;
	}

	static boolean moveguest(Node node) { // distance 빼고, 손님 위치에서 dfs 다시. destination 도착하면 연료 *2 채우고 못가면 실패
		F -= D;
		if (F < 0)
			return false;

		if (searchDestination(node)) {
			F -= D;
			if (F < 0)
				return false;
			F += D * 2;
			return true;
		} else
			return false;
	}

	static class Guest {
		Node destination;
	}

	static class Node {
		int y, x;
		int s;

		public Node() {
		}

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", s=" + s + "]";
		}

	}
}
