package forStudy.week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2887_행성터널 {

	static int N, res;
	static int[] parents;
	static Planet[] planets;
	static PriorityQueue<Edge> pqu;
	
	static class Planet {
		int x,y,z;
		int p;
		public Planet(int x, int y, int z, int p) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.p = p;
		}
		@Override
		public String toString() {
			return "Planet [x=" + x + ", y=" + y + ", z=" + z + ", p=" + p + "]";
		}
		
	}
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		planets = new Planet[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			planets[i] = new Planet(x, y, z, i);
		}
		
		/* 서로소 집합 생성*/
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		/* 인접리스트 생성 */
		// x, y, z 좌표를 각각 정렬해 N-1개의 최소비용 간선을 구함
		int dist = 0;
		pqu = new PriorityQueue<>();
		
		Arrays.sort(planets, (p1, p2) -> p1.x - p2.x);
		for (int i = 0; i < N-1; i++) {
			dist = Math.abs(planets[i].x - planets[i+1].x);
			pqu.add(new Edge(planets[i].p, planets[i+1].p, dist));
		}
		
		Arrays.sort(planets, (p1, p2) -> p1.y - p2.y);
		for (int i = 0; i < N-1; i++) {
			dist = Math.abs(planets[i].y - planets[i+1].y);
			pqu.add(new Edge(planets[i].p, planets[i+1].p, dist));
		}

		Arrays.sort(planets, (p1, p2) -> p1.z - p2.z);
		for (int i = 0; i < N-1; i++) {
			dist = Math.abs(planets[i].z - planets[i+1].z);
			pqu.add(new Edge(planets[i].p, planets[i+1].p, dist));
		}
		
		/* 행성 연결 */
		res = 0;
		int cnt = 0;
		
		while(!pqu.isEmpty()) {
			Edge e = pqu.poll();
			
			if(findSet(e.from) != findSet(e.to)) {
				res += e.weight;
				union(e.from, e.to);
			}
		}
		
		System.out.println(res);
	}
	
	static int findSet(int n) {
		if(parents[n] == n) return n;
		return parents[n] = findSet(parents[n]);
	}
	
	static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		
		if(x == y) return false;
		
		if(x < y) parents[y] = x;
		else parents[x] = y;
		
		return true;
	}
}
