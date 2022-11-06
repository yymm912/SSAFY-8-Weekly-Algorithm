import java.io.*;
import java.util.*;

public class BJ21608_상어초등학교 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int[][] board;
    static ArrayList<Integer>[] list;
    static PriorityQueue<XY> q = new PriorityQueue<>((o1, o2) -> {
        if(o1.like != o2.like) return o2.like - o1.like;
        if (o1.near != o2.near)
            return o2.near - o1.near;
        if(o1.x != o2.x) return o1.x - o2.x;
        return o1.y - o2.y;
    });
    static int N, NN, com[];

    static void input() throws IOException {

        N = Integer.parseInt(br.readLine());
        NN = N * N;
        list = new ArrayList[NN + 1];
        board = new int[N + 1][N + 1];
        com = new int[NN];
        for (int i = 1; i <= NN; i++) list[i] = new ArrayList<>();
        for (int i = 0; i < NN; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            com[i] = idx;
            for (int j = 0; j < 4; j++) {
                list[idx].add(Integer.parseInt(st.nextToken()));
            }
        }
    }


    static int cal(int x, int y) {
        int num = board[x][y];
        int count=0;

        for(int n : list[num]){
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (0 < nx && nx <= N && 0< ny && ny <= N) {
                    if(board[nx][ny]==n) count++;
                }
            }
        }
        if(count == 0) return 0;
        if(count == 1)return 1;
        if(count == 2) return 10;
        if(count == 3) return 100;
        return 1000;
    }
    static void sol() {

        for (int t = 0; t < NN; t++) {
            int num = com[t];
            for (int x = 1; x <= N; x++) {
                for (int y = 1; y <= N; y++) {
                    int nC = 0; // near
                    int lC = 0; // like
                    if (board[x][y] != 0) {
                        continue;
                    }
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if(0 <nx && nx <= N && 0 < ny && ny <= N) {
                            for (int n : list[num]) if (n == board[nx][ny]) lC++;
                            if (board[nx][ny] == 0) nC++;

                        }
                    }
                    q.add(new XY(x, y, nC, lC));
                }
            }

            XY xy = q.poll();
            board[xy.x][xy.y] = num;
            q.clear();
        }



        int ans=0;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                ans += cal(i,j);
            }
        }
        System.out.println(ans);

    }



    public static void main(String[] args) throws IOException {
        input();
        sol();
    }

    static class XY {
        int x, y, near, like; // x y 근처 수 좋아하는 사람 수

        public XY(int x, int y, int near, int like) {
            this.x = x;
            this.y = y;
            this.near = near;
            this.like = like;
        }


    }
}
