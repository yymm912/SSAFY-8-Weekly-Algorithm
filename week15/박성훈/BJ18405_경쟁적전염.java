import java.util.*;
import java.io.*;
class BJ18405_경쟁적전염 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K, S, X, Y, board[][], board2[][];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

            }
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X =Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
    }

    static boolean attack() {
        boolean possible = false;
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                board2[x][y] = Math.max(board[x][y], board2[x][y]);
                if(board[x][y] != 0) {
                    for(int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(0 <= nx && nx < N && 0<= ny && ny < N && board[nx][ny] == 0 && (board2[nx][ny] == 0 || board[x][y] < board2[nx][ny] )) {
                            board2[nx][ny] = board[x][y];
                        }
                    }
                }
            }
        }

        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                board[x][y] = board2[x][y];
                if(board[x][y] == 0) possible = true;
            }
        }

        return possible;
    }
    static void sol() {
        for(int i = 0; i < S; i++) {
            board2 = new int[N][N];
            if(!attack()) break;

        }
        System.out.print(board[X-1][Y-1]);


    }
    public static void main(String[] args) throws IOException{
        input();
        sol();

    }
}