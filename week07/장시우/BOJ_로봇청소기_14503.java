package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_로봇청소기_14503 {
	
	static int N, M, r, c, d, cnt, ans;
	static int[][] map;
	static boolean[][] cleaned;
	
	// delta
	//				        북, 동, 남, 서
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cleaned = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		
		// 로봇 청소기의 x 좌표
		r = Integer.parseInt(st.nextToken());
		// 로봇 청소기의 y 좌표
		c = Integer.parseInt(st.nextToken());
		// 로봇 청소기가 바라보는 좌표
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			// 1. 현재 위치를 청소한다.
			if (!cleaned[r][c]) {
				cleaned[r][c] = true;
				ans++;
			}
			
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색을 진행한다.
			// 왼쪽을 확인하므로, delta index를 역순으로 해야한다.
			int nd;
			if (d == 0) nd = 3;
			else nd = d - 1;
			
			int nr = r + dx[nd];
			int nc = c + dy[nd];
			
			// 3. 왼쪽 방향에 청소할 공간이 있다면, 그 방향으로 전진한 후 청소한다.
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !cleaned[nr][nc]) {
				r = nr;
				c = nc;
				d = nd;
				cnt = 0;
			} else {
				// 4. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
				d = nd;
				cnt++;
			}
			
			// 5. 네 방향 모두 청소가 이미 되어있거나 벽인 경우, 바라보는 방향을 유지한 채 한칸 후진한 채 2로 돌아간다.
			if (cnt >= 4) {
				r -= dx[d];
				c -= dy[d];
				// 6. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우 종료한다.
				if (map[r][c] == 1) {
					break;
				}
				cnt = 0;
			}
		}
		
		System.out.println(ans);
	}
}
