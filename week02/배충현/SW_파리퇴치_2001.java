import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_파리퇴치_2001 {

	static int T, N, M;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N+1][N+1];
			
			// 누적합 배열 생성
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					arr[i][j] = arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1] + Integer.parseInt(st.nextToken());
				}
			}
			
			// max 계산
			System.out.println("#" + t + " " + catchFly(M));
			
		}
	}
	
	static int catchFly(int m) {
		int max = 0;
		
		for (int i = m; i <= N; i++) {
			for (int j = m; j <= N; j++) {
				max = Math.max(max, arr[i][j] - arr[i-m][j] - arr[i][j-m] + arr[i-m][j-m]);
			}
		}
		
		return max;
	}

}
