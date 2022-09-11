package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_특정한최단경로_1504 {
	
	static int N, E, ans, w1, w2;
	static List<List<Edge>> vertex = new ArrayList<>();
	static int[] cost;
	static boolean[] checked;
	static final int INF = Integer.MAX_VALUE;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		cost = new int[N + 1];
		checked = new boolean[N + 1];
		
		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<>());
			cost[i] = INF;
		}
		
		// Edge
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			vertex.get(v1).add(new Edge(v2, c));
			vertex.get(v2).add(new Edge(v1, c));
		}
		
		st = new StringTokenizer(br.readLine());
		w1 = Integer.parseInt(st.nextToken());
		w2 = Integer.parseInt(st.nextToken());
		
		// 1부터 w1과 1부터 w2까지의 최솟값을 구한다.
		dijkstra(1);
		int fromOneToW1 = cost[w1];
		int fromOneToW2 = cost[w2];
		
		// dijkstra 알고리즘을 사용하기 위한 자료구조들을 초기화 한다.
		reset();
		
		// w1부터 w2까지의 최솟값을 구한다.
		dijkstra(w1);
		int fromW1ToW2 = cost[w2];
		
		// w1부터 N까지의 최솟값을 구한다.
		int fromW1ToN = cost[N];
		
		reset();
		
		// w2부터 w1까지의 최솟값을 구한다.
		dijkstra(w2);
		int fromW2ToW1 = cost[w1];
				
		// w1부터 N까지의 최솟값을 구한다.
		int fromW2ToN = cost[N];
		
		// 1 -> w1 -> w2 -> N과 1 -> w2 -> w1 -> N중 더 작은 값을 답으로 한다.
		int a1 = 0;
		if (fromOneToW1 == INF || fromW1ToW2 == INF || fromW2ToN == INF) {
			a1 = INF;
		} else {
			a1 = fromOneToW1 + fromW1ToW2 + fromW2ToN;
		}
		
		int a2 = 0;
		if (fromOneToW2 == INF || fromW2ToW1 == INF || fromW1ToN == INF) {
			a2 = INF;
		} else {
			a2 = fromOneToW2 + fromW2ToW1 + fromW1ToN;
		}
		
		ans = Math.min(a1, a2);
		
		if (ans == INF) {
			ans = -1;
		}
		
		System.out.println(ans);
	}
	
	static void reset() {
		Arrays.fill(cost, INF);
		Arrays.fill(checked, false);
	}
	
	static void dijkstra(int start) {
		cost[start] = 0;
		pq.add(new Edge(start, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (e.c > cost[e.v] || checked[e.v]) continue;
			
			checked[e.v] = true;
			
			for (Edge ne : vertex.get(e.v)) {
				if (!checked[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.add(new Edge(ne.v, cost[ne.v]));
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
