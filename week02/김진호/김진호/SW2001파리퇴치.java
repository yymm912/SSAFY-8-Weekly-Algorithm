package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SE2001파리퇴치 {
	static int T;
	static int N, M;
	static int arr[][];
	static int max = -1;
	static int sum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N - M + 1; i++) {
				for (int j = 0; j < N - M + 1; j++) {
					sum = 0;
					for (int k = i; k < i + M; k++) {
						for (int l = j; l < j + M; l++) {
							sum += arr[k][l];
						}
					}
					if (max <= sum) {
						max = sum;
					}
				}
			}

			System.out.println("#" + t + " " + max);
			max = -1;

		}

	}

}
