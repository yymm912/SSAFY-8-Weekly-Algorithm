package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10844_쉬운계단수 {
	
	static int N, X;
	static long answer;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1][11];
		
		for(int i=1; i<=9; i++) {
			dp[1][i] = 1;
		}
		
		for(int n=2; n<=N; n++) {
			for(int j=0; j<10; j++) {
				
				if(j==0) {
					dp[n][0] = dp[n-1][1]%1_000_000_000;
				}
				else dp[n][j] = (dp[n-1][j-1] + dp[n-1][j+1])%1_000_000_000;
			}
		}
		
		for(int j=0; j<=9; j++) {
			answer += dp[N][j];
			answer %= 1_000_000_000;
		}
		System.out.println(answer);
		

	}
	
}