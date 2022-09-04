import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_5014_스타트링크 {
	static int F, S, G, U, D;
	static boolean[] visited;
	static int move[];
	static int dx[] = new int[2];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = -Integer.parseInt(st.nextToken());

	
		dx[0] = U;
		dx[1] = D;
		visited = new boolean[F + 1];
		move = new int[F + 1];
		bfs(S, G);
	}

	static void bfs(int S, int G) {
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(S);
		visited[S] = true;

		while (!q.isEmpty()) {
			int top = q.poll();
			if (top == G) {
				System.out.println(move[top]);
				return;
			}

			for (int i = 0; i < 2; i++) {
				int nx = top + dx[i];

				if (nx > F || nx < 1)
					continue;

				if (!visited[nx]) {
					visited[nx] = true;
					q.offer(nx);
					move[nx] = move[top] + 1;
				}

			}
		}

		System.out.println("use the stairs");
	}

}
