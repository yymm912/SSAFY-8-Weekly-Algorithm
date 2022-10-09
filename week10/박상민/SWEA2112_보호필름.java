import java.io.*;
import java.util.*;

public class SWEA2112_보호필름 {

    static int T, D, W, K, answer;
    static int[][] src, map;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            src = new int[D][W];
            map = new int[D][W];
            for (int y = 0; y < D; y++){
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < W; x++){
                    src[y][x] = Integer.parseInt(st.nextToken());
                    map[y][x] = src[y][x];
                }
            }

            v = new boolean[D];
            answer = Integer.MAX_VALUE;
            solve(0, 0);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void solve(int start, int count){
        if (K == 1) {
            answer = 0;
            return;
        }
        if (count >= answer) return;
        if (check()) {
            answer = Math.min(answer, count);
            return;
        }

        for (int idx = start; idx < D; idx++){
            Arrays.fill(map[idx], 0);
            solve(idx + 1, count + 1);
            Arrays.fill(map[idx], 1);
            solve(idx + 1, count + 1);
            rollback(idx);
        }
    }

    static void rollback(int y){
        for (int x = 0; x < W; x++){
            map[y][x] = src[y][x];
        }
    }

    static boolean check(){
        for (int x = 0; x < W; x++){
            int now = -1;
            int cnt = 0;
            for (int y = 0; y < D; y++){
                if (map[y][x] != now){
                    now = map[y][x];
                    cnt = 1;
                } else {
                    cnt++;
                    if (cnt == K) break;
                }
            }
            if (cnt < K) return false;
        }
        return true;
    }
}
