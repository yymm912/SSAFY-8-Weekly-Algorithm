
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1753_최단경로 {
	static int V, E, K;
	static List<List<Edge>> vertex = new ArrayList<>();
	static boolean[] visit;
	static int[] cost;// 핵신
	static PriorityQueue<Edge> pqueue = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());

		visit = new boolean[V + 1];
		cost = new int[V + 1];

		for (int i = 0; i <= V; i++) {
			vertex.add(new ArrayList<Edge>());
			cost[i] = INF;
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			vertex.get(u).add(new Edge(v, w));
		}

		dijkstra();

		for (int i = 1; i <= V; i++) {
			System.out.println(cost[i] == INF ? "INF" : cost[i]);
		}
	}

	private static void dijkstra() {
		// 시작 정점 K
		cost[K] = 0;
		pqueue.add(new Edge(K, 0));

		while (!pqueue.isEmpty()) {
			// 꺼내면 비용이 가장 작은 것
			Edge e = pqueue.poll();

			// visit check
			if (visit[e.v])
				continue;

			// e.v정점으로부터 갈 수 있는 다른 정점을 고려
			// 고려하는 목적은 cost[] 갱신하기 위해
			visit[e.v] = true;

			for (Edge ne : vertex.get(e.v)) {
				if (!visit[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pqueue.offer(new Edge(ne.v, cost[ne.v]));
				}
			}

		}

	}

	static class Edge {
		int v; // 정점
		int c; // 비용

		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}

	}

}
