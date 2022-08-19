// BOJ 2309번 일곱 난쟁이 

package BOJ.Bruteforcing_Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class No_2309__2 {

	static int sum = 0;
	static int[] nanJang = new int[9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			nanJang[i] = Integer.parseInt(br.readLine());
			sum += nanJang[i];
		}

		int check = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (sum - nanJang[i] - nanJang[j] == 100) {
					nanJang[i] = 101;
					nanJang[j] = 101;
					check = 1;
					break;
				}
			}
			if (check == 1)
				break;
		}

		Arrays.sort(nanJang);

		for (int i = 0; i < 7; i++) {
			System.out.println(nanJang[i]);
		}
	}

}
