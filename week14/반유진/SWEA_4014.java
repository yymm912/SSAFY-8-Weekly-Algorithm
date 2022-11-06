// SWEA 4014번 [모의 SW 역량테스트] 활주로 건설

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_4014 {

	static int T, N, X, ans;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;
			for (int i = 0; i < N; i++) {
				func(map[i]);

				int[] arr = new int[N];
				for (int j = 0; j < N; j++) {
					arr[j] = map[j][i];
				}
				func(arr);
			}

			System.out.println("#" + t + " " + ans);
		}

	}

	static void func(int[] arr) {
		int j = 0;
		int cnt = 0;
		int height = arr[0];

		while (j < N) {
			if (height == arr[j]) {
				cnt++;
				j++;
			} else if (height + 1 == arr[j]) {
				if (cnt < X)
					return;

				height += 1;
				cnt = 1;
				j++;
			} else if (height - 1 == arr[j]) {
				int unCnt = 0;
				for (int i = j; i < N; i++) {
					if (arr[i] == height - 1) {
						unCnt++;

						if (unCnt == X)
							break;
					} else
						break;
				}
				if (unCnt < X)
					return;
				else {
					j += X;
					cnt = 0;
					height -= 1;
				}
			} else {
				return;
			}
		}

		ans++;
	}

}
