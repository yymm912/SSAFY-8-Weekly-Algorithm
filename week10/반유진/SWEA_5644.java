// SWEA 5644번 [모의 SW 역량테스트] 무선 충전

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_5644 {

	static int T, M, N, ans;
	static int ay, ax, by, bx;
	static int[] A, B;
	static BC[] arrBC;

	// 정지-상-우-하-좌
	static int[] dy = { 0, -1, 0, 1, 0 };
	static int[] dx = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken()); // 총 이동시간
			N = Integer.parseInt(st.nextToken()); // BC의 개수

			A = new int[M];
			B = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			arrBC = new BC[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());

				arrBC[i] = new BC(y, x, c, p);
			}

			ans = 0;
			ay = ax = 1;
			by = bx = 10;

			func();

			for (int i = 0; i < M; i++) {
				ay += dy[A[i]];
				ax += dx[A[i]];
				by += dy[B[i]];
				bx += dx[B[i]];

				func();
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void func() {
		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;

				BC bcA = arrBC[i];
				BC bcB = arrBC[j];

				int dA = Math.abs(bcA.y - ay) + Math.abs(bcA.x - ax); // 충전기와 A의 거리
				int dB = Math.abs(bcB.y - by) + Math.abs(bcB.x - bx); // 충전기와 B의 거리

				if (dA > bcA.c && dB > bcB.c) // A, B 모두 충전 범위에 속하지 못할 경우
					continue;

				int pA = bcA.p;
				int pB = bcB.p;

				if (dA > bcA.c)
					pA = 0;
				if (dB > bcB.c)
					pB = 0;

				if (i == j) { // 같은 충전소를 쓸 경우
					sum = Math.max(pA, pB);
				} else { // A, B가 다른 충전소일 경우
					sum = pA + pB;
				}

				max = Math.max(max, sum);
			}
		}

		ans += max;
	}

	static class BC {
		int y;
		int x;
		int c;
		int p;

		public BC(int y, int x, int c, int p) {
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}
	}

}
