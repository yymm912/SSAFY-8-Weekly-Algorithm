import java.io.*;
import java.util.*;

public class BJ10159_저울 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static boolean board[][];


    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        board = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

    }


    static void pro() {
       for(int i = 1; i <= N; i++){
           for (int j = 1; j <= N; j++) {
               for (int k = 1; k <= N; k++) if(board[j][i] && board[i][k]) board[j][k] = true;

           }
       }
        for (int i = 1; i <= N; i++) {
            int cnt = -1;
            for(int j = 1; j<= N; j++){
             if(!(board[i][j] || board[j][i])) cnt++;
            }
            System.out.println(cnt);
        }


    }

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }
}
