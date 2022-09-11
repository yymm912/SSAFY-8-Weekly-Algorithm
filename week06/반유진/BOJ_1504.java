// BOJ 1504번 특정한 최단 경로 

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_1504 {

	static int N, E, v1, v2, min;
	static ArrayList<ArrayList<Edge>> vertex = new ArrayList<>();
	static PriorityQueue<Edge> pqueue;
	static boolean[] visit;
	static int[] cost;
	static int INF = 200000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		cost = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<Edge>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			vertex.get(a).add(new Edge(b, c));
			vertex.get(b).add(new Edge(a, c));
		}

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		int mustDis = dijkstra(v1, v2);

		int result1 = dijkstra(1, v1) + dijkstra(v2, N);
		int result2 = dijkstra(1, v2) + dijkstra(v1, N);

		min = Math.min(result1, result2);
		min += mustDis;

		if (min >= INF)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	static int dijkstra(int start, int end) {
		pqueue = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
		visit = new boolean[N + 1];
		Arrays.fill(cost, INF);

		cost[start] = 0;
		pqueue.add(new Edge(start, 0));

		while (!pqueue.isEmpty()) {
			Edge e = pqueue.poll();

			if (e.c > cost[e.v])
				continue;

			if (visit[e.v])
				continue;

			visit[e.v] = true;

			for (Edge ne : vertex.get(e.v)) {
				if (!visit[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pqueue.offer(new Edge(ne.v, cost[ne.v]));
				}
			}
		}

		return cost[end];
	}

	static class Edge {
		int v;
		int c;

		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}

}
