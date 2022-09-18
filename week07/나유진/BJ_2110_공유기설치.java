package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2110_공유기설치 {
	static int N, C;
	static int arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int start = 1; // 최소거리
		int end = arr[N - 1] - arr[0]; // 최대 거리
		int ans = 0;

		while (start <= end) {
			int mid = (start + end) / 2;

			int cnt = 1;
			int pre = arr[0];

			for (int i = 0; i < N; i++) {
				if (arr[i] - pre >= mid) {
					cnt++;
					pre = arr[i];
				}
			}

			if (cnt >= C) {
				ans = Math.max(ans, mid);
				start = mid + 1;
			} else
				end = mid - 1;
		}

		System.out.println(ans);

	}
}
