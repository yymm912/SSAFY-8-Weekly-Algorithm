// SWEA 2112번 [모의 SW 역량테스트] 보호 필름

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_2112 {

	static int T, D, W, K, ans;
	static int[][] map, copy_map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[D][W];
			copy_map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					copy_map[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = Integer.MAX_VALUE;

			if (check()) {
				ans = 0;
			} else {
				dfs(0, 0);
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void dfs(int idx, int cnt) {
		if (cnt >= ans)
			return;

		if (idx == D) {
			if (check()) {
				ans = Math.min(ans, cnt);
			}

			return;
		}

		dfs(idx + 1, cnt); // 약품 투입 X

		for (int i = 0; i < W; i++) { // A 약품 투약
			map[idx][i] = 0;
		}
		dfs(idx + 1, cnt + 1);

		for (int i = 0; i < W; i++) { // B 약품 투약
			map[idx][i] = 1;
		}
		dfs(idx + 1, cnt + 1);

		for (int j = 0; j < W; j++) {
			map[idx][j] = copy_map[idx][j];
		}
	}

	static boolean check() {
		outer: for (int i = 0; i < W; i++) {
			int same = map[0][i];
			int cnt = 1;
			for (int j = 1; j < D; j++) {
				if (same == map[j][i]) {
					cnt++;
				} else {
					cnt = 1;
					same = map[j][i];
				}

				if (cnt == K)
					continue outer;
			}
			if (cnt < K)
				return false;
		}

		return true;
	}

}
