package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class BJ1062_가르침 {
	static int ans, N, K;
	static String[] arr; // 읽어야하는 단어들
	static Set<Character> chs = new HashSet<>(); // 새롭게 배울 수 있는 알파벳 배열
	static boolean[] alpha = new boolean[26]; // 알파벳 visit 배열


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		alpha['a' - 'a'] = true;
		alpha['n' - 'a'] = true;
		alpha['t' - 'a'] = true;
		alpha['i' - 'a'] = true;
		alpha['c' - 'a'] = true;

		arr = new String[N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			line = line.substring(4, line.length()); // anta 제거
			line = line.substring(0, line.length() - 4); // tica 제거
			arr[i] = line;
		}

		// 완전탐색
		if (K < 5) ans = 0;
		else if (K == 26) ans = N;
		else comb(0, 0);

		// 출력
		System.out.println(ans);

	} // end main


	private static void comb(int dep, int idx) {
		if (dep == K - 5) {
			ans = Math.max(ans, check());
			return;
		}

		for (int i = idx; i < 26; i++) {
			if (alpha[i]) continue;

			alpha[i] = true;
			comb(dep + 1, i + 1);
			alpha[i] = false;
		}

	} // end dfs


	// 각 단어의 알파벳이 새롭게 배운 알파벳이 아니라면 false
	private static int check() {
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			boolean flag = true;

			for (char c : arr[i].toCharArray()) {
				if (!alpha[c - 'a']) {
					flag = false;
					break;
				}

			}

			if (flag) cnt++;

		}

		return cnt;
	} // end check

}
