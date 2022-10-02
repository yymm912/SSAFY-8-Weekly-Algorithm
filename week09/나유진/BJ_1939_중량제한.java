package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1939_중량제한 {

	static int N, M;
	static ArrayList<ArrayList<Edge>> edge = new ArrayList<>();
	static boolean[] visited;
	static int[] weight;
	static int min, max;
	static int start, end;
	static int ans;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) { // 0 dummy
			edge.add(new ArrayList<Edge>());
		}

		weight = new int[M];

		/* 초기화 끝 */

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			edge.get(a).add(new Edge(b, c));
			edge.get(b).add(new Edge(a, c));

			weight[i] = c;

		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		Arrays.sort(weight);
		min = 0;
		max = M - 1;
		
		/* 입력 끝 */

		binarysearch();

		System.out.println(ans);

	}

	static void binarysearch() {
		while (min <= max ) {
			int mid = (min + max) / 2;
			visited = new boolean[N+1]; // 0 dummy

			if (bfs(mid)) {
				min = mid + 1;
				ans = weight[mid];
			} else {
				max = mid - 1;
			}
		}
	}

	static boolean bfs(int mid) {
		Queue<Integer> queue = new ArrayDeque<>();

		visited[start] = true;
		queue.offer(start);
		/* Queue 초기화 */

		while (!queue.isEmpty()) {
			int temp = queue.poll();

			if (temp == end)
				return true;

			for (int i = 0; i < edge.get(temp).size(); i++) {
				Edge e = edge.get(temp).get(i);
				if (e.w >= weight[mid] && visited[e.v] == false) {
					visited[e.v] = true;
					queue.offer(e.v);
				}
			}
		}

		return false;

	}

	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}

	}
}
