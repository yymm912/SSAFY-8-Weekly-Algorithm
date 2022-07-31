import java.io.*;
import java.util.*;

// 바다가 항상 외곽에 위치하는 조건확인
// 왼쪽으로 이동 후 왼쪽을 한번 더 확인
// 반대방향 ( D + 2 ) % 4
// 델타 순서 (북 동 남 서)
public class game{
    static int[] py = {-1, 0, 1, 0};
    static int[] px = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] v;
    static int N, M;
    static int answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        v[Y][X] = true;
        answer = 0;
        int check = 0;

        while (true){
            D = D - 1 < 0 ? 3 : D - 1;  // 왼쪽회전
            int nextY = Y + py[D];
            int nextX = X + px[D];

            // 갈 수 있으면 
            if (map[nextY][nextX] == 0 && !v[nextY][nextX]){
                // 이동
                v[nextY][nextX] = true;
                Y = nextY;
                X = nextX;
                answer++;   // 이동거리 + 1
                check = 0;

                // 왼쪽확인
                int nextD = D - 1 < 0 ? 3 : D - 1;  
                nextY += py[nextD];
                nextX += px[nextD];

                // 왼쪽에 또 갈 수 있으면 방향만 회전
                if (map[nextY][nextX] == 0 || v[nextY][nextX]) D = nextD;
            } else check++;     // 갈 수 없으면 check + 1

            // 4방향 모두 갈 수 없으면
            if (check == 4){
                int nextD = (D + 2) % 4;        // 반대방향 index
                nextY = Y + py[nextD];          // 반대방향 Y
                nextX = X + px[nextD];          // 반대방향 X

                // 뒤칸이 바다라면 중지
                if (map[nextY][nextX] == 1) break;
                else {  // 뒤칸이 바다가 아니면 뒤로이동
                    Y = nextY;  // 반대방향으로 이동
                    X = nextX;
                    answer++;   // 이동거리 + 1
                    check = 0;
                }
            }
        }
        System.out.println(answer);
        br.close();
    }
}