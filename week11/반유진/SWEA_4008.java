// SWEA 4008번 [모의 SW 역량테스트] 숫자 만들기
// 순열

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_4008_perm {

	static int T, N, min, max, ans;
	static int[] oper, num;

	static int[] tgt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());

			oper = new int[4];
			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			num = new int[N];
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			tgt = new int[N - 1];

			// 순열을 이용해서 연산자 순서 정하기
			perm(0);

			ans = Math.abs(max - min);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == N - 1) {

			calc();

			return;
		}

		for (int i = 0; i < 4; i++) {
			if (oper[i] == 0)
				continue;

			oper[i]--;
			tgt[tgtIdx] = i;
			perm(tgtIdx + 1);
			oper[i]++;
		}
	}

	static void calc() {
		int result = num[0];

		for (int i = 0; i < N - 1; i++) {
			if (tgt[i] == 0) { // +
				result += num[i + 1];
			} else if (tgt[i] == 1) { // -
				result -= num[i + 1];
			} else if (tgt[i] == 2) { // *
				result *= num[i + 1];
			} else if (tgt[i] == 3) { // /
				result /= num[i + 1];
			}
		}

		max = Math.max(max, result);
		min = Math.min(min, result);
	}

}
