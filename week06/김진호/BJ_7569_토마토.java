import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7569_토마토 {
	static int M, N, H;
	static int[][][] map;
	static int[][] temp;
	static boolean[][][] visited;

	static Queue<int[]> q = new ArrayDeque<>();
	static int dz[] = { -1, 1, 0, 0, 0, 0 };
	static int dy[] = { 0, 0, -1, 1, 0, 0 };
	static int dx[] = { 0, 0, 0, 0, -1, 1 };
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];
		visited = new boolean[H][N][M];
		temp = new int[N][M];

		
		
		for(int i = 0 ; i < H ; i++) {
			for(int j = 0 ; j < N ; j++) {
				st =new StringTokenizer(br.readLine());
				for(int k = 0 ;k < M ; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		if (checkComplete()) {
			System.out.println(0);
		} else {
			setInitQueueState();
			bfs();

			if (checkComplete()) {
				System.out.println(cnt -1);
			} else
				System.out.println(-1);
		}

	}

	static void bfs() {
		while (!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for (int i = 0; i < size; i++) {
				int top[] = q.poll();
				for (int d = 0; d < 6; d++) {
					int nz = top[0] + dz[d];
					int ny = top[1] + dy[d];
					int nx = top[2] + dx[d];

					if (ny < 0 || nx < 0 || nz < 0 || ny >= N || nx >= M || nz >= H || map[nz][ny][nx] == 1
							|| map[nz][ny][nx] == -1 || visited[nz][ny][nx])
						continue;

					visited[nz][ny][nx] = true;
					map[nz][ny][nx] = 1;
					q.offer(new int[] { nz, ny, nx });
				}
			}

		}

	}

	static void setInitQueueState() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[i][j][k] == 1) {
						q.offer(new int[] { i, j, k });
					}

				}
			}
		}
	}

	static boolean checkComplete() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[i][j][k] == 0)
						return false;
				}
			}
		}
		return true;
	}

}
