package week3.김정윤;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_음료수얼려먹기 {
	static int N, M, ans;
	static int[][] ice;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ice = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dfs(i, j) == true)
					ans++;
			}
		}
		System.out.println(ans);
	}
	
	static boolean dfs(int x, int y) {
		// 기저조건
		if (x < 0 || x >= N || y < 0 || y >= M) 
			return false;
		
		if (ice[x][y] == 0) {
			// 0 - 얼음, 1 - 가림막, 2 - 방문완료
			ice[x][y] = 2;
			
			// 얼음 틀 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				dfs(x+dx[i], y+dy[i]);
			}

			return true;
		}
		return false;
	}

}
