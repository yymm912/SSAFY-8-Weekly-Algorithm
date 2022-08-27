// BOJ 2887번 행성 터널
// 이코테 - 행성 터널

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_2887 {

	static int N, sum;
	static int[] parent;
	static PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2) -> e1.c - e2.c);
	static List<Node> input = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			input.add(new Node(i, x, y, z));
		}

		List<Node> xList = new ArrayList<>(input);
		xList.sort((o1, o2) -> o1.x - o2.x);
		List<Node> yList = new ArrayList<>(input);
		yList.sort((o1, o2) -> o1.y - o2.y);
		List<Node> zList = new ArrayList<>(input);
		zList.sort((o1, o2) -> o1.z - o2.z);

		Node node1, node2;
		for (int i = 0; i < N - 1; i++) {
			node1 = xList.get(i);
			node2 = xList.get(i + 1);
			int dis = Math.min(Math.min(Math.abs(node1.x - node2.x), Math.abs(node1.y - node2.y)),
					Math.abs(node1.z - node2.z));
			edges.add(new Edge(node1.star, node2.star, dis));

			node1 = yList.get(i);
			node2 = yList.get(i + 1);
			dis = Math.min(Math.min(Math.abs(node1.x - node2.x), Math.abs(node1.y - node2.y)),
					Math.abs(node1.z - node2.z));
			edges.add(new Edge(node1.star, node2.star, dis));

			node1 = zList.get(i);
			node2 = zList.get(i + 1);
			dis = Math.min(Math.min(Math.abs(node1.x - node2.x), Math.abs(node1.y - node2.y)),
					Math.abs(node1.z - node2.z));
			edges.add(new Edge(node1.star, node2.star, dis));
		}

		makeSet();

		int cnt = 0;
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();

			if (union(edge.v1, edge.v2)) {
				cnt++;
				sum += edge.c;
			}
			if (cnt == N - 1) {
				break;
			}
		}

		System.out.println(sum);
	}

	static void makeSet() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if (parent[x] == x)
			return x;

		return parent[x] = findSet(parent[x]);
	}

	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px == py)
			return false;

		if (px < py)
			parent[py] = px;
		else
			parent[px] = py;

		return true;
	}

	static class Node {
		int star;
		int x;
		int y;
		int z;

		public Node(int star, int x, int y, int z) {
			this.star = star;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge {
		int v1;
		int v2;
		int c;

		public Edge(int v1, int v2, int c) {
			this.v1 = v1;
			this.v2 = v2;
			this.c = c;
		}
	}
}
