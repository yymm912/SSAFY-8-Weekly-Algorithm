package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2293_동전1 {
	static int N, K;
	static int dp[], num[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new int[K + 1];
		num = new int[N];
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(num);

		for (int i = 0; i < N; i++) {
			for (int j = num[i]; j <= K; j++) {
				if (num[i] == j)
					dp[num[i]] += 1;
				else
					dp[j] += dp[j - num[i]];
			}
		}

		System.out.println(dp[K]);
	}
}
