package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dfs + dp
public class BJ1520_내리막길 {

	static int M, N, cnt;
	static int[][] map, dp;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		dp = new int[M][N]; // 현재 좌표에서 도착지점까지 가는 경로 갯수 저장
		map = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1; // 경로 갯수 초기화
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int x, int y) {
		if(x == M-1 && y == N-1) {
			return 1;
		}
		
		if(dp[x][y] != -1) return dp[x][y];
		
		dp[x][y] = 0;
		
		for(int d=0; d<4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= M || ty >= N ) continue;
			
			if(map[x][y] > map[tx][ty])
				dp[x][y] += dfs(tx, ty);
		}
		return dp[x][y];
	}

}
