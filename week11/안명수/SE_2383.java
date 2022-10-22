package algo.SE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SE_2383 {
	static int N, M, R, C, L, P, time;
	static int[][] map;
	static int[][] people;

	static int Ay, Ax, By, Bx;
	static int[] A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());

			map = new int[N][N];
			people = new int[10][2];
			int idx = 0;
			P = -1;
			for (int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if (map[i][j] > 1) {
						if (idx++ == 0) {
							Ay = i;
							Ax = j;
						} else {
							By = i;
							Bx = j;
						}
					} else if (map[i][j] == 1) {
						people[++P][0] = i;
						people[P][1] = j;
					}
				}
			}

			A = new int[10];
			B = new int[10];
			time = Integer.MAX_VALUE;
			// 사람마다 내려갈 계단 선택 // 조합
			comb(0, P, 0, 0);

			sb.append('#').append(t).append(' ').append(time).append('\n');
		}

		System.out.println(sb);
	}

	static void comb(int cnt, int limit, int Aidx, int Bidx) {
		if (cnt > limit) {
			// 도착해서 계단에 이미 3명 내려가고있으면 대기 // 시뮬
			// 한명이라도 빠지면 다음사람 내려감. // 시뮬
			int localtime = 0;
			int[] copyA = A.clone();
			int[] copyB = B.clone();
			Arrays.sort(copyA, 0, Aidx);
			Arrays.sort(copyB, 0, Bidx);
			if (Aidx > 0) {
				if (Aidx > 3) {
					for (int i = 3; i < Aidx; i++) {
						if (copyA[i] < copyA[i - 3] + map[Ay][Ax] + 1) {
							copyA[i] = copyA[i - 3] + map[Ay][Ax];
						} else if(copyA[i] == copyA[i - 3] + map[Ay][Ax] + 1) {
							copyA[i] = copyA[i - 3] + map[Ay][Ax] + 1;
						}
					}
				}

				localtime = Math.max(localtime, copyA[Aidx - 1] + map[Ay][Ax] + 1);
			}

			if (Bidx > 0) {
				if (Bidx > 3) {
					for (int i = 3; i < Bidx; i++) {
						if (copyB[i] < copyB[i - 3] + map[By][Bx] + 1) {
							copyB[i] = copyB[i - 3] + map[By][Bx];
						} else if (copyB[i] == copyB[i - 3] + map[By][Bx] + 1) {
							copyB[i] = copyB[i - 3] + map[By][Bx] + 1;
						} 
					}
				}
				localtime = Math.max(localtime, copyB[Bidx - 1] + map[By][Bx] + 1);
			}

			time = Math.min(time, localtime);
			return;
		}

		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				A[Aidx] = dist(Ay, Ax, people[cnt][0], people[cnt][1]);
				comb(cnt + 1, limit, Aidx + 1, Bidx);
			} else {
				B[Bidx] = dist(By, Bx, people[cnt][0], people[cnt][1]);
				comb(cnt + 1, limit, Aidx, Bidx + 1);
			}
		}
	}

	static int dist(int y1, int x1, int y2, int x2) {
		return Math.abs(y1 - y2) + Math.abs(x1 - x2);
	}
}
