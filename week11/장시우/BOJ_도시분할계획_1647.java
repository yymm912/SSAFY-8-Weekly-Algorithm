package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_도시분할계획_1647 {
	
	static int N, M;
	static Edge[] edges;
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new Edge[M];
		parent = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(v, w, c);
		}
		
		// kruskal
		Arrays.sort(edges, (e1, e2) -> e1.c - e2.c);
		
		makeSet();
		
		int cnt = 0;
		int sum = 0;
		
		for (int i = 0; i < M; i++) {
			Edge edge = edges[i];
			
			if (union(edge.v, edge.w)) {
				cnt++;
				sum += edge.c;
			}
			
			if (cnt == N - 2) break;
		}
		
		System.out.println(sum);
	}
	
	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if (parent[x] == x) return x;
		
		return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if (px == py) return false;
		
		if (py > px) parent[py] = px;
		else parent[px] = py;
		
		return true;
	}
	
	static class Edge {
		int v;
		int w;
		int c;
		
		public Edge(int v, int w, int c) {
			this.v = v;
			this.w = w;
			this.c = c;
		}
	}
}
