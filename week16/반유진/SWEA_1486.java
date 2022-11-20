// SWEA 1486번 장훈이의 높은 선반 

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1486 {

	static int T, N, B, min, ans;
	static int[] height; // src
	static boolean[] select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			height = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}

			min = Integer.MAX_VALUE;
			ans = 0;

			select = new boolean[N];
			subset(0);


			System.out.println("#" + t + " " + ans);
		}
	}

	static void subset(int srcIdx) {
		if (srcIdx == N) {
			check();
			ans = min - B;

			return;
		}

		select[srcIdx] = true;
		subset(srcIdx + 1);
		select[srcIdx] = false;
		subset(srcIdx + 1);
	}

	static int check() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (select[i]) {
				sum += height[i];
			}
		}

		if (sum >= B)
			min = Math.min(min, sum);

		return min;
	}
}
