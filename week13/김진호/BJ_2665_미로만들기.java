import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ_2665_미로만들기 {
	static int N;
	static int[][] map;

	static int dy[] = { 1, -1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static int[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		visited = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= N; j++) {
				map[i][j] = str.charAt(j - 1) - '0';
				visited[i][j] = Integer.MAX_VALUE;
			}
		}

		bfs(1, 1);
		System.out.println(visited[N][N]);

	}

	static void bfs(int y, int x) {
		Queue<Node> q = new ArrayDeque<>();

		visited[y][x] = 0;
		q.offer(new Node(y, x));

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Node cur = q.poll();

				for (int d = 0; d < 4; d++) {
					int ny = cur.y + dy[d];
					int nx = cur.x + dx[d];

					if (ny > N || nx > N || ny < 1 || nx < 1) {
						continue;
					}

					// visited배열에 검은방을 지나는 최소 횟수를 누적
					if (visited[ny][nx] <= visited[cur.y][cur.x])
						continue;
					
					if (map[ny][nx] == 1)
						visited[ny][nx] = visited[cur.y][cur.x];
					else
						visited[ny][nx] = visited[cur.y][cur.x] + 1;

					q.offer(new Node(ny, nx));

				}
			}
		}
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

}
