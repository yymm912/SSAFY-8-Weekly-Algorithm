package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_파이프옮기기1_17070 {
	
	static int N, ans;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dir : 가로 0, 세로 1, 대각선 2
		dfs(0, 1, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int x, int y, int dir) {
		// 기저 조건
		if (x == N - 1 && y == N - 1) {
			ans++;
			return;
		}
		
		// 가로
		if (dir == 0) {
			if (y + 1 < N && map[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
			// 세로
		} else if (dir == 1) {
			if (x + 1 < N && map[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
			// 대각선
		} else if (dir == 2) {
			if (y + 1 < N && map[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
			if (x + 1 < N && map[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
		}
		
		if (x + 1 < N && y + 1 < N && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) {
			dfs(x + 1, y + 1, 2);
		}
	}
}
