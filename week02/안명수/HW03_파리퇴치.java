package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class hwalgo03_부울경_3반_안명수 {
	static int N, M;
	static int[][] area;
	static int[][] sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int TC = 1; TC <= T; TC++) {
			sb.append("#" + TC + " ");
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			
			area = new int[N + 1][N + 1];
			sum = new int[N + 1][N + 1];
			for(int i = 1; i <= N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 1; j <= N; j++) {
					area[i][j] = Integer.parseInt(stk.nextToken());
					sum[i][j] = area[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
				}
			}
			
			int ans = 0;
			for(int i = N; i - M >= 0; i--) {
				for(int j = N; j - M >= 0; j--) {
					ans = Math.max(ans, sum[i][j] - sum[i-M][j] - sum[i][j-M] + sum[i-M][j-M]);
				}
			}
			
			
			sb.append(ans + "\n");
		}
		System.out.println(sb);
	}
}
