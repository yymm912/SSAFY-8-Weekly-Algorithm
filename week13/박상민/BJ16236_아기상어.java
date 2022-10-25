import java.io.*;
import java.util.*;

public class BJ16236_아기상어{

    static int N, count, size, sy, sx, answer;
    static int[][] map;
    static PriorityQueue<P> fish;

    static class P{
        int y;
        int x;
        int d;
        public P(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;

        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9) {
                    sy = y;
                    sx = x;
                    map[y][x] = 0;
                }
            }
        }

        answer = 0;
        count = 0;
        size = 2;

        while(bfs(sy, sx)){
            P next = fish.poll();
            answer += next.d;
            sy = next.y;
            sx = next.x;
            count++;
            if (size == count) {
                count = 0;
                size++;
            }
            map[sy][sx] = 0;
        }

        System.out.println(answer);
        br.close();
    }

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static boolean bfs(int y, int x){

        fish = new PriorityQueue<>((p1, p2) -> {
            if (p1.d == p2.d){
                if (p1.y == p2.y){
                    return p1.x - p2.x;
                } else return p1.y - p2.y;
            } else return p1.d - p2.d;
        });

        Queue<P> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];

        q.add(new P(y, x, 0));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            for (int i = 0; i < 4; i++){
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx]) continue;
                if (map[ny][nx] > size) continue;
                if (map[ny][nx] < size && map[ny][nx] != 0) fish.add(new P(ny, nx, now.d + 1));

                q.add(new P(ny, nx, now.d + 1));
                v[ny][nx] = true;
            }

        }

        if (fish.size() == 0) return false;
        else return true;
    }
}