package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_5972_택배배송 {
	static int N, M;
	static List<List<Edge>> graph = new ArrayList<>();
	static int D[];
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		D = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			D[i] = Integer.MAX_VALUE;
		}

		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Edge(b, c));
			graph.get(b).add(new Edge(a, c));
		}

//		for (int i = 0; i <= N; i++) {
//			System.out.println(graph.get(i).toString());
//		}
		dijkstra();
		System.out.println(D[N]);

	}

	static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

		// 출발지
		D[1] = 0;
		pq.offer(new Edge(1, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (visited[cur.v])
				continue;

			visited[cur.v] = true;
			for (Edge next : graph.get(cur.v)) {
				if (!visited[next.v] && D[cur.v] + next.cost < D[next.v]) {
					D[next.v] = D[cur.v] + next.cost;
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

		@Override
		public String toString() {
			return "Edge [v=" + v + ", cost=" + cost + "]";
		}

	}
}
