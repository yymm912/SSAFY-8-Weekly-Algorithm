package algo.SE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SE_1486 {
	static int N, B, MIN;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			B = Integer.parseInt(stk.nextToken());
			
			arr = new int[N];
	
			
			int sum = 0;
			stk = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(stk.nextToken());
				sum += arr[i];
			}
			
			dp = new int[N][sum + 1];
			dp[0][0] = dp[0][arr[0]] = 1;
			for(int i = 1; i < N; i++) {
				dp[i][0] = 1;
				for(int j = 1; j <= sum; j++) {
					if(dp[i-1][j] == 1) dp[i][j] = 1;
					else if(j - arr[i] >= 0 && dp[i-1][j-arr[i]] == 1) dp[i][j] = 1;
				}
			}
			
			for(int i = B; i <= sum; i++) {
				if(dp[N-1][i] == 1) {
					MIN = i - B;
					break;
				}
			}
			System.out.println("#" + t + " " + MIN );
		}
		
	}
}
