package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11404 {
	static int N, M;
	static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1][N + 1];
		for(int i = 0; i < N + 1; i++) {
			Arrays.fill(arr[i], 10_000_001);
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			int here = Integer.parseInt(stk.nextToken());
			int there = Integer.parseInt(stk.nextToken());
			int value = Integer.parseInt(stk.nextToken());
			
			arr[here][there] = Math.min(arr[here][there], value);
		}
		
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(arr[i][j] > arr[i][k] + arr[k][j])
						arr[i][j] = arr[i][k] + arr[k][j];
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) { 
			for(int j = 1; j <= N; j++) {
				if(arr[i][j] == 10_000_001 || i == j) arr[i][j] = 0;
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
}
