package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class SW1926_간단한369게임 {
	static int ans, N;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			String str = Integer.toString(i);
			String tmp = "";
			for (char c : str.toCharArray()) {
				if (c == '3' || c == '6' || c == '9') tmp += '-';
			}

			if (tmp.length() >= 1) System.out.print(tmp + " ");
			else System.out.print(str + " ");
		}

	} // end main
}
