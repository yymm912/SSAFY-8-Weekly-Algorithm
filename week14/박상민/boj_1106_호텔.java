import java.io.*;
import java.util.*;

// 0-1 냅색
public class boj_호텔_tableMinPark {

    static int C, N, answer;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            arr[i][0] = V;
            arr[i][1] = P;
        }

        dp = new int[C + 101];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= N; i++){
            int nowV = arr[i][0];
            int nowP = arr[i][1];

            for (int j = nowP; j < C + 101; j++){
                if (dp[j - nowP] != Integer.MAX_VALUE) dp[j] = Math.min(dp[j], dp[j - nowP] + nowV);
            }
        }

        answer = Integer.MAX_VALUE;
        for (int i = C; i < C + 100; i++){
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
        br.close();
    }
}