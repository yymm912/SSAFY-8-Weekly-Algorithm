package algorithm_assignments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_1697_숨바꼭질 {
	static int N, K, ans;
	static boolean[] visit = new boolean[100001]; // 0 dummy
	static Queue<Integer> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		search();

		System.out.println(ans);
	}

	static void search() {
		queue.offer(N);
		ans = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int num = queue.poll();
				if (num == K)
					return;
				if (num - 1 >= 0 && !visit[num - 1]) {
					visit[num - 1] = true;
					queue.add(num - 1);
				}

				if (num + 1 <= 100000 && !visit[num + 1]) {
					visit[num + 1] = true;
					queue.add(num + 1);
				}
				if (num * 2 <= 100000 && !visit[num * 2]) {
					visit[num * 2] = true;
					queue.add(num * 2);
				}
			}
			ans++;
		}

	}
}
