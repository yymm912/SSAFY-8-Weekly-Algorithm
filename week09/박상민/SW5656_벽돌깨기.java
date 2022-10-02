import java.io.*;
import java.util.*;

public class SW5656_벽돌깨기 {

    // 중복순열
    static int T, N, W, H, answer;
    static int[][] src;
    static int[][] map;
    static int[] srcTop;
    static int[] top;
    static int[] tgt;
    static int[] dy = { 0, 0, -1, 1 };
    static int[] dx = { -1, 1, 0, 0 };

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            
            src = new int[H][W];
            srcTop = new int[W];
            Arrays.fill(srcTop, H);

            for (int y = 0; y < H; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < W; x++) {
                    src[y][x] = Integer.parseInt(st.nextToken());
                    if (src[y][x] > 0) {
                        srcTop[x] = Math.min(srcTop[x], y);
                    }
                }
            }
            
            answer = Integer.MAX_VALUE;
            tgt = new int[N];
            solve(0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }
    
    static void solve(int tgtIdx) {
        if (tgtIdx == N) {            
            map = new int[H][W];
            top = new int[W];
            copy(map, top);

            for (int x : tgt) {
                if (top[x] == H) continue;
                remove(top[x], x, map[top[x]][x]);
                sort();
            }

            answer = Math.min(answer, counting());
            return;
        }
        for (int i = 0; i < W; i++) {
            tgt[tgtIdx] = i;
            solve(tgtIdx + 1);
        }
    }

    static void copy(int[][] map, int[] top) {
        for (int x = 0; x < W; x++){
            for (int y = 0; y < H; y++){
                map[y][x] = src[y][x];
            }
            top[x] = srcTop[x];
        }
    }
    
    
    static void remove(int y, int x, int p) {
        map[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            
            for (int j = 1; j < p; j++) {
                int ny = y + dy[i] * j;
                int nx = x + dx[i] * j;

                if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;

                remove(ny, nx, map[ny][nx]);
            }
        }
    }

    static void sort(){
        Stack<Integer> stack = new Stack<>();
        for (int x = 0; x < W; x++){
            for (int y = 0; y < H; y++){
                if (map[y][x] != 0) {           // 1  11 1
                    stack.push(map[y][x]);
                    map[y][x] = 0;
                }
            }

            int y = H;
            while(!stack.isEmpty()){
                map[--y][x] = stack.pop();
            }
            top[x] = y;
        }
    }
    
    static int counting() {
        int count = 0;
        for (int i = 0; i < W; i++) {
            count += (H - top[i]);
        }
        return count;
    }
}
