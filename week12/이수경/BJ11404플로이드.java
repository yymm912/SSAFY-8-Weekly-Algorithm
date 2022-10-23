package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11404플로이드 {

	static int N, M;
	static int map[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		
		//초기화
		for (int i = 1; i <= N ; i++) {
			Arrays.fill(map[i], 99999999 );
			map[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[a][b] = Math.min(map[a][b], c);
		}
		
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if( i == k ) continue;
				for (int j = 1; j <= N; j++) {
					if( i == j ) continue;
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]) ;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] >= 99999999 ? 0 + " " : map[i][j]  + " ");
			}
			System.out.println();
		}
		
		
	}
}
