package baekjoon.평범한배낭_12865;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[][] P;
	static int[][] bag;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		P = new int[N+1][2]; // 0 : W, 1 : V
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			P[i][0] = Integer.parseInt(st.nextToken());
			P[i][1] = Integer.parseInt(st.nextToken());
		}
		
		bag = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < K+1; j++) {
				if( j < P[i][0] ) {
					bag[i][j] = bag[i-1][j];
				}else {
					bag[i][j] = Math.max(bag[i-1][j], bag[i-1][j-P[i][0]] + P[i][1]);
				}
			}
		}
		
		System.out.println(bag[N][K]);
		
	}
}
