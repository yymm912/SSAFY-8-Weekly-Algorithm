import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178_미로찾기 {
	static int N, M;

	static int[][] map;
	static boolean[][] visited;
	static int answer;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs(0, 0);
		System.out.println(answer+1);
	}

	static void bfs(int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { y, x });
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				int top[] = q.poll();

				if (top[0] == N - 1 && top[1] == M - 1)
					return;
				for (int d = 0; d < 4; d++) {
					int ny = top[0] + dy[d];
					int nx = top[1] + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx] || map[ny][nx] == 0)
						continue;

					q.offer(new int[] { ny, nx });
					visited[ny][nx] = true;
				}
			}
			answer++;

		}

	}

}
