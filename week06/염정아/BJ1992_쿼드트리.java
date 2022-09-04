package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class BJ1992_쿼드트리 {
	static int N;
	static String ans = "";
	static char[][] map;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		dfs(0, 0, N);

		System.out.println(ans);

	} // end main


	private static void dfs(int y, int x, int n) {
		if (isPossible(y, x, n)) {
			ans += map[y][x];
			return;
		}

		int newN = n / 2;

		ans += "(";

		dfs(y, x, newN);
		dfs(y, x + newN, newN);
		dfs(y + newN, x, newN);
		dfs(y + newN, x + newN, newN);

		ans += ")";

	} // end dfs


	private static boolean isPossible(int y, int x, int n) {
		char m = map[y][x];

		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++)
			    if (m != map[i][j]) return false;
		}

		return true;
	} // end isPossible
}
