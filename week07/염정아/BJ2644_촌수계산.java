package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 주의
 * - 두사람의 친척관계가 전혀없을 때는 -1 출력 
 * */


// N: 사람 수
// A, B: 구해야하는 
public class BJ2644_촌수계산 {
	static int ans, N, A, B, M;
	static int[][] matrix = new int[101][101];

	static Queue<Integer> q = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			matrix[from][to] = 1;
			matrix[to][from] = 1;
		}

		// 초기화
		ans = -1;

		// 탐색
		bfs(A);

		// 출력
		System.out.println(ans);

	} // end main


	private static void bfs(int s) {
		q.offer(s);
		matrix[s][0] = 1;

		int dep = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				// System.out.println(cur);

				if (cur == B) {
					ans = dep;
					return;
				}

				for (int j = 1; j <= 100; j++) {
					if (matrix[j][0] == 1) continue;
					if (matrix[cur][j] == 0) continue;

					q.offer(j);
					matrix[j][0] = 1;
				}
			}

			dep++;
		}
	} // end bfs

}
