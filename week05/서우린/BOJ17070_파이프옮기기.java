package _5주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070_파이프옮기기 {
	static int N;
	static int [][] board;
	static int [][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		
		dp = new int[N+1][N+1][3];
		for(int i = 1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j<=N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
				
		dp[1][2][0] = 1;
		//0 : 가로 , 1 : 대각선 , 2 : 세로
		for(int j = 1;j<=N;j++) {
			for(int i = 1;i<=N;i++) {
				//가로 방향 설치
				if(board[i][j] == 0) {
					
					if(dp[i][j-1][0] > 0) dp[i][j][0] += dp[i][j-1][0];
					if(dp[i][j-1][1] > 0) dp[i][j][0] += dp[i][j-1][1];
				}
				
				//대각선 아래 방향 설치
				if(board[i][j] == 0 && board[i][j-1] == 0 && board[i-1][j] == 0) {
					
					if(dp[i-1][j-1][2] > 0) dp[i][j][1] += dp[i-1][j-1][2];
					if(dp[i-1][j-1][1] > 0) dp[i][j][1] += dp[i-1][j-1][1];
					if(dp[i-1][j-1][0] > 0) dp[i][j][1] += dp[i-1][j-1][0];
				}
				
				//아래 방향 설치
				
				if(board[i][j] == 0) {
					
					if(dp[i-1][j][2] > 0) dp[i][j][2] += dp[i-1][j][2];
					if(dp[i-1][j][1] > 0) dp[i][j][2] += dp[i-1][j][1];
				}
				
			}
		}
		int ans = 0;
		for(int z = 0;z<3;z++) {
			if(dp[N][N][z] > 0)
				ans += dp[N][N][z];
		}
		
		
		System.out.println(ans);
		
	}

}
