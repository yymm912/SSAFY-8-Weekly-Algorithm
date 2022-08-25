import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_숨바꼭질 {
	static int N, K;
	static int answer;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[100001];

		bfs(N);

	}

	static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {

			int q_size = queue.size();

			for (int i = 0; i < q_size; i++) {
				int cur = queue.poll();
				if (cur == K) {
					System.out.println(answer);
					return;
				}

				if (cur * 2 <= 100000 && !visited[cur * 2]) {
					queue.offer(cur * 2);
					visited[cur * 2] = true;

				}
				if (cur - 1 >= 0 && !visited[cur - 1]) {
					queue.offer(cur - 1);
					visited[cur - 1] = true;

				}
				if (cur + 1 <= 100000 && !visited[cur + 1]) {
					queue.offer(cur + 1);
					visited[cur + 1] = true;

				}

			}
			answer++;

		}
	}

}