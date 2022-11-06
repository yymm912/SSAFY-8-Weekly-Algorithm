package _14주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14925_목장건설하기 {
	static int N,M;
	static int [][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N+1][M+1];
		for(int i = 1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1;j<=M;j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = dp[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
			}
		}
		
		int lo = 1, hi = 1000;
		
		while(lo <= hi) {
			int mid = (lo+hi)/2;
			if(solve(mid)) {
				lo = mid + 1;
			}else {
				hi = mid - 1;
			}
		}
		System.out.println(hi);
	}
	
	static boolean solve(int k) {
		for(int i = 1;i<=N-k+1;i++) {
			for(int j = 1;j<=M-k+1;j++) {
				int r = i+k-1;
				int c = j+k-1;
				if(dp[r][c] - dp[r][j-1] - dp[i-1][c] + dp[i-1][j-1] == 0)
					return true;
			}
		}
		return false;
	}

}
