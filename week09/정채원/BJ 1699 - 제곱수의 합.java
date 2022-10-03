import java.io.*;
import java.util.*;

class Main{
	static int N;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 제곱 가능한 수 : sqrt(100_000) = sqrt(10)*100 ~= 400
		// 1부터 sqrt(n)까지 늘려감
		int lim = (int) Math.sqrt(N); // 내림
		if(lim*lim < N) lim++; // 올림으로
		
		/**
		 1~lim 제곱수 하나씩 늘려감
		 */
		int[] dp = new int[N+1];
		dp[1] = 1;
		for(int i=2; i<=N; i++) dp[i] = i;
		for(int l=2; l<=lim; l++) {
			int pow = l*l;
			for(int i=pow; i<=N; i++) {
				dp[i] = Math.min(dp[i], 1 + dp[i - pow]);
			}
		}
		System.out.println(dp[N]);
	}
}