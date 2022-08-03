package swea;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HW03_파리퇴치 {

	static int T, N, M;
	static int map[][];

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("SWEA2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N + 1][N + 1];

			// 누적합 구하기 
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()) + map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
				}
			}

			
			int Max = Integer.MIN_VALUE;
			// 파리채 크기 M만큼 범위 구하기 
			for(int i=M;i<=N;i++) {
				for(int j=M;j<=N;j++) {
					Max = Math.max(Max, map[i][j] - (map[i-M][j] + map[i][j-M]) + map[i-M][j-M] );
				}
			}

			System.out.println("#" + t + " " + Max);
		}

	}

}
