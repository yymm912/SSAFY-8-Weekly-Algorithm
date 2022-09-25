import java.io.*;

public class BJ1463_1로만들기 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for (int i = 2; i <= N; i++){
            dp[i] = dp[i - 1] + 1;          // -1 은 그냥 + 1 해줌
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1); // / 3 했을경우랑 비교했을 때 더 작은거 넣음
            }
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1); // / 2 했을 경우랑 비교했을 때 더 작은거 넣음
            }

            // 결국 -1, / 2, / 3 했을 경우중에 가장 작은거 넣으면서 계속 진행 (가능한 경우만)
        }

        System.out.println(dp[N]);
        br.close();
    }
}