// BOJ 17281번 ⚾

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_17281 {

	static int N, max;
	static int[][] inning;
	static int[] player, tgt;
	static boolean[] select, base;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		select = new boolean[10];
		tgt = new int[10];

		inning = new int[N][10];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		select[4] = true;
		tgt[4] = 1;

		max = 0;
		perm(2);

		System.out.println(max);
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == 10) {
			game();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (select[i])
				continue;

			tgt[i] = tgtIdx;
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

	static void game() {
		int score = 0;
		int out = 0;
		int first = 1;

		for (int i = 0; i < N; i++) {
			out = 0;
			base = new boolean[4];

			boolean check = false;
			end: while (true) {
				if (check) // for문 탈출 시 check 변수를 통해 while문까지 완전 탈출
					break;

				for (int j = first; j <= 9; j++) {
					int type = inning[i][tgt[j]];

					switch (type) {
					case 0:
						out++;
						break;
					case 1:
						if (base[3]) {
							score++;
							base[3] = false;
						}
						for (int k = 3; k >= 1; k--) {
							if (base[k]) {
								base[k + 1] = true;
								base[k] = false;
							}
						}
						base[1] = true;
						break;

					case 2:
						for (int k = 3; k >= 2; k--) {
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						if (base[1]) {
							base[3] = true;
							base[1] = false;
						}
						base[2] = true;
						break;

					case 3:
						for (int k = 3; k >= 1; k--) {
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						base[3] = true;
						break;

					case 4:
						for (int k = 1; k < 4; k++) {
							if (base[k]) {
								score++;
								base[k] = false;
							}
						}
						score++; // 현재 타자까지 모두 홈으로 들어옴
						break;
					}
					if (out == 3) {
						first = j + 1;
						if (first == 10)
							first = 1;

						check = true;
						break end;
					}
				}

				first = 1;
			}
		}

		max = Math.max(max, score);
	}

}
