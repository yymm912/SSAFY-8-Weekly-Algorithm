// BOJ 1697번 숨바꼭질

package BOJ.DfsBfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_1697 {

	static int N, K, ans;
	static Queue<Integer> que = new ArrayDeque<>();
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if (N == K) // 처음부터 같은 위치가 입력될 경우
			System.out.println(0);
		else {
			bfs();
			System.out.println(ans);
		}
	}

	static void bfs() {
		que.add(N);
		visited[N] = true;

		while (!que.isEmpty()) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				int x = que.poll();

				if (x + 1 == K || x - 1 == K || x * 2 == K) {
					que.clear();
					break;
				}

				if (x + 1 <= 100000 && !visited[x + 1]) {
					visited[x + 1] = true;
					que.add(x + 1);
				}
				if (x - 1 >= 0 && !visited[x - 1]) {
					visited[x - 1] = true;
					que.add(x - 1);
				}
				if (2 * x >= 0 && 2 * x <= 100000 && !visited[x * 2]) {
					visited[x * 2] = true;
					que.add(x * 2);
				}
			}
			ans++;
		}
	}

}
