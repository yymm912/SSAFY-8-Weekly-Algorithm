package baekjoon.동전1_2293;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n,k,ans;
	static int[] v;
	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ans = 0;
		
		v = new int[n+1];
		for (int i = 1; i <= n; i++) {
			v[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(v);
		dp = new int[k+1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++) {
			for (int j = v[i]; j <= k; j++) {
				dp[j] = dp[j] + dp[j-v[i]];
				
			}
		}
		
		System.out.println(dp[k]);
		
	}

}
