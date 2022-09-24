package baekjoon.일로만들기_1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		dp = new int[N+1];
		// dp[1] = 0
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1]+1; // X = i라고 하면 기본적으로 연산량이 1 증가
			if(i%3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1); // X를 3 으로 나눈 결과에서 1을 더한 값과 기존 값을 비교
			}
			if(i%2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1); // X를 2로 나눈 결과에서 1을 더한 값과 비교
			}
			// X-1은 굳이 ?
		}
		System.out.println(dp[N]);
	}
}
