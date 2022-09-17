// BOJ 5972번 택배 배송

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_5972 {

	static int N, M;
	static ArrayList<ArrayList<Edge>> vertex = new ArrayList<>();
	static PriorityQueue<Edge> pqueue = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	static boolean[] visit;
	static int[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cost = new int[N + 1];
		visit = new boolean[N + 1];

		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<Edge>());
			cost[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			vertex.get(a).add(new Edge(b, c));
			vertex.get(b).add(new Edge(a, c));
		}

		dijkstra();

		System.out.println(cost[N]);
	}

	static void dijkstra() {
		cost[1] = 0;
		pqueue.add(new Edge(1, 0));

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
