import java.io.*;
import java.util.*;

class Main{
	static int MOD = 1_000_000_009;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] dp = new int[100_001][4]; // [][0] : dummy, [n][k] : 총합 n, 끝자리가 k로 끝난 개수
		
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = dp[3-1][2] + dp[3-1][3]; //1
		dp[3][2] = dp[3-2][1] + dp[3-2][3]; //1
		dp[3][3] = 1;// 1
		int getMax = 3;
		for(int tc=0; tc<T; tc++) {
			int n = Integer.parseInt(br.readLine());
			if(n > getMax) {
				for(int i=getMax+1; i<=n; i++) {
					dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % MOD;
					dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % MOD;
					dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % MOD;
				}
				getMax = n;
			}
			System.out.println(((dp[n][1] + dp[n][2])%MOD + dp[n][3])%MOD);
			
		}
	}
}