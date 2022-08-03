package swea.p2001;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 누적합을 이용한 풀이
public class Main {
	static int N,T,M,ans;
	static int[][] dp;
	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("src/swea/p2001/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int t = 1;t<=T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			dp = new int[N+1][N+1];

			for(int i=1;i<=N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1;j<=N;j++) {
					dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] +Integer.parseInt(st.nextToken());
				}
			}

			ans = Integer.MIN_VALUE;
			// 파리 영역 M X M
			for(int i=1;i<=N-M+1;i++) {
				int sum = 0;
				for(int j=1;j<=N-M+1;j++) {
					sum = dp[i+M-1][j+M-1] - dp[i+M-1][j-1] - dp[i-1][j+M-1] + dp[i-1][j-1];
					ans = Math.max(ans, sum);
				}
			}
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

	}
}
