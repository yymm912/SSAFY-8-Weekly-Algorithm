// BOJ 2309번 일곱 난쟁이 
// 순열 풀이법 

package BOJ.Bruteforcing_Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


public class No_2309__solved_Comb {

	static int check = 0;
	static int[] nanJang = new int[9];
	static int[] tgt = new int[7];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			nanJang[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nanJang);

		comb(0, 0);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (check == 1)
			return;
		
		if (tgtIdx == tgt.length) {
			int sum = 0;

			for (int i = 0; i < 7; i++) {
				sum += tgt[i];
			}

			if (sum == 100) {
				check = 1;
				for (int i = 0; i < 7; i++) {
					System.out.println(tgt[i]);
				}
			}
			return;
		}

		for (int i = srcIdx; i < 9; i++) {
			tgt[tgtIdx] = nanJang[i];
			comb(i + 1, tgtIdx + 1);
		}
	}

}
