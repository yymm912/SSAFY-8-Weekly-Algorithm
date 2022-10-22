import java.io.*;
import java.util.*;

public class BJ2293_동전1 {

    static int N, K, max, answer;
    static int[] dp;
    static int[] coin;

/*

dp[k] = dp[k] + dp[k - coin[i]]

#1 coin[1] -> 1
1  1 (1)
2  1 (1, 1)
3  1 (1, 1, 1)
4  1 (1, 1, 1, 1)
5  1 (1, 1, 1, 1, 1)
6  1 (1, 1, 1, 1, 1, 1)
7  1 (1, 1, 1, 1, 1, 1, 1)
8  1 (1, 1, 1, 1, 1, 1, 1, 1)
9  1 (1, 1, 1, 1, 1, 1, 1, 1, 1)
10 1 (1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

dp[k] = dp[k] + dp[k - 1]
k 0 1 2 3 4 5 6 7 8 9 10
  1 1 1 1 1 1 1 1 1 1 1

#2 coin[2] -> 2
1  1 (1)
2  2 (1, 1), (2)
3  2 (1, 1, 1), (1, 2)
4  3 (1, 1, 1, 1), (1, 1, 2), (2, 2)
5  3 (1, 1, 1, 1, 1), (1, 1, 1, 2), (1, 2, 2)
6  4 (1, 1, 1, 1, 1, 1), (1, 1, 1, 1, 2), (1, 1, 2, 2), (2, 2, 2) 
7  4 (1, 1, 1, 1, 1, 1, 1), (1, 1, 1, 1, 1, 2), (1, 1, 1, 2, 2), (1, 2, 2, 2)
8  5 (1, 1, 1, 1, 1, 1, 1, 1), (1, 1, 1, 1, 1, 1, 2), (1, 1, 1, 1, 2, 2), (1, 1, 2, 2, 2), (2, 2, 2, 2)
9  5 (1, 1, 1, 1, 1, 1, 1, 1, 1), (1, 1, 1, 1, 1, 1, 1, 2), (1, 1, 1, 1, 1, 2, 2), (1, 1, 1, 2, 2, 2), (1, 2, 2, 2, 2)
10 6 (1, 1, 1, 1, 1, 1, 1, 1, 1, 1), (1, 1, 1, 1, 1, 1, 1, 1, 2), (1, 1, 1, 1, 1, 1, 2, 2), (1, 1, 1, 1, 2, 2, 2), (1, 1, 2, 2, 2, 2), (2, 2, 2, 2, 2)

dp[k] = dp[k] + dp[k - 2]
k 0 1 2 3 4 5 6 7 8 9 10
  1 1 2 2 3 3 4 4 5 5 6

.
.
.

*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N + 1];
        for (int i = 1; i <= N; i++) coin[i] = Integer.parseInt(br.readLine());

        dp = new int[K + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++){
            for (int j = coin[i]; j <= K; j++){
                dp[j] += dp[j - coin[i]];
            }
        }

        System.out.println(dp[K]);
        br.close();
    }
}
