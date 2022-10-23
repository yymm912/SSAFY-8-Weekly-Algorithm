package BOJ.완전탐색.사탕게임_3085;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 번호 : 3085
// 난이도 : 실버 3
// 제목 : 사탕 게임
// https://www.acmicpc.net/problem/3085
public class Main {
    static int N;
    static char[][] map;
    static int max = Integer.MIN_VALUE;
    static boolean[][] visit;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visit = new boolean[N][N];
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = line.charAt(j);
            }
        }

        pro();

        System.out.println(max);
    }

    static void pro(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                char swap = map[i][j];
                map[i][j] = map[i][j+1];
                map[i][j+1] = swap;

                search();
                // 원상 복구
                swap = map[i][j];
                map[i][j] = map[i][j+1];
                map[i][j+1] = swap;
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N-1;j++){
                char swap = map[j][i];
                map[j][i] = map[j+1][i];
                map[j+1][i] = swap;

                search();
                // 원상 복구
                swap = map[j][i];
                map[j][i] = map[j+1][i];
                map[j+1][i] = swap;
            }
        }
    }

    static void search(){
        // 가로
        for(int i=0;i<N;i++){
            int cnt = 1;
            for(int j=0;j<N-1;j++){
                if(map[i][j] == map[i][j+1])cnt++;
                else cnt = 1;
                max = Math.max(max,cnt);
            }
        }

        // 세로
        for(int i=0;i<N;i++){
            int cnt = 1;
            for(int j=0;j<N-1;j++){
                if(map[j][i] == map[j+1][i])cnt++;
                else cnt = 1;
                max = Math.max(max,cnt);
            }
        }
    }
}



