package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11048_이동하기 {
	
	static int N, M;
	static int[][] map;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		dp = new int[N][M];
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = map[0][0];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				col_dfs(i,j);
				row_dfs(i,j);
				dfs(i,j);
			}
		}
		System.out.println(dp[N-1][M-1]);
	}
	
	static void row_dfs(int row, int col) {
		
		if(row==N) return;
		if(row==0) {
			row_dfs(row+1, col);
			return;
		}
		
		if(dp[row][col] < dp[row-1][col]+map[row][col]) {
			dp[row][col] = dp[row-1][col]+map[row][col];
			row_dfs(row+1, col);
		}
	}
	
	static void col_dfs(int row, int col) {
		
		if(col==M) return;
		if(col==0) {
			col_dfs(row, col+1);
			return;
		}
		
		if(dp[row][col] < dp[row][col-1]+map[row][col]) {
			dp[row][col] = dp[row][col-1]+map[row][col];
			col_dfs(row, col+1);
		}
		
	}

	static void dfs(int row, int col) {
		
		if(row >= N || col >=M) return;
		if(row==0||col==0) {
			dfs(row+1, col+1);
			return;
		}
		
		if(dp[row][col] < dp[row-1][col-1]+map[row][col]) {
			dp[row][col] = dp[row-1][col-1]+map[row][col];
			dfs(row+1, col+1);
		}
		
	}

}