import java.io.*;
import java.util.*;

// 11% 터짐
public class BJ17825_주사위윷놀이 {

    static int answer;
    static int[] arr;
    static H[] horse;
    static boolean[][] v;
    static int[][] next;
    static boolean[] dead;

    static class H {
        int n;
        int d;
        public H (int n, int d){
            this.n = n;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 이렇게 까지 해야되나
        next = new int[4][42];
        for (int n = 2, i = 0; n <= 40; n += 2, i += 2) next[0][i] = n;
        next[0][40] = 41;

        next[1][10] = 13;
        next[1][13] = 16;
        next[1][16] = 19;
        next[1][19] = 25;
        next[1][25] = 30;
        next[1][30] = 35;
        next[1][35] = 40;
        next[1][40] = 41;

        next[2][20] = 22;
        next[2][22] = 24;
        next[2][24] = 25;
        next[2][25] = 30;
        next[2][30] = 35;
        next[2][35] = 40;
        next[2][40] = 41;

        next[3][30] = 28;
        next[3][28] = 27;
        next[3][27] = 26;
        next[3][26] = 25;
        next[3][25] = 30;
        next[3][30] = 35;
        next[3][35] = 40;
        next[3][40] = 41;

        arr = new int[10];
        for (int i = 0; i < 10; i++) arr[i] = Integer.parseInt(st.nextToken());

        answer = 0;
        horse = new H[4];
        dead = new boolean[4];
        v = new boolean[4][41];
        for (int i = 0; i < 4; i++) horse[i] = new H(0, 0);
        solve(0, 0);

        System.out.println(answer);
        br.close();
    }

    static void solve(int depth, int score){
        if (depth == 10){
            answer = Math.max(answer, score);
            return;
        }

        for (int i = 0; i < 4; i++){
            if (dead[i]) continue;

            H now = horse[i];

            int nextN = nextPosition(now.n, now.d, arr[depth]);
            int nextD = now.d;

            // 종점에 꼬라박았을 경우
            if (nextN == 41) {
                dead[i] = true;
                back(now.d, now.n);
                solve(depth + 1, score);
                go(now.d, now.n); 
                dead[i] = false;
            } 
            // 종점이 아닌 경우
            else {
                if (nextN == 10) nextD = 1;
                else if (nextN == 20) nextD = 2;
                else if (nextN == 30 && now.d == 0) nextD = 3;

                if (v[nextD][nextN]) continue;

                back(now.d, now.n);
                go(nextD, nextN);
                horse[i] = new H(nextN, nextD);     // 다음위치로 초기화
                solve(depth + 1, score + nextN);   
                horse[i] = new H(now.n, now.d);      
                back(nextD, nextN);
                go(now.d, now.n); 
            }

        }
    }

    static int nextPosition(int n, int d, int c){
        int now = n;
        for (int i = 0; i < c; i++){
            now = next[d][now];
            if (now == 41) return now;
        }
        return now;
    }

    static void go(int nextD, int nextN){
        if (nextN == 25 || nextN == 40){
            for (int i = 0; i < 4; i++) v[i][nextN] = true;
        } else v[nextD][nextN] = true;

    }
    static void back(int nextD, int nextN){
        if (nextN == 25 || nextN == 40){
            for (int i = 0; i < 4; i++) v[i][nextN] = false;
        } else v[nextD][nextN] = false;
    }
}
