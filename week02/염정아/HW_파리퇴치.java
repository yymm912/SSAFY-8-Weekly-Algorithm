


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [입력]
 * T
 * N:맵 크기  M:파리채 크기 
 * return 최대한 많이 죽인 파리의 개수 
 * */


public class HW_파리퇴치 {
	static int T, N, M;
	static int[][] memo; // memoization


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 변수 입력 받기
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 배열 입력 받기: 메모이제이션
			memo = new int[N + 1][N + 1];
			for (int y = 1; y <= N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 1; x <= N; x++) {
					int k = Integer.parseInt(st.nextToken());
					memo[y][x] = memo[y - 1][x] + memo[y][x - 1] - memo[y - 1][x - 1] + k;
				}

			}

			// for (int i = 0; i < N; i++) {
			// System.out.println(Arrays.toString(memo[i]));
			// }

			// 파리 잡기
			int max = 0;
			for (int y = M; y <= N; y++) {
				for (int x = M; x <= N; x++) {
					max = Math.max(memo[y][x] - memo[y - M][x] - memo[y][x - M]
						+ memo[y - M][x - M], max);

				}

			}

			sb.append("#" + t + " " + max + "\n");

		} // end test cast

		System.out.println(sb.toString());

	} // end main
}
