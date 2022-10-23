package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class SW1267_작업순서 {
	static int ans, T = 10, V, E;
	static int[][] matrix;

	static Queue<Integer> q = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			matrix = new int[V + 1][V + 1]; // 0 진입차수

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				matrix[from][to] = 1;
				matrix[to][0]++;
			}

			System.out.print("#" + t + " ");
			topologySort();

		}

	} // end main


	private static void topologySort() {
		// 진입 차수가 0인 노드부터 넣기
		for (int i = 1; i <= V; i++) if (matrix[i][0] == 0) q.offer(i);

		// 진입 차수가 0이면 큐에서 꺼내기
		while (!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");

			for (int i = 1; i <= V; i++) {
				if (matrix[cur][i] == 1) {
					matrix[i][0]--;

					if (matrix[i][0] == 0) q.offer(i);
				}

			}

		}

		System.out.println();

	} // end main

}
