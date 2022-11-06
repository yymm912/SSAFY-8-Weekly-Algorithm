package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class SW2007_패턴마디의길이 {
	static int ans, T;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 초기화
			ans = Integer.MAX_VALUE;
			String str = br.readLine();

			for (int i = 2; i < 15; i++) {
				String tmp = str.substring(0, i); // 현재 문자와
				String tmpCT = str.substring(i, i + i); // 현재 문자의 길이 그대로의 다음 문자를 비교

				if (tmp.equals(tmpCT)) ans = Math.min(ans, i);
			}

			// 출력
			System.out.println("#" + t + " " + ans);

		}

	} // end main
}


