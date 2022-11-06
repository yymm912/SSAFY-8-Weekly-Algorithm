package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_19236_청소년상어 {
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 8개 방향
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Node[][] Map = new Node[4][4];
	static Node Shark = new Node();
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int number = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1; // 방향이 1부터 시작하기 때문에 -1 해서 index를 가리킬 수 있게함
				Map[i][j] = new Node(number, dir, i, j);
			}
		}

		// map 입력 끝

		makeShark(); // 0,0 초기 상어 위치 & 방향 설정
		simul(Map, Shark, max); // 시뮬레이션 시작

		// 전략
		// 1. 물고기 이동 list sort 해서 작은 것부터 이동 시작
		// 2. 상어가 먹을 수 있는 후보 list 담기. 후보지 다 돌며 먹기.-> 재귀로 simul() 계속 수행(1,2 반복) 먹을 수 없을 때
		// 까지 sum 해서 max 갱신

		System.out.println(max);
	}

	private static void makeShark() {
		Node node = Map[0][0];
		Map[0][0] = null;

		Shark.dir = node.dir;
		Shark.y = 0;
		Shark.x = 0;
		Shark.number = node.number;
		max = node.number;

	}

	private static void simul(Node[][] map, Node shark, int sum) {
		Node[][] copy = copyMap(map);
		copy = moveFish(copy, shark);
		List<Node> list = makeList(copy, shark); // 상어가 갈 수 있는 후보지 얻음.
		for (Node node : list) {
			Node[][] copy2 = copyMap(copy);

			copy2[node.y][node.x] = null; // 상어 위치 이동 후 물고기 먹기
			simul(copy2, node, sum + node.number);
			max = Math.max(max, sum + node.number);
		}

	}

	private static List<Node> makeList(Node[][] map, Node shark) {
		List<Node> list = new ArrayList<>();
		int ny = shark.y;
		int nx = shark.x;

		while (true) {
			ny += dy[shark.dir];
			nx += dx[shark.dir];
			
			if(!check(ny, nx))break;
			if (map[ny][nx] == null)
				continue;

			list.add(new Node(map[ny][nx].number, map[ny][nx].dir, ny, nx));

		}

		return list;
	}

	private static Node[][] copyMap(Node[][] map) {
		Node[][] copy = new Node[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null) {
					copy[i][j] = null;
				} else
					copy[i][j] = new Node(map[i][j].number, map[i][j].dir, i, j);
			}
		}

		return copy;
	}

	private static Node[][] moveFish(Node[][] map, Node shark) {
		PriorityQueue<Node> pq = new PriorityQueue<>((p1, p2) -> p1.number - p2.number);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] != null)
					pq.add(map[i][j]);
			}
		}

		while (!pq.isEmpty()) {
			Node fish = pq.poll(); // 번호가 작은 물고기 부터 이동 시작

			for (int i = 0; i < 8; i++) {
				int idx = (fish.dir + i) % 8;

				int ny = fish.y + dy[idx];
				int nx = fish.x + dx[idx];

				if (!check(ny, nx))
					continue; // map 범위 체크

				if (ny == shark.y && nx == shark.x)
					continue; // 상어 있는 곳은 못감

				if (map[ny][nx] == null) {
					map[fish.y][fish.x] = null;

					// 물고기 위치 & 방향 갱신
					fish.dir = idx;
					fish.y = ny;
					fish.x = nx;

				} else {
					// fish swap
					Node temp = map[ny][nx];

					temp.y = fish.y;
					temp.x = fish.x;

					map[fish.y][fish.x] = temp;

					// 물고기 위치 & 방향 갱신
					fish.dir = idx;

					fish.y = ny;
					fish.x = nx;
				}

				map[ny][nx] = fish;

				break;
			}

		}

		return map;
	}

	private static boolean check(int y, int x) {
		return y >= 0 && y < 4 && x >= 0 && x < 4;
	}

	static class Node {
		int number;
		int dir;
		int y, x; // 상어만 위치를 알아야하기 때문에 넣어놓기. 생성자 X

		public Node() {
		}

		public Node(int number, int dir, int y, int x) {
			this.number = number;
			this.dir = dir;
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "Node [number=" + number + ", dir=" + dir + ", y=" + y + ", x=" + x + "]";
		}

	}
}
