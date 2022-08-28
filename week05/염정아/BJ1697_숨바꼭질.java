package first.m08.month.practice;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


// N: 수빈이 위치 
// M: 동생 위치 
// ans: 몇 초만에 찾았는지 
public class BJ1697_숨바꼭질 {
	static int N, M, ans;
	static final int MAX = 100000;
	static boolean[] visit = new boolean[MAX + 1];

	static Queue<Integer> q = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		q.offer(N);

		bfs();

		System.out.println(ans);

	} // end main


	private static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int n = q.poll();

				if (n == M) return;

				if (n > 0 && !visit[n - 1]) {
					q.offer(n - 1);
					visit[n - 1] = true;
				}

				if (n < MAX && !visit[n + 1]) {
					q.offer(n + 1);
					visit[n + 1] = true;
				}

				if (n * 2 <= MAX && !visit[n * 2]) {
					q.offer(n * 2);
					visit[n * 2] = true;
				}

			}

			ans++;

		}

	} // end bfs
}
