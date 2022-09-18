import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14503_로봇청소기 {
	static int N, M;
	static int startY, startX, view;
	static int[][] map;
	static int answer = 1;
	static boolean fin;

	// 방향 0:북 1:동 2:남 3:서
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static int clean_cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		startY = Integer.parseInt(st.nextToken());
		startX = Integer.parseInt(st.nextToken());
		view = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(startY, startX, view);
		System.out.println(answer);
	}

	static void dfs(int y, int x, int view) {

		// 현재 위치 청소
		visited[y][x] = true;
		int temp = view;
		while (true) {
			if (fin)
				break;

			// 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행
			if (temp == 0)
				temp = 3;
			else
				temp = temp - 1;

			// 왼쪽 방향 좌표 계산
			int ny = y + dy[temp];
			int nx = x + dx[temp];
			// 왼쪽 방향에 아직 청소하지 않은 공간이 존재, 회전한다음 한칸 전진
			if (map[ny][nx] == 0 && !visited[ny][nx]) {
				clean_cnt = 0;
				answer++;
				dfs(ny, nx, temp);
			}

			// 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다
			else if (visited[ny][nx] || map[ny][nx] == 1) {
				clean_cnt++;
			}

			// 네 방향 모두 청소가 되어있거나 벽인 경우에는, 바라보는 방향을 유지한채 한 칸 후진
			if (clean_cnt == 4) {
				switch (view) {
				// 북
				case 0:
					y = y + 1;
					break;
				// 동
				case 1:
					x = x - 1;
					break;
				// 남
				case 2:
					y = y - 1;
					break;
				// 서
				case 3:
					x = x + 1;
					break;

				default:
					break;
				}

				// 뒤쪽 방향도 벽이라 후진을 할 수 없으면 작동을 멈춤
				if (map[y][x] == 1) {
					fin = true;
					return;
				} else {
					clean_cnt = 0;
					dfs(y, x, view);
				}

			}

		}

	}

}
