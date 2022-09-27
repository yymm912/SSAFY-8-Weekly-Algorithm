package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ9019_DSLR {
	static int T, src, tgt;
	static String ans;
	static boolean[] visit;

	static Queue<Node> q = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			src = Integer.parseInt(st.nextToken());
			tgt = Integer.parseInt(st.nextToken());

			// 초기화
			ans = "";
			visit = new boolean[10000];
			q.clear();

			// 탐색
			bfs();

			// 출력
			System.out.println(ans);

		}

	} // end main


	private static void bfs() {
		q.offer(new Node(src, ""));
		visit[src] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int cur = node.cur;

			if (cur == tgt) {
				ans = node.ans;
				return;
			}

			int D = (cur * 2) % 10000;
			if (!visit[D]) {
				q.offer(new Node(D, node.ans + "D"));
				visit[D] = true;
			}

			int S = (cur == 0) ? 9999 : cur - 1;
			if (!visit[S]) {
				q.offer(new Node(S, node.ans + "S"));
				visit[S] = true;
			}

			int L = (cur % 1000) * 10 + cur / 1000;
			if (!visit[L]) {
				q.offer(new Node(L, node.ans + "L"));
				visit[L] = true;
			}

			int R = (cur % 10) * 1000 + cur / 10;
			if (!visit[R]) {
				q.offer(new Node(R, node.ans + "R"));
				visit[R] = true;
			}

		}

	} // end bfs


	static class Node {
		int cur;
		String ans;


		public Node(int cur, String ans) {
			super();
			this.cur = cur;
			this.ans = ans;
		}


		@Override
		public String toString() {
			return "Node [cur=" + cur + ", ans=" + ans + "]";
		}

	} // end Node

}
