package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5643키순서 {

	static int T, N, M;
	static int matrix[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			matrix = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				matrix[a][b]  = 1;
			}
			
			// 플로이드로 연결 
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					if( k == i )continue;
					for (int j = 1; j <= N; j++) {
						if( i == j || j == k )continue;
						if( matrix[i][k] == 1 && matrix[k][j] == 1 ) {
							matrix[i][j] = 1;
						}
					}
				}
			}
			
			// 자신보다 작거나 큰 사람 알 수 있으면 cnt + 1 
			// 자신제외한 나머지들 만족하면 (= N - 1) ans++
			int ans = 0;
		    for (int i = 1; i <= N; i++) {
		        int cnt = 0;
		        for (int j = 1; j <= N; j++) {
		            if (matrix[i][j] == 1 || matrix[j][i] == 1) cnt++;
		        }
		        if (cnt == N - 1) ans++;
		    }
			
			System.out.println("#" + t + " " + ans);
		}
		
		
		
	}

}
