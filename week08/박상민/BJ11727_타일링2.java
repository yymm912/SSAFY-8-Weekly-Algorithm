import java.io.*;

public class BJ11727_타일링2 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 5;

        for (int i = 4; i <= N; i++){
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10_007;
        }

        System.out.println(dp[N]);
        br.close();
    }
}