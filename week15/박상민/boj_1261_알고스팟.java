import java.io.*;
import java.util.*;

class P implements Comparable<P>{
    int y; 
    int x;
    int w;

    public P(int y, int x, int w){
        this.y = y;
        this.x = x;
        this.w = w;
    }

    @Override
    public int compareTo(P p){
        return this.w - p.w;
    }
}

public class boj_알고스팟_tableMinPark {
    static int[][] map;
    static boolean[][] v;
    static PriorityQueue<P> q;
    static int[] py = {-1, 1, 0, 0};
    static int[] px = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for (int y = 0; y < N; y++){
            String[] s = br.readLine().split("");
            for (int x = 0; x < M; x++){
                map[y][x] = Integer.parseInt(s[x]);
            }
        }

        int answer = Integer.MAX_VALUE;

        q = new PriorityQueue<>();
        q.add(new P(0, 0, 0));
        v[0][0] = true;
        
        while(!q.isEmpty()){
            P p = q.poll();

            if (p.y == N - 1 && p.x == M - 1) answer = answer > p.w ? p.w : answer;

            for (int i = 0; i < 4; i++){
                int nextY = p.y + py[i];
                int nextX = p.x + px[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || v[nextY][nextX]) continue;
    
                q.add(new P(nextY, nextX, map[nextY][nextX] == 1 ? p.w + 1 : p.w));
                v[nextY][nextX] = true;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.flush();
        bw.close();
    }
}