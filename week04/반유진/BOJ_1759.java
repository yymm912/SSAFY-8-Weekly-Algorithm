// BOJ 1759번 암호 만들기  

package BOJ.Mathematics;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1759 {

	static int L, C, collection = 0, consonant = 0;
	static char[] src;

	static char[] tgt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		tgt = new char[L];
		src = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			src[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(src);
		comb(0, 0);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == tgt.length) {
			for (int i = 0; i < L; i++) {
				if (tgt[i] == 'a' || tgt[i] == 'e' || tgt[i] == 'i' || tgt[i] == 'o' || tgt[i] == 'u') {
					collection = 1;
				} else {
					consonant++;
				}
			}

			if (collection == 1 && consonant >= 2) {
				for (int i = 0; i < L; i++) {
					System.out.print(tgt[i]);
				}
				System.out.println();
			}

			collection = 0;
			consonant = 0;
			return;
		}

		for (int i = srcIdx; i < src.length; i++) {
			tgt[tgtIdx] = src[i];
			comb(i + 1, tgtIdx + 1);
		}
	}

}
