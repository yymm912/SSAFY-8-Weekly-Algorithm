package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17070_파이프_옮기기_1 {


    static int N, answer;
    static int[][] map;
    //3방향 델타 선언
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 1);    //
        System.out.println(answer);
    }

    public static void dfs(int status, int x, int y) {
        //기저 조건 목적지에 도달하면 카운트를 세어줌
        if(x == N - 1 && y == N - 1) {
            answer++;
            return;
        }

        switch(status) {
            case 0:
                // 가로일때 이동
                for(int i = 0; i < 2; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if(i == 0 && able(nx, ny)) dfs(i, nx, ny);
                    if(i == 1 && checkDiagonal(nx, ny)) dfs(i, nx, ny);
                }
                break;
            case 1:
                // 대각일때 이동
                for(int i = 0; i < 3; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if(i == 0 && able(nx, ny)) dfs(i, nx, ny);
                    if(i == 1 && checkDiagonal(nx, ny)) dfs(i, nx, ny);
                    if(i == 2 && able(nx, ny)) dfs(i, nx, ny);
                }
                break;
            case 2:
                // 세로일때 이동
                for(int i = 1; i < 3; i++) {
                    int nx = x + dx[i], ny = y + dy[i];
                    if(i == 1 && checkDiagonal(nx, ny)) dfs(i, nx, ny);
                    if(i == 2 && able(nx, ny)) dfs(i, nx, ny);
                }
                break;
        }
    }

    public static boolean able(int x, int y) {
        if(0 <= y && y < N && 0 <= x && x < N && map[x][y] == 0) return true;
        else return false;
    }

    public static boolean checkDiagonal(int x, int y) {
        if(able(x -1, y) && able(x, y) && able(x, y - 1)) return true;
        else return false;
    }
}