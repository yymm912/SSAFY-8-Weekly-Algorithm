package 박상민;

import java.io.*;
import java.util.*;

public class boj_19238_스타트택시 {

    static int N, M, F, SY, SX, CN;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int[][] map;

    static P[] end;

    static class P implements Comparable<P> {
        int y;
        int x;
        int d;
        public P (int y, int x){
            this.y = y;
            this.x = x;
        }
        public P (int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
        @Override
        public int compareTo(P p) {
            if (this.d == p.d) {
                if (this.y == p.y) {
                    return this.x - p.x;
                }
                return this.y - p.y;
            }
            return this.d - p.d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        SY = Integer.parseInt(st.nextToken()) - 1;
        SX = Integer.parseInt(st.nextToken()) - 1;

        end = new P[M + 2];
        for (int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;

            map[sy][sx] = i + 1;
            end[i + 1] = new P(ey, ex);
        }

        for (int i = 0; i < M; i++){
            // 다음 손님 찾음
            int findF = find(SY, SX);
            if (findF == Integer.MAX_VALUE) {
                F = -1;
                break;
            }

            // 태우고
            map[SY][SX] = 0;
            // 출발
            int goF = go(SY, SX);
            if (goF == Integer.MAX_VALUE || findF + goF > F) {
                F = -1;
                break;
            }
            F -= findF + goF;
            F += goF * 2;
        }

        System.out.println(F);
        br.close();

    }

    // pq 사용
    // 거리가 같으면 행 기준으로 정렬
    // 거리가 짧은 순부터 땡겨옴
    // 손님찾음
    static int find(int y, int x){
        PriorityQueue<P> q = new PriorityQueue<>();
        boolean[][] v = new boolean[N][N];

        q.add(new P(y, x, 0));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            // 가장 가까운 사람
            if (map[now.y][now.x] != 0) {
                SY = now.y;
                SX = now.x;
                CN = map[now.y][now.x];
                return now.d;
            }

            for (int i = 0; i < 4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx] || map[ny][nx] == 1) continue;

                q.add(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }
        }
        return Integer.MAX_VALUE;
    }

    // 일반 bfs
    // 목적지까지감
    static int go(int y, int x){
        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];   

        q.add(new P(y, x, 0));
        v[y][x] = true;

        P e = end[CN];

        while(!q.isEmpty()){
            P now = q.poll();

            if (now.y == e.y && now.x == e.x) {
                SY = now.y;
                SX = now.x;
                return now.d;
            }

            for (int i = 0; i < 4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx] || map[ny][nx] == 1) continue;

                q.add(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }
        }
        return Integer.MAX_VALUE;
    }
}