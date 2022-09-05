package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import bj.BJ1504특정한최단경로.Edge;

// dijkstra
public class BJ5972택배배송 {

	static int N, M;
	static List<Edge> vertex[];
	static PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> a.c - b.c);  
	static int cost[];
	static boolean visit[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visit = new boolean[N+1];
		cost = new int[N+1];
		vertex = new ArrayList[N+1];
		for (int i = 1; i < N+1; i++) {
			vertex[i] = new ArrayList<>();
			cost[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < M; i++) {
			// 양방향 그래프
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			vertex[u].add(new Edge(v, c));
			vertex[v].add(new Edge(u, c));
		}
		// 입력 완
		
		// 시작은 항상 1부터
		cost[1] = 0;
		visit[1] = true;
		pq.offer(new Edge(1, cost[1])); 
		
		while(!pq.isEmpty()) {
			
			Edge e = pq.poll();
			
			visit[e.v] = true;
			
			for (Edge ne : vertex[e.v] ) {
				if( !visit[ne.v] &&  cost[e.v] + ne.c  < cost[ne.v]  ) {
					cost[ne.v] = cost[e.v] + ne.c ;
					pq.offer(new Edge(ne.v, cost[ne.v]));
				}
			}
			
			
			
		}
		
		System.out.println(cost[N]);
		
		
		
	}
	
	static class Edge{
		int v;
		int c;
		public Edge(int v, int c) {
			super();
			this.v = v;
			this.c = c;
		}
	}

}
