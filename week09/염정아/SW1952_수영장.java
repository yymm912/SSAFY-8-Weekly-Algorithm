package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SW1952_수영장 {
	static int ans, T;
	static int[] pays, days;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 초기화
			ans = Integer.MAX_VALUE;
			pays = new int[4];
			days = new int[13]; // 0 dummy

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) pays[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) days[i] = Integer.parseInt(st.nextToken());

			dfs(1, 0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb.toString());
	} // end main


	private static void dfs(int dep, int sum) {
		// 기저 조건
		if (dep > 12) {
			// complete code
			ans = Math.min(ans, sum);
			return;
		}

		dfs(dep + 1, sum + pays[0] * days[dep]);
		dfs(dep + 1, sum + pays[1]);
		dfs(dep + 3, sum + pays[2]);
		dfs(dep + 12, sum + pays[3]);
	} // end dfs

}
