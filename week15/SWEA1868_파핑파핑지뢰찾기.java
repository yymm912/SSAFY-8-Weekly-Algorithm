import java.io.*;
import java.util.*;

public class Solution {
	static int T, N, cnt;
	static char[][] map;
	
	static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 지뢰가 아닌 칸이면서 주변에 지뢰가 없는 경우
					if (map[i][j] == '.' && check(i, j)) {
						// 해당 지점 클릭
						bfs(i, j);
						cnt++;
					}
				}
			}
			
			// 남은 지뢰가 아닌 칸 클릭
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.')
						cnt++;
				}
			}

			System.out.println("#" + tc + " " + cnt);
		}
	}
	static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r, c});
		map[r][c] = 'x';
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 8; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if (nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc] == '.') {
					if (check(nr, nc)) {
						q.offer(new int[] {nr, nc});
					}
					// 방문 완료
					map[nr][nc] = 'x';
				}
			}
		}
	}
	// 8방 지뢰 탐색
	static boolean check(int i, int j) {
		for (int d = 0; d < 8; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			
			// 범위 내 지뢰를 발견할 경우
			if (nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc] == '*')
				return false;
		}
		// 지뢰를 발견하지 못한 경우
		return true;
	}
}
