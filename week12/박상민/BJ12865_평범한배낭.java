import java.io.*;
import java.util.*;

public class BJ12865_평범한배낭 {

    static int N, K;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        dp = new int[N + 1][K + 1];     // 0 dummy

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());   // 물건의 무게
            arr[i][1] = Integer.parseInt(st.nextToken());   // 물건의 가치
        }
        for (int i = 1; i <= N; i++){           // 물건들
            for (int j = 1; j <= K; j++){       // 무게
                int nw = j - arr[i - 1][0];     // 현재 고려중인 물건의 무게를 뺀 값
                dp[i][j] = dp[i - 1][j];        // 바로 위에 위치한 물건의 가치를 넣어주고
                if (nw >= 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][nw] + arr[i - 1][1]);      // 다른 경우의 수와 비교해서 큰 값으로 갱신
            }
        }
        
        System.out.println(dp[N][K]);
        br.close();
    }
}