package algo1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 *  최대 공사 가능 깊이까지 구현하고, 이 후의 진행방식이 생각나지 않아,
 *   솔루션을 참고하여 if 경우 수를 나누고 완성함.
 */

public class SW_등산로조성_1949 {
	static int N, K, peek, max;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			/*************** case ****************/
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 크기
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이

			peek = 0;
			map = new int[N][N];
			visited = new boolean[N][N];
			max = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					peek = Math.max(peek, map[i][j]);				// 최대 높이 값을 기억
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == peek) { 						// 만약 해당 봉우리가 최대 높이라면 dfs 탐색 시작
						visited[i][j] = true;
						dfs(i, j, 1, 1, map[i][j]);
						visited[i][j] = false;
					}
				}
			}

			System.out.println("#" + t + " " + max);
			/*************** case ****************/

		}

	}

	/**
	 * @param y		: 지도 상 y 좌표
	 * @param x		: 지도 상 x 좌표
	 * @param cnt	: 공사 가능한 횟수 (문제에서는 딱 한번으로 주어짐)
	 * @param depth	: 현재 움직임 수
	 * @param height: 해당 지점의 높이
	 */
	static void dfs(int y, int x, int cnt, int depth, int height) {

		for (int d = 0; d < 4; d++) {
			if (depth > max) {
				max = depth;
			}

			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || visited[ny][nx]) continue;
			if (map[ny][nx] >= height) { 						 // 다음 지점이 현재 지점의 높이보다 같거나 높다면,
				if (cnt == 1 && height > map[ny][nx] - K) { 
					// 거기서, 공사가 가능한 상태이고, 
					// 현재 지점 높이가 최대 공사 깊이의 최대만큼 파낸 높이보다 높다면
					// dfs 탐색 가능하므로 진행 
					
					visited[ny][nx] = true;
					dfs(ny, nx, cnt - 1, depth + 1, height - 1); // 공사 가능 횟수를 차감하고 공사한 상태의 맵을 dfs 탐색
					visited[ny][nx] = false;
				}
			} else {											 // 다음 지점이 낮아서 탐색이 가능하다면 일반 탐색.
				visited[ny][nx] = true;
				dfs(ny, nx, cnt, depth + 1, map[ny][nx]);
				visited[ny][nx] = false;
			}

		}

	}
}
