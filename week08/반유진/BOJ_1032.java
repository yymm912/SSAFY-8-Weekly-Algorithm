// BOJ 1032번 명령 프롬프트 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_1032 {

	static int N;
	static String[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}

		int len = arr[0].length();

		char[] result = new char[len];

		for (int idx = 0; idx < len; idx++) {
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (arr[i].charAt(idx) != arr[j].charAt(idx)) {
						result[idx] = '?';
						break;
					}
				}
				if (result[idx] != '?') {
					result[idx] = arr[i].charAt(idx);
				}
			}
		}

		System.out.println(result);
	}
}