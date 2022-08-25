package week5.김정윤;

import java.io.*;
import java.util.*;

// Dijkstra
public class BJ1916_최소비용구하기2 {
	static int N, M, start, end;
	static List<List<Edge>> bus = new ArrayList<>();
	static int[] cost;
	static boolean[] v;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		cost = new int[N+1];
		v = new boolean[N+1];
		
		for (int i = 0; i <= N; i++) {
			bus.add(new ArrayList<>());
			cost[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < 8; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			System.out.println(v1 + ", " + v2 + " : " + c);
			bus.get(v1).add(new Edge(v2, c));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		dijkstra();
		for (int i = 0; i <= N; i++) {
			System.out.println(cost[i]);
		}
		System.out.println(cost[end]);
	}
	static void dijkstra() {
		cost[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (e.c > cost[e.v]) continue;
			if (v[e.v]) continue;
			v[e.v] = true;
			
			for (Edge ne : bus.get(e.v)) {
				if (!v[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.offer(new Edge(ne.v, cost[ne.v]));
				}
			}
			
		}
	}
	static class Edge {
		int v, c;

		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
		
	}

}
