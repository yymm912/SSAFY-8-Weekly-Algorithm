// BOJ 17070번 파이프 옮기기 1

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_17070 {

	static int N, cnt = 0;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cnt = 0;
		pipe(0, 1, 0);

		System.out.println(cnt);
	}

	static void pipe(int y, int x, int dir) {
		// dir = 0: 가로, 1: 세로, 2: 대각선
		if (y == N - 1 && x == N - 1) {
			cnt++;
			return;
		}

		// 파이프가 가로로 있을 때
		if (dir == 0) {
			// 가로로 밀 때
			if (x + 1 < N && map[y][x + 1] == 0)
				pipe(y, x + 1, 0);

			// 대각선으로 밀 때
			if (y + 1 < N && x + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0)
				pipe(y + 1, x + 1, 2);
		}

		// 파이프가 세로로 있을 때
		if (dir == 1) {
			// 세로로 밀 때
			if (y + 1 < N && map[y + 1][x] == 0)
				pipe(y + 1, x, 1);

			// 대각선으로 밀 때
			if (y + 1 < N && x + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0)
				pipe(y + 1, x + 1, 2);
		}

		// 파이프가 대각선으로 있을 때
		if (dir == 2) {
			// 가로로 밀 때
			if (x + 1 < N && map[y][x + 1] == 0)
				pipe(y, x + 1, 0);

			// 세로로 밀 때
			if (y + 1 < N && map[y + 1][x] == 0)
				pipe(y + 1, x, 1);

			// 대각선으로 밀 때
			if (y + 1 < N && x + 1 < N && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0)
				pipe(y + 1, x + 1, 2);
		}
	}
}
