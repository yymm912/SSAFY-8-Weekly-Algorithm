package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1051숫자정사각형 {

	static int N, M;
	static int map[][];
	static int max;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		// 입력 완
		
		// 정사각형 찾기
		int K = Math.min(N, M);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < K; k++) {
					
					if( i + k >= N || j + k >= M ) continue;
					
					if( map[i][j] == map[i+k][j] &&  map[i][j] == map[i][j+k] && map[i][j] == map[i+k][j+k] )
					result = Math.max(result, k+1);
				}
			}
		}
		
		
		System.out.println(result*result);
		
	}
}
