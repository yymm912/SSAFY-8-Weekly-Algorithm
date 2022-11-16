package problem.SW;


import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;


public class SW2112_보호필름_RE {
	static int ans, T, D, W, K;
	static int[][] map, mapTemp;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);

		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();

			map = new int[D][W];
			mapTemp = new int[D][W];
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) map[i][j] = mapTemp[i][j] = sc.nextInt();
			}

			// 초기화
			ans = D;

			// 탐색
			if (check()) ans = 0;
			else {
				for (int i = 1; i <= D; i++) {
					if (i > ans) break;
					comb(0, 0, i);
				}

			}

			// 출력
			System.out.println("#" + t + " " + ans);

			// break;
		}

	} // end main


	// 투입할 행을 골라 약을 주사하는 함수
	private static void comb(int dep, int idx, int drugCnt) {
		if (dep >= ans) return; // 가지치기

		if (dep == drugCnt) {
			if (check()) {
				ans = Math.min(ans, dep);
				return;
			}

		}

		for (int i = idx; i < D; i++) {
			for (int type = 0; type <= 1; type++) {
				inject(i, type);
				comb(dep + 1, i + 1, drugCnt);
				unInject(i);
			}

		}

	} // end comb


	private static boolean check() {
		boolean totFlag = true;

		for (int c = 0; c < W; c++) {
			int cnt = 1;
			int prev = map[0][c];
			boolean flag = false;

			for (int r = 1; r < D; r++) {
				if (map[r][c] == prev) cnt++;
				else {
					cnt = 1;
					prev = map[r][c];
				}

				if (cnt >= K) {
					flag = true;
					break;
				}

			}

			totFlag &= flag;

		}

		return totFlag;
	} // end check


	private static void inject(int r, int type) {
		for (int c = 0; c < W; c++) map[r][c] = type;
	} // end inject


	private static void unInject(int r) {
		for (int c = 0; c < W; c++) map[r][c] = mapTemp[r][c];
	} // end unInject


	private static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) System.out.println(Arrays.toString(map[i]));
		System.out.println();
	} // end print
}
