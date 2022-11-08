package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class SW2005_파스칼의삼각형 {
	static int T, N;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			System.out.println("#" + t);
			solve();

		} // end tc

	} // end main


	private static void solve() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) System.out.print(1 + " ");
				else System.out.print(i + " ");
			}

			for (int j = N - i; j >= 0; j--) System.out.print(" ");

			System.out.println();
		}

	} // end solve
}
