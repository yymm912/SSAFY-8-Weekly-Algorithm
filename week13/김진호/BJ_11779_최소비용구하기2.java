package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_11779_최소비용구하기2 {
	static int N, M;
	static List<List<Edge>> graph = new ArrayList<>();
	static int[] distance;
	static boolean[] visited;
	static int start, end;
	static int path[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		distance = new int[N + 1];
		visited = new boolean[N + 1];
		path = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			distance[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Edge(b, c));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra(start);
		System.out.println(distance[end]);

		Stack<Integer> stack = new Stack<>();
		stack.add(end);
		while (true) {
			int top = stack.peek();

			if (path[top] == 0) {
				break;
			}

			stack.add(path[top]);
		}
		
		System.out.println(stack.size());
		while(!stack.isEmpty())
			System.out.print(stack.pop()+" ");
	}

	static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);

		pq.offer(new Edge(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (visited[cur.v])
				continue;

			visited[cur.v] = true;
			for (Edge next : graph.get(cur.v)) {
				if (!visited[next.v] && distance[cur.v] + next.cost <= distance[next.v]) {
					distance[next.v] = distance[cur.v] + next.cost;
					pq.offer(new Edge(next.v, distance[next.v]));
					path[next.v] = cur.v;
				}
			}
		}
	}

	static class Edge {
		int v;
		int cost;

		public Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

}
