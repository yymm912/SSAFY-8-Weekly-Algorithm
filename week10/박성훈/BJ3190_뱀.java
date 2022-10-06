import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3190 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, K, L, time = 0;
    static int[][] board;
    static snake first,end;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};


    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        first = new snake(0,0,0);
        end = new snake(0,0,0);
        board[0][0] = 2;
        boolean possible = true;
        for(int k = 0; k < K; k++){
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 6;
        }
        L = Integer.parseInt(br.readLine());
        for(int d = 0; d < L; d++){
            st = new StringTokenizer(br.readLine());
            if(!sol(Integer.parseInt(st.nextToken()), st.nextToken())){
                possible = false;
                break;
            }
        }
        if(possible){
            sol(Integer.MAX_VALUE, "L");
        }
    }
    static boolean sol(int t, String dir){

        while(time < t){

            time++;
            int nx = first.x + dx[first.d];
            int ny = first.y + dy[first.d];
            if(0 <= nx && nx < N && 0<= ny && ny < N && (board[nx][ny] == 0 || board[nx][ny] == 6)){

                first.x = nx;
                first.y = ny;
                if(board[nx][ny] != 6){
                    int d = board[end.x][end.y];
                    board[end.x][end.y] = 0;
                    end.x += dx[d - 2];
                    end.y += dy[d - 2];
                }

                board[nx][ny] = first.d+2;

                continue;
            }

            return false;
        }



        if(dir.equals("D")) first.d = (first.d + 1)%4;
        else {
            first.d = (first.d - 1);
            if(first.d == -1) first.d = 3;
        }
        board[first.x][first.y] = first.d+2;
        return true;

    }
    public static void main(String[] args) throws IOException {
        input();
        System.out.println(time);
    }

    static class snake{
        int x, y, d;

        public snake(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
