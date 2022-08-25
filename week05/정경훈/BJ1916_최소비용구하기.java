package baekjoon.최소비용구하기_1916;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, S, E;
	static Vertex[] vertex;
	static int[] dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1];
		visit = new boolean[N+1];
		vertex = new Vertex[N+1];
		
		for (int i = 0; i <= N; i++) {
			vertex[i] = new Vertex(i);
			dist[i] = Integer.MAX_VALUE;
		}
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			vertex[from].add(new Edge(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		dijkstra(S);
		
		System.out.println(dist[E]);
	}

	static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.w - e2.w); // 가중치가 낮은 순으로 정렬
		pq.add(new Edge(start,0));
		dist[start] = 0;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(visit[edge.v]) continue;
			visit[edge.v] = true;
			if(edge.v == E) return;
			for (int i = 0; i < vertex[edge.v].list.size(); i++) {
				Edge e = vertex[edge.v].list.get(i);
				if(!visit[e.v] && dist[e.v] > dist[edge.v]+e.w) {
					dist[e.v] = dist[edge.v] + e.w;
					pq.add(new Edge(e.v, dist[e.v])); // 큐에 넣을 때 거리가 짧은 순으로 먼저 접근 해야함
				}
			}	
		}	
	}
	
	static class Vertex{
		List<Edge> list = new ArrayList<>();
		int V;
		
		public Vertex(int V) {
			this.V = V;
		}
		
		public void add(Edge edge) {
			list.add(edge);
		}
	}
	
	static class Edge{
		int v;
		int w;
		
		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
