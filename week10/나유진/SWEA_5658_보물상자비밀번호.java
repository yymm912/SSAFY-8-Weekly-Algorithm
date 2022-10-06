package STUDY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5658_보물상자비밀번호 {
	static int T, N, K, len;
	static char[] pw;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			len = N / 4;

			pw = br.readLine().toCharArray();

			int ans = makePassword();

			System.out.println("#" + t + " " + ans);

		}
	}

	private static int makePassword() {
		TreeSet<Integer> keys = new TreeSet<>();

		for (int i = 0; i < len; i++) {
			int cnt = 0;
			String key = "";
			for (int j = N - i; j < N; j++) {
				key += pw[j];
				cnt++;
			}
			for (int j = 0; j < N - i; j++) {
				if (cnt == len) {
					cnt = 0;
					keys.add(Integer.parseInt(key, 16));
					key = "";
				}
				key += pw[j];
				cnt++;
			}
			keys.add(Integer.parseInt(key, 16));
		}

		for (int i = 1; i < K; i++) {
			keys.pollLast();
		}
		return keys.last();
	}
}
