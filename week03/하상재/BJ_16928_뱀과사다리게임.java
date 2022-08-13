import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16928_뱀과사다리게임 {

    static int N,M;
    static Integer[] board;
    static int[][] ladder;
    static int[][] snake;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new Integer[101];
        board[1] = 0;
        ladder = new int[N][2];
        snake = new int[M][2];


        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }

        bfs();

        System.out.println(board[100]);


    }

    static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(1);

        while(board[100]==null) {
            int start = que.poll();
            for(int i=1; i<=6; i++) {
                if(start+i>100) continue;

                if(board[start+i]!=null && board[start+i] <= board[start]+1) continue;

                board[start+i] = board[start]+1;

                int warp = ladderCheck(start+i);
                if(warp!=-1) {

                    if(board[warp]!=null) board[warp] = Math.min(board[warp], board[start]+1);
                    else board[warp] = board[start]+1;
                    que.offer(warp);
                    continue;
                }
                warp = snakeCheck(start+i);
                if(warp!=-1) {

                    if(board[warp]!=null) board[warp] = Math.min(board[warp], board[start]+1);
                    else board[warp] = board[start]+1;

                    que.offer(warp);
                    continue;
                }

                que.offer(start+i);
            }
        }


    }

    static int ladderCheck(int start) {

        for(int i=0; i<N; i++) {
            if(ladder[i][0]==start) return ladder[i][1];
        }

        return -1;
    }

    static int snakeCheck(int start) {

        for(int i=0; i<M; i++) {
            if(snake[i][0]==start) return snake[i][1];
        }

        return -1;
    }

}