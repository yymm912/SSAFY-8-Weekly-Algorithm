package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BJ24479_깊이우선탐색1 {

	static int N, M, R;
	static int u, v;
//	static int[][] graph;
	static boolean[] visited;
	static int[] result;
	static int cnt;
	static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
			graph[v].add(u);
		}
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph[i], Comparator.reverseOrder());
		}

		dfs(R);
		for (int i = 1; i <= N; i++)
			System.out.println(result[i]);

	}

	static void dfs(int x) {
		if (visited[x])
			return;
		visited[x] = true;
		result[x] = ++cnt;
		for (int i = 0; i < graph[x].size(); i++) {
			int next = graph[x].get(i);
			if (visited[next])
				continue;
			dfs(next);

		}

	}
}
