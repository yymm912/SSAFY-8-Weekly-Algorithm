// BOJ 1339번 단어 수학 

package BOJ.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class No_1339 {

	static int N, num, result;
	static int[] alphabet = new int[26];
	static String word;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			word = br.readLine();
			for (int j = 0; j < word.length(); j++) {
				alphabet[word.charAt(j) - 'A'] += (Math.pow(10, word.length() - j - 1));
			}

		}

		Arrays.sort(alphabet);

		num = 9;
		result = 0;
		for (int i = 25; i >= 0; i--) {
			if (alphabet[i] == 0)
				break;
			result += alphabet[i] * num;
			num--;
		}

		System.out.println(result);
	}

}
