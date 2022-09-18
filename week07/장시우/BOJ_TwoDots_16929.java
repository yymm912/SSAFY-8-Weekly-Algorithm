package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_TwoDots_16929 {
	
	static int N, M;
	static char[][] map;
	static boolean[][] checked;
	
	static boolean hasAns;
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// dfs를 사용해 각 점에서 시작하는 싸이클이 존재하는지 확인한다.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				checked = new boolean[N][M];
				checked[i][j] = true;
				dfs(i, j, i, j, map[i][j], 0);
				if (hasAns) return;
			}
		}
		
		System.out.println("No");
	}
	
	static void dfs(int x, int y, int targetX, int targetY, char c, int count) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny] && map[nx][ny] == c) {
				checked[nx][ny] = true;
				dfs(nx, ny, targetX, targetY, c, count + 1);
			} else if (nx >= 0 && nx < N && ny >= 0 && ny < M && checked[nx][ny] && count + 1 >= 4 && nx == targetX && ny == targetY) {
				System.out.println("Yes");
				hasAns = true;
				return;
			}
		}
	}
}
