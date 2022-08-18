import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class TC_인구이동 {

    static int[][] arr;
    static boolean[][] visit;
    static int N,L,R,answer, sum;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean bool;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = -1;
        while(!bool) {
            bool = true;
            visit = new boolean[N][N];
            answer++;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(!visit[i][j]) {
                        sum = 0;
                        bfs(i,j);
                    }
                }
            }
        }

        System.out.println(answer);


    }

    static void bfs(int r, int c) {
        Queue<int[]> que = new ArrayDeque<>();
        Queue<int[]> change = new ArrayDeque<>();
        int cnt = 0;
        que.add(new int[] {r,c});

        while(!que.isEmpty()) {
            int[] rc = que.poll();
            visit[rc[0]][rc[1]] = true;
            int nr=0,nc=0;
            change.add(new int[] {rc[0],rc[1]});
            sum += arr[rc[0]][rc[1]];
            cnt++;

            for(int i=0; i<4; i++) {
                nr = rc[0] + dr[i];
                nc = rc[1] + dc[i];
                if(nr<0 || nc <0 || nr>=N || nc>=N) continue;

                if(visit[nr][nc]) continue;

                if(Math.abs(arr[nr][nc]-arr[rc[0]][rc[1]] )>=L && Math.abs(arr[nr][nc]-arr[rc[0]][rc[1]] ) <= R ) {
                    bool = false;
                    que.add(new int[] { nr, nc});
                    visit[nr][nc] = true;
                }
            }

        }

        sum = sum/cnt;

        while(!change.isEmpty()) {
            int[] rc = change.poll();
            arr[rc[0]][rc[1]] = sum;
        }


    }
}
