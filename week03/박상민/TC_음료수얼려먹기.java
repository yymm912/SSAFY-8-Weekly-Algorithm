import java.io.*;
import java.util.*;

public class TC_음료수얼려먹기{

    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int answer;
    static int[] py = {0, 0, -1, 1};
    static int[] px = {-1, 1, 0, 0};
    static class P{
        int y;
        int x;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for (int y = 0; y < N; y++){
            char[] input = br.readLine().toCharArray();
            for (int x = 0; x < M; x++){
                map[y][x] = input[x] - '0';
            }
        }

        answer = 0;
        for (int y = 0; y < N; y++){
            for (int x = 0; x < M; x++){
                if (map[y][x] == 1) continue;
                if (v[y][x]) continue;
                BFS(y, x);
            }
        }

        System.out.println(answer);
        br.close();
    }

   

    static void BFS(int y, int x){
        Queue<P> q = new LinkedList<>();

        q.add(new P(y, x));
        v[y][x] = true;

        while(!q.isEmpty()){
            P now = q.poll();

            for (int i = 0; i < 4; i++){
                int nextY = now.y + py[i];
                int nextX = now.x + px[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M) continue;
                if (v[nextY][nextX] || map[nextY][nextX] == 1) continue;

                q.add(new P(nextY, nextX));
                v[nextY][nextX] = true;
            }
        }
        answer++;
    }
}