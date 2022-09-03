package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_18352_특정거리의도시찾기 {
	static int N, M, K, X;
	static boolean[] visit;// 0 dummy
	static int[] distance;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		visit = new boolean[N + 1];
		distance = new int[N + 1];

		for (int i = 0; i <= N; i++) { // 0 dummy
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list.get(A).add(B);
		}

		bfs();

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			if (distance[i] == K)
				sb.append(i).append('\n');
		}

		if (sb.length() == 0) {
			sb.append(-1);
			System.out.println(sb);
		} else {
			System.out.println(sb);
		}

	}

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(X);
		visit[X] = true;
		distance[X] = 0;

		while (!queue.isEmpty()) {
			int k = queue.poll();

			for (int j = 0; j < list.get(k).size(); j++) {
				if (visit[list.get(k).get(j)])
					continue; // 방문한적 있다면 continue;

				visit[list.get(k).get(j)] = true;
				queue.add(list.get(k).get(j));
				distance[list.get(k).get(j)] = distance[k] + 1;

			}

		}
	}
}
