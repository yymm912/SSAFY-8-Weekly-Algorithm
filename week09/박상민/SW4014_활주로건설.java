import java.io.*;
import java.util.*;

public class SW4014_활주로건설 {

    static int T, N, X, answer;
    static int[][] rowMap, colMap;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            rowMap = new int[N][N];
            colMap = new int[N][N];
            for (int y = 0; y < N; y++){
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++){
                    int num = Integer.parseInt(st.nextToken());
                    rowMap[y][x] = num;
                    colMap[x][y] = num;
                }
            }

            answer = 0;
            solve(rowMap);
            solve(colMap);

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void solve(int[][] map){
LOOP:   for (int y = 0; y < N; y++){

            v = new boolean[N];

            for (int x = 1; x < N; x++){
                int front = map[y][x - 1];
                int rear = map[y][x];

                // 평지이면 다음 칸으로 이동
                if (front == rear) continue;
                // 높이가 1을 넘어가면 다음줄로
                if (Math.abs(front - rear) > 1) continue LOOP;
                // 내리막 + 오른쪽으로 X칸 만큼 비어있지 않을 경우 다음줄로
                else if (front > rear && !check(y, x - 1, 1, map[y][x], map)) continue LOOP;
                // 오르막 + 왼쪽으로 X칸 만큼 비어있지 않을 경우 다음줄로
                else if (front < rear && !check(y, x, -1, map[y][x - 1], map)) continue LOOP;
            }
            // 여기까지 온 경우는 경사로를 다 놓고 길이 이어져있는 경우이기 때문에 answer++
            answer++;
        }
    }

    static boolean check(int y, int x, int d, int h, int[][] map){
        for (int i = 1; i <= X; i++){
            int ny = y;
            int nx = x + i * d;

            if (nx < 0 || nx >= N) return false;
            if (map[ny][nx] != h) return false;
            if (v[nx]) return false;
        }

        for (int i = 1; i <= X; i++){
            int nx = x + i * d;
            v[nx] = true;
        }
        return true;
    }
}