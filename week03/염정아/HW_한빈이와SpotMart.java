


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class HW_한빈이와SpotMart {
	static int T, N, M;
	static int[] snack;
	static int max;
	static StringBuilder sb = new StringBuilder();


	private static void comb(int cnt, int start, int sum) {
		if (sum > M) return;

		if (cnt == 2) {
			max = Math.max(max, sum);
			// System.out.println(max);
			return;
		}

		for (int i = start + 1; i < N; i++) {
			sum += snack[i];

			comb(cnt + 1, i, sum);
			sum -= snack[i];
		}

	}


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// System.out.println(N + " " + M);

			snack = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}

			// System.out.println(Arrays.toString(snack));

			max = -1;
			comb(0, -1, 0);

			sb.append("#" + t + " ").append(max + "\n");
			// break;

		} // end tc

		System.out.println(sb.toString());

	} // end main
}
