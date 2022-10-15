// SWEA 2115번 [모의 SW 역량테스트] 벌꿀채취

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_2115 {

	static int T, N, M, C, ans;
	static int[][] map;
	static boolean[][] visit;

	static int[] mA;
	static int[] mB;
	static int max;

	static boolean[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;

			for (int i = 0; i < N; i++) {
				visit = new boolean[N][N];
				mA = new int[M];
				mB = new int[M];
				select = new boolean[M];

				max = 0;

				func(i);
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void func(int yA) {
		int maxA = 0;
		int maxB = 0;

		for (int xA = 0; xA < N; xA++) {
			if (xA + M > N)
				return;

			for (int m = 0; m < M; m++) { // 현재 A visit 표시
				visit[yA][xA + m] = true;
				mA[m] = map[yA][xA + m];
			}

			max = 0;
			subset(0, mA);
			maxA = max; // 현재 A 위치에서 최대 꿀의 양

			for (int yB = yA; yB < N; yB++) {
				outer: for (int xB = 0; xB < N; xB++) { // B

					if (xB + M > N)
						continue;

					// 벌통이 겹치는지 확인
					for (int m = 0; m < M; m++) {
						if (visit[yB][xB + m]) { // 이미 선택된 벌통이면
							continue outer;
						}

						mB[m] = map[yB][xB + m];
					}

					// 벌통이 겹치지 않도록 선택하고 나면
					max = 0;
					subset(0, mB); // 현재 B 위치에서 최대 꿀의 양 구하기
					maxB = max;
					
					ans = Math.max(ans, maxA + maxB);
				}
			}

			for (int m = 0; m < M; m++) { // A visit 초기화
				visit[yA][xA + m] = false;
			}
		}
	}

	static void subset(int srcIdx, int[] arr) {
		if (srcIdx == M) {
			int sum = 0;
			int squSum = 0;

			for (int i = 0; i < M; i++) {
				if (select[i]) {
					sum += arr[i];
					squSum += (arr[i] * arr[i]);

					if (sum > C) {
						return;
					}
				}
			}

			max = Math.max(max, squSum);

			return;
		}

		select[srcIdx] = true; // 선택
		subset(srcIdx + 1, arr); // 다음 선택

		select[srcIdx] = false; // 비선택
		subset(srcIdx + 1, arr); // 다음 선택
	}

}
