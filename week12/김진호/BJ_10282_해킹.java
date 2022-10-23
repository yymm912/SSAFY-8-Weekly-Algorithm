package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_10282_해킹 {
	static int T;
	static int n, d, c;
	static int a, b, s;
	static int answer1, answer2;

	static List<List<Edge>> graph = new ArrayList<>();
	static int[] D;
	static boolean[] visit;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 컴퓨터의 개수
			n = Integer.parseInt(st.nextToken());
			// 의존성 개수
			d = Integer.parseInt(st.nextToken());
			// 해킹당한 컴퓨터 번호
			c = Integer.parseInt(st.nextToken());

			answer1 = 0;
			answer2 = 0;

			visit = new boolean[n + 1];
			D = new int[n + 1];

			// 인접리스트 만들기~
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
				D[i] = INF;
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());

				// a가 b를 의존
				// b가 감되면 s초뒤 전염
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				graph.get(b).add(new Edge(a, s));
			}
			dijkstra();

			for (int i = 0; i <= n; i++) {
				if (visit[i])
					answer1++;

				if (visit[i] && D[i] != Integer.MAX_VALUE)
					answer2 = Math.max(answer2, D[i]);
			}
			System.out.println(answer1 + " " + answer2);
			graph.clear();
			pq.clear();

		}

	}

	private static void dijkstra() {

		// c에서 출발
		D[c] = 0;

		pq.add(new Edge(c, 0));

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			// 방문 체크
			if (visit[edge.v])
				continue;

			visit[edge.v] = true;

			for (Edge next : graph.get(edge.v)) {
				if (!visit[next.v] && D[edge.v] + next.cost < D[next.v]) {
					D[next.v] = D[edge.v] + next.cost;
					pq.offer(new Edge(next.v, D[next.v]));
				}
			}
		}

	}

	static class Edge {
		int v;
		int cost;

		public Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
}
