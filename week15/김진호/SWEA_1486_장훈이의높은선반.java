import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의높은선반 {
	static int T;
	static int N, B;
	static int[] arr;
	static boolean[] select;
	static int answer;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;

			arr = new int[N];
			select = new boolean[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			subset(0);

			System.out.println("#" + t + " " + answer);
		}
	}

	public static void subset(int srcIdx) {

		if (srcIdx == N) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (select[i]) {
					sum += arr[i];
				}
			}

			if (sum >= B) {
				answer = Math.min(answer, sum - B);
			}

			return;

		}

		select[srcIdx] = true;
		subset(srcIdx + 1);

		select[srcIdx] = false;
		subset(srcIdx + 1);

	}

}
