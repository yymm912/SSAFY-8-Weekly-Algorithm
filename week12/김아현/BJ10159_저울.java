import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10159_저울 {

	static int N, M;
	static int[][] C;
	static final int BIG = 987654321;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		C = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			C[a][b] = 1;  // a가 b보다 크다
			C[b][a] = -1; // b가 a보다 작다
		}
		
		for (int k = 1; k <= N; k++) {
			
			for (int i = 1; i <= N; i++) {
				if(i == k) continue;
				
				for (int j = 1; j <= N; j++) {
					if(i == j || j == k) continue;
					
					if(C[i][k] == 1 && C[k][j] == 1)
						C[i][j] = 1;
					
					if(C[i][k] == -1 && C[k][j] == -1)
						C[i][j] = -1;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if(C[i][j] == 0)
					cnt++;
			}
			System.out.println(cnt-1);
		}
	}

}
