// BOJ 10974번 모든 순열

package BOJ.Bruteforcing_Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_10974 {

	static int N;
	static int[] src, tgt;
	static boolean[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		tgt = new int[N];
		src = new int[N];
		select = new boolean[N];

		for (int i = 0; i < N; i++) {
			src[i] = i;
		}

		perm(0);
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == N) {
			for (int i = 0; i < N; i++) {
				System.out.print((tgt[i] + 1) + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < N; i++) {
			if (select[i])
				continue;

			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm(tgtIdx + 1);
			select[i] = false;
		}
	}

}
