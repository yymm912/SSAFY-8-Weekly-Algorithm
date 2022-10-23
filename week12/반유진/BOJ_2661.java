// BOJ 2661번 좋은수열

package BOJ.Backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_2661 {

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		solve("");
	}

	static void solve(String str) {
		if (str.length() == N) {
			System.out.println(str);

			System.exit(0); // 가장 작은 수를 출력한 후 아예 종료
		}

		for (int i = 1; i <= 3; i++) { // 1, 2, 3 숫자 넣어보기
			if (isGood(str + i)) {
				solve(str + i);
			}
		}
	}

	static boolean isGood(String str) {
		int len = str.length();

		for (int i = 1; i <= len / 2; i++) {
			String front = str.substring(len - i - i, len - i);
			String back = str.substring(len - i, len);

			if (front.equals(back)) // 앞과 뒤가 같다면 나쁜 수열
				return false;
		}

		return true;
	}

}
