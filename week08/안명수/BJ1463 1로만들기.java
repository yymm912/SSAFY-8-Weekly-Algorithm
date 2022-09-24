package algo.BJ.l로만들기;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int num;
	static int[] dp;
	public static void main(String[] args) throws Exception{
		num = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		
		
		dp = new int[1_000_001];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;
		for(int i = 2; i <= 1_000_000; i++) {
			if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
			if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
		}
		
		System.out.println(dp[num]);
	}

}
