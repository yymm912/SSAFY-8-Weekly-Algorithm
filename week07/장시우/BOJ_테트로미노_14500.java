package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_테트로미노_14500 {
	
	static int N, M, ans;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1번 모형 _ _ _ _ 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M - 3; j++) {
				ans = Math.max(ans, (map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i][j + 3]));
			}
		}
		// 1번 모형 
		// _
		// _
		// _
		// _
		for (int i = 0; i < N - 3; i++) {
			for (int j = 0; j < M; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 3][j]));
			}
		}
		
		// 2번 모형
		// _ _
		// _ _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i][j + 1] + map[i + 1][j + 1]));
			}
		}
		
		// 3번 모형
		// _
		// _
		// _ _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 2][j + 1]));
			}
		}
		
		// 3번 모형 
		// _ _ _
		// _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i][j + 1] + map[i][j + 2]));
			}
		}
		
		// 3번 모형
		// _
		// _ _ _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i][j] + map[i  + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]));
			}
		}
		
		// 3번 모형
		// _ _ _
		//     _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 2]));
			}
		}
		
		// 3번 모형
		//     _
		// _ _ _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i][j + 2] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2]));
			}
		}
		// 3번 모형
		// _ _
		//   _
		//   _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j + 1]));
			}
		}
		
		// 3번 모형
		// _ _
		// _
		// _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i][j + 1]));
			}
		}
		
		// 3번 모형
		//   _
		//   _
		// _ _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j + 1] + map[i + 1][j + 1] + map[i + 2][j] + map[i + 2][j + 1]));
			}
		}
		
		// 4번 모형
		// _
		// _ _
		//   _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j + 1]));
			}
		}
		
		// 4번 모형
		//   _
		// _ _
		// _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j + 1] + map[i + 1][j] + map[i + 1][j + 1] + map[i + 2][j]));
			}
		}
		
		// 4번 모형
		//  _ _
		//_ _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i + 1][j] + map[i + 1][j + 1] + map[i][j + 1] + map[i][j + 2]));
			}
		}
		
		// 4번 모형
		// _ _
		//   _ _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i][j] + map[i][j + 1] + map[i + 1][j + 1] + map[i + 1][j + 2]));
			}
		}
		
		// 5번 모형
		// _ _ _
		//   _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i][j] + map[i][j + 1] + map[i][j + 2] + map[i + 1][j + 1]));
			}
		}
		
		// 5번 모형
		//   _
		// _ _
		//   _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i + 1][j] + map[i + 1][j + 1] + map[i][j + 1] + map[i + 2][j + 1]));
			}
		}
		
		// 5번 모형
		//   _
		// _ _ _
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M - 2; j++) {
				ans = Math.max(ans, (map[i + 1][j] + map[i + 1][j + 1] + map[i + 1][j + 2] + map[i][j + 1]));
			}
		}
		
		// 5번 모형
		// _
		// _ _
		// _
		for (int i = 0; i < N - 2; i++) {
			for (int j = 0; j < M - 1; j++) {
				ans = Math.max(ans, (map[i][j] + map[i + 1][j] + map[i + 2][j] + map[i + 1][j + 1]));
			}
		}
		
		System.out.println(ans);
	}
}
