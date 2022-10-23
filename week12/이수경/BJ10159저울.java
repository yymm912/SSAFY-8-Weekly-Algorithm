package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10159저울 {

	static int N, M;
	static int matrix[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		matrix = new int[N+1][N+1];
		for (int i = 0; i < M; i++) {
			// 앞의 물건이 뒤의 물건보다 더 무겁다. 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			matrix[a][b] = 1;
		}
		
		
		for (int k = 0; k <= N; k++) {
			for (int i = 0; i <= N; i++) {
				if(i == k) continue;
				for (int j = 0; j <= N; j++) {
					if(i == j) continue;
					if( matrix[i][k] == 1 && matrix[k][j] == 1 ) matrix[i][j] = 1;
				}
			}
		}
		
	
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if(matrix[i][j] == 0 && matrix[j][i] == 0) cnt++;
			}
			System.out.println(cnt-1);
		}
		
	}

}
