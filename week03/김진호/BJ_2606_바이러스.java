package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_2606_바이러스 {
	static int N, M;
	static int answer;
	static int u, v;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			graph[u].add(v);
			graph[v].add(u);
		}

		for (int i = 0; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		dfs(1);
		System.out.println(answer);

	}

	static void dfs(int x) {
		if (visited[x])
			return;

		visited[x] = true;
	

		for (int i = 0; i < graph[x].size(); i++) {
			int next = graph[x].get(i);

			if (visited[next])
				continue;
			answer++;
			dfs(next);
		}

	}

}
