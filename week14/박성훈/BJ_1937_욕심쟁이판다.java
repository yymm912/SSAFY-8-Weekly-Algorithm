import java.io.*;
import java.util.*;

class BJ_1937_욕심쟁이판다 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int n, ans = Integer.MIN_VALUE;
    static int[][] board;
    static int[][] move;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static void input() throws IOException{
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        move = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                ans = Math.max(ans, pro(i,j, 1));
            }
        }



        System.out.println(ans);
    }

    static int pro(int x, int y, int cnt) {
        if(move[x][y] != 0) return move[x][y];
        int s = cnt;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0 <= nx && nx < n && 0<= ny && ny < n && board[x][y] < board[nx][ny]) {

                s = Math.max(s, cnt + pro(nx, ny, cnt));
            }
        }
        return move[x][y] = s;


    }
    public static void main(String[] args) throws IOException {
        input();
    }
}
