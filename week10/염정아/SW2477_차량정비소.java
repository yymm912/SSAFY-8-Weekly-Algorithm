package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class SW2477_차량정비소 {
	static int ans, T, N, M, K, A, B;
	static int[] arr, brr, trr;

	static PriorityQueue<Node> wa = new PriorityQueue<>((e1, e2) -> e1.no - e2.no);
	static Node[] a;
	static Queue<Node> wb = new ArrayDeque<>();
	static Node[] b;
	static List<Node> res = new ArrayList<>();

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// 초기화
			ans = 0;
			wa.clear();
			a = new Node[N + 1];
			wb.clear();
			b = new Node[M + 1];
			res.clear();

			arr = new int[N + 1]; // 0 dummy
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());
			// System.out.println(Arrays.toString(arr));

			brr = new int[M + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) brr[i] = Integer.parseInt(st.nextToken());
			// System.out.println(Arrays.toString(brr));

			trr = new int[K + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) trr[i] = Integer.parseInt(st.nextToken());
			// System.out.println(Arrays.toString(trr));

			// 시뮬레이션
			int time = 0;
			while (true) {
				if (res.size() == K) break;
				// System.out.println("\ntime: " + time);

				// 해당 시간에 도착한 사람들 뽑아서 wa에 넣기
				for (int i = 1; i <= K; i++) {
					if (trr[i] == time) wa.add(new Node(i, -1, -1, 0, 0));
				}

				// System.out.println("wa(접수창구 웨이팅): " + wa);

				// 접수창구의 수만큼 wa 에서 빼내 a에 넣기
				for (int i = 1; i <= N; i++) {
					if (a[i] == null && !wa.isEmpty()) {
						a[i] = wa.poll();
						a[i].ai = i; // 접수창구 번호 입력
					}

				}

				// 접수창구에서 보낸 시간 더하기
				for (int i = 1; i <= N; i++) {
					if (a[i] != null) a[i].atime++;
				}

				// System.out.println("a(접수창구): " + Arrays.toString(a));

				// 시간이 지나면 a에 꺼내서 wb에 넣기
				for (int i = 1; i <= N; i++) {
					if (a[i] != null && a[i].atime == arr[i]) {
						wb.offer(a[i]);
						a[i] = null; // 빈 접수창구 표시
					}

				}

				// System.out.println("wb(정비창구 웨이팅): " + wb);

				// 정비창구의 수만큼 wb에서 꺼내 b에 넣기
				for (int i = 1; i <= M; i++) {
					if (b[i] == null && !wb.isEmpty()) {
						b[i] = wb.poll();
						b[i].bi = i;
					}

				}

				// 정비창구에서 보낸 시간 더하기
				for (int i = 1; i <= M; i++) {
					if (b[i] != null) b[i].btime++;
				}

				// System.out.println("b(정비창구): " + Arrays.toString(b));

				// 다 했으면 나오기
				for (int i = 1; i <= M; i++) {
					if (b[i] != null && b[i].btime == brr[i]) {
						res.add(b[i]);
						b[i] = null; // 빈 접수창구 표시
					}

				}

				// System.out.println("다한 고객: " + res);
				//
				// System.out.println("====끝====");
				// System.out.println("wa(접수창구 웨이팅): " + wa);
				// System.out.println("a(접수창구): " + Arrays.toString(a));
				// System.out.println("wb(정비창구 웨이팅): " + wb);
				// System.out.println("b(정비창구): " + Arrays.toString(b));
				// System.out.println("다한 고객: " + res);

				time++;
			}

			// System.out.println("다한 고객: " + res);

			ans = check();
			if (ans == 0) ans = -1;

			// 출력
			sb.append("#").append(tc).append(" ").append(ans).append("\n");

			// break;
		}

		System.out.println(sb.toString());

	}


	// A, B 창구를 이용한 고객을 찾는 함수
	private static int check() {
		int sum = 0;

		for (Node node : res) {
			if (node.ai == A && node.bi == B) sum += node.no;
		}

		return sum;
	} // end check


	static class Node {
		int no;
		int ai;
		int bi;
		int atime; // 접수창구에서 기다린 시간
		int btime; // 정비창구에서 기다린 시간


		public Node(int no, int ai, int bi, int atime, int btime) {
			this.no = no;
			this.ai = ai;
			this.bi = bi;
			this.atime = atime;
			this.btime = btime;
		}


		@Override
		public String toString() {
			return "Node [no=" + no + ", ai=" + ai + ", bi=" + bi + ", atime=" + atime + ", btime="
			    + btime + "]";
		}

	} // end Node
}
