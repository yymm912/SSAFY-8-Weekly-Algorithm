import java.io.*;

// 점화식 -> dp[i][j] = dp[i - 1][j - 1] + 1 (i번째 문자와 j번째 문자가 같을 경우)
public class BJ5582_공통부분문자열_2 {
    static char[] s1, s2;
    static int[][] dp;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input1 = br.readLine();
        String input2 = br.readLine();
        int N = input1.length();
        int M = input2.length();
        s1 = input1.toCharArray();
        s2 = input2.toCharArray();

        answer = 0;
        dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= M; j++){
                if (s1[i - 1] == s2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.println(answer);
    }
}
