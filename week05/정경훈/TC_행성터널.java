package baekjoon.행성터널_2887;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long ans;
	static Node[] planet;
	static PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2)-> e1.w - e2.w);
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ans = 0;
		planet = new Node[N];
		
		// 좌표값 저장
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			planet[i] = new Node(i, x, y, z);
		}
		
		parent = new int[N];
			
		// 각 노드별 연결시 드는 비용 계산 -- 아마 여기가 문젠데... 이러면 최대 100000 * 99999 / 2 개
//		for (int i = 0; i < N; i++) {
//			for (int j = i+1; j < N; j++) {
//				int Xdiff = Math.abs(planet[i].x - planet[j].x);
//				int Ydiff = Math.abs(planet[i].y - planet[j].y);
//				int Zdiff = Math.abs(planet[i].z - planet[j].z);
//				edges.add(new Edge(i,j, Math.min(Xdiff, Math.min(Ydiff, Zdiff)))); // 3개중 제일 작은게 가중치
//			}
//		}
		
		// 이러면 N-1개 * 3 최대 99999 * 3
		Arrays.sort(planet, (n1, n2) -> n1.x - n2.x);
		for (int i = 0; i < N-1; i++) {
			int diff = Math.abs(planet[i].x - planet[i+1].x);
			edges.add(new Edge(planet[i].v, planet[i+1].v, diff));
		}
		
		Arrays.sort(planet, (n1, n2) -> n1.y - n2.y);
		for (int i = 0; i < N-1; i++) {
			int diff = Math.abs(planet[i].y - planet[i+1].y);
			edges.add(new Edge(planet[i].v, planet[i+1].v, diff));
		}
		
		Arrays.sort(planet, (n1, n2) -> n1.z - n2.z);
		for (int i = 0; i < N-1; i++) {
			int diff = Math.abs(planet[i].z - planet[i+1].z);
			edges.add(new Edge(planet[i].v, planet[i+1].v, diff));
		}
		
		//크루스칼이 편할지도..
		makeSet();
	
		kruskal();
		
		System.out.println(ans);
	}
	
	static void kruskal() {
		int cnt = 0; // 선의 수가 N-1이면 종료
		
		while(!edges.isEmpty()) {
			Edge edge = edges.poll();
			if(union(edge.v1, edge.v2)) {
				cnt++;
				ans += edge.w;
			}
			if(cnt == N-1) break;
		}
	}
	
	static void makeSet() {
		for (int i = 1; i < N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if(px == py) return false;
		
		if(px < py) parent[py] = px;
		else parent[px] = py;
		
		return true;
	}
	
	static class Edge{
		int v1;
		int v2;
		int w;
		
		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}
	
	static class Node{
		int v, x, y, z;
		public Node(int v, int x, int y, int z) {
			this.v = v;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

}
