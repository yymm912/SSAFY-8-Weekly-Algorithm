package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_해킹_10282 {
	
	static int T, n, d, c, a, b, s, cnt, time;
	static List<List<Edge>> vertex;
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> (e1.c - e2.c));
	static int[] cost;
	static boolean[] checked;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			cost = new int[n + 1];
			checked = new boolean[n + 1];
			
			vertex = new ArrayList<>();
			
			for (int i = 0; i <= n; i++) {
				vertex.add(new ArrayList<>());
			}
						
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());
				
				vertex.get(b).add(new Edge(a, s));
			}
			
			dijkstra(c);
			
			System.out.println(cnt + " " + time);
		}
	}
	
	static void dijkstra(int c) {
		reset();
		
		cost[c] = 0;
		pq.add(new Edge(c, 0));
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (e.c > cost[e.v] || checked[e.v]) continue;
			
			checked[e.v] = true;
			cnt++;
			
			for (Edge ne : vertex.get(e.v)) {
				if (!checked[ne.v] && cost[e.v] + ne.c < cost[ne.v]) {
					cost[ne.v] = cost[e.v] + ne.c;
					pq.add(new Edge(ne.v, cost[ne.v]));
				}
			}
		}
		
		for (int i = 0; i < cost.length; i++) {
			if (cost[i] != Integer.MAX_VALUE) {
				time = Math.max(time, cost[i]);
			}
		}
	}
	
	static void reset() {
		cnt = 0;
		time = 0;
		
		pq.clear();
		Arrays.fill(cost, Integer.MAX_VALUE);
		Arrays.fill(checked, false);
	}
	
	static class Edge{
		int v;
		int c;
		
		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
