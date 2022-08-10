import java.io.*;
import java.util.*;

public class BFS_DFS_서우린_Solution {
    
    /*
    우린이는 싸피를 마치고 집으로 돌아가려고 합니다.
    N * M 격자 공간에서 멀티 캠퍼스와 우린이의 집이 표시되어 있습니다.
    집까지 이동하려고 하는데 너무 빨리 돌아가고 싶은 나머지 순간이동을 딱 한번 사용하려고 합니다.
    순간 이동은 일직선으로 벽 앞 또는 맵 끝까지 한번에 이동하는 것을 말합니다.
    만약 순간 이동 중에 집을 만나게 된다면 바로 집으로 들어갈 수 있습니다.
    각 칸을 이동하는 데에는 1분이 소요되고 순간이동 시에도 똑같이 1분이 소요됩니다.
    'o'는 멀티 캠퍼스, 'x'는 집, '.' 은 이동 가능한 길, '#' 은 벽일 때 집으로 돌아갈 수 있는 가장 빠른 시간을 구해주세요.
    */

    /* input 입니다
    10 7
    ........o.
    ....#.....
    ..........
    ..........
    .......#..
    ....#.....
    ...x......
     
     */
    
    static int N, M;
    static char[][] map;
    static boolean[][] v;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int ey, ex;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int sy = 0, sx = 0;
        ey = 0;
        ex = 0;

        map = new char[N][M];
        for (int y = 0; y < N; y++){
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < M; x++){
                if (map[y][x] == 'o'){
                    sy = y;
                    sx = x;
                } else if (map[y][x] == 'x'){
                    ey = y;
                    ex = x;
                }
            }
        }

        v = new boolean[N][M];
        v[sy][sx] = true;
        answer = Integer.MAX_VALUE;
        dfs(sy, sx, 0, false);

        System.out.println(answer);        
        br.close();
    }

    static void dfs(int y, int x, int t, boolean f){
        if (t > answer) return;     // 현재 최솟값 보다 크면 답이 될수 없기 때문에 return
        if (y == ey && x == ex){
            answer = Math.min(answer, t);
            return;
        }

        for (int d = 0; d < 4; d++){
            int nextY = y + dy[d];
            int nextX = x + dx[d];
            
            if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= M || v[nextY][nextX]) continue;
            if (map[nextY][nextX] == '#') continue;

            v[nextY][nextX] = true;
            dfs(nextY, nextX, t + 1, f);        // 순간이동 안쓰고 달리기
            v[nextY][nextX] = false;

            if (f) continue;                    // 순간이동 썼으면 패스

            // 순간이동 한번 쓰러왔다
            nextY = y;
            nextX = x;
            while(true){
                int ny = nextY + dy[d];
                int nx = nextX + dx[d];
                if (ny < 0 || ny >= N || nx < 0 || nx >= M || v[ny][nx]) break;
                if (map[ny][nx] == '#') break;
                nextY = ny;
                nextX = nx;
            }
            v[nextY][nextX] = true;
            dfs(nextY, nextX, t + 1, true);     // 순간이동 하기 (1회용)
            v[nextY][nextX] = false;
        }
    }
}