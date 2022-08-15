import java.io.*;
import java.util.*;

public class BJ2206_벽부수고이동하기{

    static int N, M, answer;
    static int[][] map;
    static int[][] dist;    
    static boolean[][] v;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static class P{
        int y;
        int x;
        int d;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
        public P(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }


    // 1. 벽을 출발점으로 종료점 (N, M) 까지 거리를 미리 계산 (BFS 1회)
    // 2. (1, 1) 부터 BFS 탐색하며 벽이 있으면 현재까지의 거리 + 위에서 계산한 (벽을 출발점으로 종료점까지의 거리) 거리를 더해서 최솟값 갱신
    // (벽을 허물고 이동했을 때 거리를 계속해서 최솟값에 갱신해준다.)
    // 3. 마지막 종료점에 도착했을 때 최솟값을 갱신 (벽을 만났을 때 최솟값 갱신 & 종료점에 도착했을 때 최솟값 갱신)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];
        for (int y = 0; y < N; y++){
            char[] input = br.readLine().toCharArray();
            for (int x = 0; x < M; x++){
                map[y][x] = input[x]-'0';
            }
        }

        answer = Integer.MAX_VALUE;

        v = new boolean[N][M];
        bfsWall(N - 1, M - 1);

        for (int y = 0; y < N; y++){
            for (int x = 0; x < M; x++) System.out.print(dist[y][x] + " ");
            System.out.println();
        }

        v = new boolean[N][M];
        bfs(0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        br.close();
    }

    // 벽을 시작점으로 종료점까지 거리
    // 종료점에서 시작해서 미로탐색 bfs 하면서 벽이 있으면 종료점에서 현재까지의 거리 + 1

    /*
    // TestCase1
    6 4
    0 1 0 0
    1 1 1 0
    1 0 0 0 
    0 0 0 0
    0 1 1 1
    0 0 0 0

    0 13 0 0
    0 8 9 0
    6 0 0 0
    0 0 0 0
    0 3 2 1
    0 0 0 0 

    // TestCase2
    6 4
    0 0 0 0
    1 1 1 0
    1 0 0 0
    0 0 0 0
    0 1 1 1
    0 0 0 0
    
    0 0 0 0 
    15 8 9 0
    6 0 0 0
    0 0 0 0
    0 3 2 1
    0 0 0 0
     */

    static void bfsWall(int y, int x){
        Queue<P> q = new ArrayDeque<>();
        q.add(new P(y, x, 1));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            for (int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
                if (v[nextY][nextX]) continue;

                // 도
                if (map[nextY][nextX] == 0){
                    q.add(new P(nextY, nextX, now.d + 1));
                    v[nextY][nextX] = true;
                } else if (dist[nextY][nextX] == 0){
                    dist[nextY][nextX] = now.d;
                }
            }
        }
    }

    static void bfs(int y, int x){
        Queue<P> q = new ArrayDeque<>();
        q.add(new P(y, x, 1));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            if (now.y == N - 1 && now.x == M - 1){
                answer = Math.min(answer, now.d + 1);
            }

            for (int i = 0; i < 4; i++){
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
                if (v[nextY][nextX]) continue;

                if (map[nextY][nextX] == 0){    // 벽이 아닐 때 정상적으로 탐색
                    q.add(new P(nextY, nextX, now.d + 1));
                    v[nextY][nextX] = true;
                } else {                        // 벽일 때 (벽과 종료점까지의 거리 + 현재까지의 거리 = 벽을 뚫었을 때 종료점까지의 거리) 를 최솟값 갱신
                    if (dist[nextY][nextX] == 0) continue;      // dist가 0이면 갈 수 있는 경우의 수가 없기 때문에 최솟값 갱신을 못하게 막아야한다.
                    answer = Math.min(answer, now.d + dist[nextY][nextX] + 1);
                }
            }
        }
    }

}