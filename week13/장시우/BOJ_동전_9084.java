package boj;

import java.util.Scanner;

public class BOJ_동전_9084 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for (int tc = 1; tc <= TC; tc++) {
            int N = sc.nextInt();
            int[] coin = new int[N];
            for (int i = 0; i < N; i++) coin[i] = sc.nextInt();
            int M = sc.nextInt();
            int[] dp = new int[M + 1];
            dp[0] = 1;

            for (int i = 0; i < N; i++) {
                for (int j = coin[i]; j <= M; j++) {
                    dp[j] += dp[j - coin[i]];
                }
            }

            System.out.println(dp[M]);
        }
    }
}