// BOJ 1527번 금민수의 개수

package BOJ.Bruteforcing_Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1527 {

	static long A, B, cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		gold(4);
		gold(7);

		System.out.println(cnt);
	}

	static void gold(long n) {
		if (n > B) {
			return;
		} else if (n >= A) {
			cnt++;
		}

		gold(n * 10 + 4);
		gold(n * 10 + 7);
	}
}