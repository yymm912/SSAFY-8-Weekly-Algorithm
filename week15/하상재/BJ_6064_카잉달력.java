package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_6064_카잉달력 {

	static int T, answer;
	static int[] arr, DP, sum;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean check = false;
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			for (int i = x; i < (N * M); i += M) {
				if (i % N == y) {
					sb.append(i + 1).append("\n");
					check = true;
					break;
				}
			}

			if (!check) {
				sb.append(-1).append("\n");
			}

		}
		System.out.println(sb);
	}
	// 잘 안풀려서 구글링 함
}