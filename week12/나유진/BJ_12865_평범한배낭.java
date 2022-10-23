package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {
	static int N, K;
	static int[][] arr;
	static int[][] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][K + 1]; // 0 dummy
		list = new int[N + 1][2];// 0 dummy

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (list[i][0] <= j)
					arr[i][j] = Math.max(arr[i - 1][j], arr[i - 1][j - list[i][0]] + list[i][1]);
				else
					arr[i][j] = arr[i - 1][j];
			}
		}

		System.out.println(arr[N][K]);
	}
}
