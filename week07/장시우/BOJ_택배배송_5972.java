package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_택배배송_5972 {
	
	static int N, M, v1, v2, c, ans;
	static List<List<Edge>> vertex = new ArrayList<>();
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	static int[] cost;
	static boolean[] checked;
	
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cost = new int[N + 1];
		checked = new boolean[N + 1];
		
		// 비용 배열을 초기화해준다.
		for (int i = 0; i <= N; i++) {
			cost[i] = INF;
		}
		
		for (int i = 0; i <= N; i++) {
			vertex.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			// 일단은 무방향 그래프라고 가정한다.
			vertex.get(v1).add(new Edge(v2, c));
			vertex.get(v2).add(new Edge(v1, c));
		}
		
		// dijkstra
		ans = dijkstra();
		
		System.out.println(ans);
	}
	
	static int dijkstra() {
		cost[1] = 0;
		pq.add(new Edge(1, 0));
		
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
		
		return cost[N];
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
