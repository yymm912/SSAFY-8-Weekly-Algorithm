import java.util.*;
import java.io.*;

public class BJ_파이프옮기기1_17070 {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] input;
	static int[][][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				input[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		dp = new int[N+5][N+5][3];	// 0: 가로, 1: 세로, 2: 대각선
		dp[1][2][0] = 1;
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				move(i, j);
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
		
	}
	
	// y, x 로 이동
	static void move(int y, int x) {
		if (input[y][x] == 0) {
			dp[y][x][0] += dp[y][x-1][0] + dp[y][x-1][2];
			dp[y][x][1] += dp[y-1][x][1] + dp[y-1][x][2];
		}
		
		if (input[y][x] == 0 && input[y-1][x] == 0 && input[y][x-1] == 0) {
			dp[y][x][2] += dp[y-1][x-1][0] + dp[y-1][x-1][1] + dp[y-1][x-1][2];
		}
	}
	
}
