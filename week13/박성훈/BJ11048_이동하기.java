import java.io.*;
import java.util.*;
public class BJ11048_이동하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] board, dp;
    static int[] dx = {0,1};
    static int[] dy = {1,0};
    static int N, M;

    static void sol() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N+1][M+1];
        dp = new int[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]) + board[i][j];


            }
        }


        System.out.println(dp[N][M]);
    }

    public static void main(String[] args) throws IOException{
        sol();
    }




}
