package algorithm_assignments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_2887_행성터널 {
	static int N, ans;
	static PriorityQueue<Edge> pq;
	static ArrayList<Planet> planet;
	static boolean[] visit;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		planet = new ArrayList<>();
		pq = new PriorityQueue<>((p1, p2) -> p1.w - p2.w);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			planet.add(new Planet(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), i));
		}

		// planet 입력 끝

		Collections.sort(planet, (p1, p2) -> p1.x - p2.x);

		for (int i = 0; i < N - 1; i++) {
			pq.offer(new Edge(planet.get(i).idx, planet.get(i + 1).idx,
					Math.abs(planet.get(i).x - planet.get(i + 1).x)));
		}

		Collections.sort(planet, (p1, p2) -> p1.y - p2.y);
		for (int i = 0; i < N - 1; i++) {
			pq.offer(new Edge(planet.get(i).idx, planet.get(i + 1).idx,
					Math.abs(planet.get(i).y - planet.get(i + 1).y)));
		}

		Collections.sort(planet, (p1, p2) -> p1.z - p2.z);
		for (int i = 0; i < N - 1; i++) {
			pq.offer(new Edge(planet.get(i).idx, planet.get(i + 1).idx,
					Math.abs(planet.get(i).z - planet.get(i + 1).z)));
		}

		// 각각 x, y, z별로 sort 후 그 차이별 edge 저장

		parent = new int[N];
		makeSet();

		// kurskal 위해 union find 전 초기화

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int p1 = FindSet(edge.v1);
			int p2 = FindSet(edge.v2);

			if (p1 != p2) {
				Union(p1, p2);
				ans += edge.w;
			}
		}

		System.out.println(ans);

	}

	static void makeSet() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	static int FindSet(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = FindSet(parent[x]);
	}

	static void Union(int a, int b) {
		int ap = parent[a];
		int bp = parent[b];

		if (a > b)
			parent[ap] = bp;
		else
			parent[bp] = ap;
	}

	static class Edge {
		int v1, v2, w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}

	}

	static class Planet {
		int x, y, z, idx;

		public Planet(int x, int y, int z, int idx) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.idx = idx;
		}
	}

	static int make_tunnel(Planet p1, Planet p2) {
		int result = Math.min(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		result = Math.min(result, Math.abs(p1.z - p2.z));

		return result;
	}
}
