import java.io.*;
import java.util.*;

public class Main {
	static int N, M, max;
	static int[][] map;
	static boolean[][] v;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new int[N][M];
		v = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		boomerang(0, 0);
		System.out.println(max);
	}
	public static void boomerang(int cnt, int sum) {
		// 탐색 완료
		if (cnt == N * M) {
			max = Integer.max(max, sum);
			return;
		}

		int r = cnt / M;
		int c = cnt % M;

		if (!v[r][c]) {
			// type1
			if (r + 1 < N && c + 1 < M && !v[r + 1][c] && !v[r][c + 1]) {
				v[r][c] = true;
				v[r + 1][c] = true;
				v[r][c + 1] = true;

				int tmp = sum + 2 * (map[r][c]) + map[r + 1][c] + map[r][c + 1];
				boomerang(cnt + 1, tmp);

				v[r][c] = false;
				v[r + 1][c] = false;
				v[r][c + 1] = false;
			}
			// type2
			if (r + 1 < N && c - 1 >= 0 && !v[r + 1][c] && !v[r][c - 1]) {
				v[r][c] = true;
				v[r + 1][c] = true;
				v[r][c - 1] = true;

				int tmp = sum + 2 * (map[r][c]) + map[r + 1][c] + map[r][c - 1];
				boomerang(cnt + 1, tmp);

				v[r][c] = false;
				v[r + 1][c] = false;
				v[r][c - 1] = false;
			}
			// type3
			if (r - 1 >= 0 && c - 1 >= 0 && !v[r - 1][c] && !v[r][c - 1]) {
				v[r][c] = true;
				v[r - 1][c] = true;
				v[r][c - 1] = true;

				int tmp = sum + 2 * (map[r][c]) + map[r - 1][c] + map[r][c - 1];
				boomerang(cnt + 1, tmp);

				v[r][c] = false;
				v[r - 1][c] = false;
				v[r][c - 1] = false;
			}
			// type4
			if (r - 1 >= 0 && c + 1 < M && !v[r - 1][c] && !v[r][c + 1]) {
				v[r][c] = true;
				v[r - 1][c] = true;
				v[r][c + 1] = true;

				int tmp = sum + 2 * (map[r][c]) + map[r - 1][c] + map[r][c + 1];
				boomerang(cnt + 1, tmp);

				v[r][c] = false;
				v[r - 1][c] = false;
				v[r][c + 1] = false;
			}

		}
		// none
		boomerang(cnt+1, sum);
	}

}