package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW1486_장훈이의높은선반 {
	static int ans, T, N, B;
	static int[] arr;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

			// 초기화
			ans = Integer.MAX_VALUE;

			// 부분집합
			subset(0, 0);

			// 출력
			System.out.println("#" + t + " " + (ans - B));

			// break;

		}

	} // end main


	private static void subset(int dep, int sum) {
		if (dep == N) {
			if (sum < B) return;

			ans = Math.min(ans, sum);
			return;
		}

		subset(dep + 1, sum + arr[dep]);
		subset(dep + 1, sum);

	} // end subset
}
