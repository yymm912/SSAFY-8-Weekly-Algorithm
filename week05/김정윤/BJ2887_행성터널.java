package week5.김정윤;

import java.io.*;
import java.util.*;

public class BJ2887_행성터널 {
	static int N;
	static int[] parent;
	static ArrayList<Edges> edge = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		Points[] point = new Points[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			point[i] = new Points(i, x, y, z);
		}
		
		// x 정렬
		Arrays.sort(point, (o1, o2) -> o1.x - o2.x);
		for (int i = 0; i < N-1; i++) {
			int weight = Math.abs(point[i].x - point[i+1].x);
			edge.add(new Edges(point[i].node, point[i+1].node, weight));
		}
		// y 정렬
		Arrays.sort(point, (o1, o2) -> o1.y - o2.y);
		for (int i = 0; i < N-1; i++) {
			int weight = Math.abs(point[i].y - point[i+1].y);
			edge.add(new Edges(point[i].node, point[i+1].node, weight));
		}
		// z 정렬
		Arrays.sort(point, (o1, o2) -> o1.z - o2.z);
		for (int i = 0; i < N-1; i++) {
			int weight = Math.abs(point[i].z - point[i+1].z);
			edge.add(new Edges(point[i].node, point[i+1].node, weight));
		}
		
		makeSet();
		
		Collections.sort(edge, (o1, o2) -> o1.weight - o2.weight);

		int result = 0, cnt = 0;
		for (int i = 0; i < edge.size(); i++) {
			Edges e = edge.get(i);

			// 사이클이 발생하는 간선은 제외.
			if (union(e.start, e.end)) { // start와 end를 union => 두개가 같으면 cycle
				result += e.weight; // 가중치 추가
				if (cnt++ == N-1) break;
			}
		}
		System.out.println(result);

	}
	static void makeSet() {
		for (int i = 0; i < N; i++) {
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
		if (px == py) return false; // 이미 같은 소속
		parent[py] = x; // py로 통합
		return true;
	}
	static class Points {
		int node, x, y, z;

		public Points(int node, int x, int y, int z) {
			this.node = node;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Points [node=" + node + ", x=" + x + ", y=" + y + ", z=" + z + "]";
		}
		
		
	}
	static class Edges {
		int start, end, weight;

		public Edges(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edges [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
		
		
	}

}
