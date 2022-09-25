package dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ1463_1로만들기 {

	static int N, cnt;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1]; // 0 dummy
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = dp[1] = 0;
		
		for(int i=2; i<N+1; i++) {
			dp[i] = dp[i-1] + 1;
			
			if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
			if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
		}
		System.out.println(dp[N]);
	}
}
