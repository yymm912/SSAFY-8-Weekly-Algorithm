// BOJ 9095번 1,2,3 더하기 
// 순열

package BOJ.Dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_9095__solved_perm {

	static int T, n, cnt;
	static int[] src = { 1, 2, 3 };
	static int[] tgt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			cnt = 0;

			for (int i = 1; i <= n; i++) {
				tgt = new int[i];
				perm(0);
			}

			System.out.println(cnt);
		}
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			int sum = 0;
			for (int i = 0; i < tgt.length; i++) {
				sum += tgt[i];
			}

			if (sum == n) {
				cnt++;
			}
			return;
		}

		for (int i = 0; i < src.length; i++) {
			tgt[tgtIdx] = src[i];
			perm(tgtIdx + 1);
		}
	}
}
