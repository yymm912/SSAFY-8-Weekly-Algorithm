package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class SW2383_점심식사시간 {
	static int ans, T, N, nodeSize;
	static int[] tgt;
	static List<Node> nodes = new ArrayList<>();
	static List<Stairs> stairs = new ArrayList<>();
	static boolean flag = false;
	static int cnt;

	static Queue<Node> wa = new ArrayDeque<>();
	static Queue<Node> a = new ArrayDeque<>();
	static Queue<Node> wb = new ArrayDeque<>();
	static Queue<Node> b = new ArrayDeque<>();
	static List<Node> res = new ArrayList<>(); // 이동 완료한 사람들

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			// 초기화
			ans = Integer.MAX_VALUE;
			nodes.clear();
			stairs.clear();

			for (int i = 0, idx = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int m = Integer.parseInt(st.nextToken());
					if (m == 0) continue;
					else if (m == 1) nodes.add(new Node(i, j, ++idx, 0));
					else stairs.add(new Stairs(i, j, m));
				}

			}

			nodeSize = nodes.size();
			tgt = new int[nodeSize];

			// 탐색
			perm(0);

			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");

			// break;

		}

		System.out.println(sb.toString());

	} // end main


	// 사람들을 0번, 1번 계단에 배치시키는 함수
	private static void perm(int dep) {
		if (dep == nodeSize) {
			// 초기화
			wa.clear();
			a.clear();
			wb.clear();
			b.clear();
			res.clear();
			for (Node node : nodes) node.wait = 0;

			go();

			return;
		}

		for (int i = 0; i < 2; i++) {
			tgt[dep] = i;
			perm(dep + 1);
		}

	} // end perm


	// 시간을 재면서 사람들이 계단으로 이동하는 함수
	private static void go() {
		int time = 0;
		Stairs s0 = stairs.get(0); // 계단
		Stairs s1 = stairs.get(1); // 계단

		while (true) {
			time++;

			if (res.size() == nodeSize) {
				ans = Math.min(ans, time + 1);
				return;
			}

			// 0번 계단 입구에 입구까지 온 사람들을 대기 시킨다.
			// 1번 계단 입구에 입구까지 온 사람들을 대기 시킨다.
			for (int i = 0; i < nodeSize; i++) {
				if (tgt[i] == 0) {
					Node n = nodes.get(i); // 사람

					int dis = extent(n.y, n.x, s0.y, s0.x);
					if (dis == time) wa.add(n);
				} else if (tgt[i] == 1) {
					Node n = nodes.get(i); // 사람

					int dis = extent(n.y, n.x, s1.y, s1.x);
					if (dis == time) wb.add(n);
				}

			}

			// 허용 가능한 3명만큼 계단에 배치한다.
			while (true) {
				if (a.size() < 3 && !wa.isEmpty()) a.add(wa.poll());
				else break;
			}

			while (true) {
				if (b.size() < 3 && !wb.isEmpty()) b.add(wb.poll());
				else break;
			}

			// 0번 계단을 통해서 내려가는 과정 시간 세기
			// 1번 계단을 통해서 내려가는 과정 시간 세기
			// 도착하고 나서 그 다음부터 시간을 센다.
			for (Node node : a) node.wait++;
			for (Node node : b) node.wait++;

			// 만약 계단을 다 내려갔다면 res 배열에 추가
			for (Node node : a) if (node.wait == s0.time) res.add(a.poll());
			for (Node node : b) if (node.wait == s1.time) res.add(b.poll());

		}

	} // end go


	private static int extent(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	} // end extent


	static class Node {
		int y, x;
		int no; // 사람의 번호
		int wait; // 기다린 시간


		public Node(int y, int x, int no, int wait) {
			this.y = y;
			this.x = x;
			this.no = no;
			this.wait = wait;
		}


		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", no=" + no + ", wait=" + wait + "]";
		}

	} // end Node


	static class Stairs {
		int y, x;
		int time; // 내려가는 데 걸리는 시간


		public Stairs(int y, int x, int time) {
			super();
			this.y = y;
			this.x = x;
			this.time = time;
		}


		@Override
		public String toString() {
			return "Stairs [y=" + y + ", x=" + x + ", time=" + time + "]";
		}

	} // end Stairs
}
