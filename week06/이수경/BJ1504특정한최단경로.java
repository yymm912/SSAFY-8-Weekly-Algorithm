package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1504특정한최단경로 {
	static int N, E;
	static int a, b, c;
	static int v1, v2;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.c - b.c);
	static List<Edge> vertex[];
	static boolean visit[];
	static int cost[];
	static int ans;
	static int INF = 200000000;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 초기화
		cost = new int[N + 1];
		visit = new boolean[N + 1];
		vertex = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			vertex[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			vertex[a].add(new Edge(b, c)); // 도착정점, 가중치
			vertex[b].add(new Edge(a, c)); // 도착정점, 가중치
		}

		// 꼭 거쳐야 하는 정점 번호
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		int ans1 = 0;
		ans1 += dijkstra(1, v1);
		ans1 += dijkstra(v1, v2);
		ans1 += dijkstra(v2, N);

		int ans2 = 0;		
		ans2 += dijkstra(1, v2);
		ans2 += dijkstra(v2, v1);
		ans2 += dijkstra(v1, N);

		if (ans1 >= INF && ans2 >= INF) ans = -1;
		else ans = Math.min(ans1, ans2);
		System.out.println(ans);
	}

	static int dijkstra(int start, int goal) {
		Arrays.fill(visit, false);
		Arrays.fill(cost, INF);

		// 시작 정점 start
		cost[start] = 0;
		pq.offer(new Edge(start, 0)); // 1부터 시작

		while (!pq.isEmpty()) {
			// 꺼내면 비용이 가장 작은 것
			Edge e = pq.poll();

//			if (e.c > cost[e.v]) continue; // 가지치기. 이미 e.c가 더 크면 아래로 내려갈 의미가 없음!

			// visit check
			if (visit[e.v])continue;

			// e.v 정점으로부터 갈 수 있는 다른 정점을 고려
			// ** 고려하는 목적은 cost[] 갱신하기 위해 **
			visit[e.v] = true;

			for (Edge ne : vertex[e.v]) {

				if (!visit[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {

					cost[ne.v] = cost[e.v] + ne.c;
					
					pq.offer(new Edge(ne.v, cost[ne.v]));

				}
			}

		}

		return cost[goal];

	}

	static class Edge {
		int v; // 도착 정점
		int c; // 가중치

		public Edge(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}

	}

}


/* 

4 5
1 2 3
1 3 1
1 4 1
2 3 3
3 4 4
2 3

*/