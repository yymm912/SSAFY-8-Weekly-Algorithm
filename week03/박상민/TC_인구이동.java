import java.io.*;
import java.util.*;

public class TC_인구이동 {
    
    static int N, L, R;
    static int[][] map;
    static boolean[][] v;
    static int team;
    static int answer = 0;
    static List<P> arr;

    static int[] py = {0, 0, -1, 1};
    static int[] px = {-1, 1, 0, 0};
    static class P{
        int y;
        int x;
        int next;
        public P(int y, int x){
            this.y = y;
            this.x = x;
        }

        public P(int y, int x, int next){
            this.y = y;
            this.x = x;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int y = 0; y < N; y++){
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        arr = new ArrayList<>();

        while(true){
            int city = 0;
            v = new boolean[N][N];
            for (int y = 0; y < N; y++){
                for (int x = 0; x < N; x++){
                    if (v[y][x]) continue;
                    city++;
                    BFS(y, x);
                }
            }   // 연합이 나오면 (연합이 나오지 않고 도시 하나만 나올 수 있다.)
            for (P a : arr) map[a.y][a.x] = a.next;     // 방문한 도시의 수정을 한번에 함
            arr.clear();                                // 수정끝난 도시의 리스트를 초기화

            if (city == N * N) break;       // 모든 도시를 하나씩 다 탐색했을 때 (연합이 없을 때 = 수정이 필요하지 않음)
            answer++;                       // 하루 + 
        }

        System.out.println(answer);
        br.close();
    }

    static void BFS(int y, int x){
        Queue<P> q = new ArrayDeque<>();
        q.add(new P(y, x));
        v[y][x] = true;

        List<P> temp = new ArrayList<>();       // 방문한 도시 임시저장
        temp.add(new P(y, x));              
        int sum = map[y][x];                    // 방문한 도시의 인구수의 합을 저장할 변수

        while(!q.isEmpty()){
            P now = q.poll();

            for (int i = 0; i < 4; i++){
                int nextY = now.y + py[i];
                int nextX = now.x + px[i];

                if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N) continue;
                if (v[nextY][nextX]) continue;

                int sub = Math.abs(map[now.y][now.x] - map[nextY][nextX]);
                if (L > sub || sub > R) continue;
                // 탐색 후 인구수 차이 조건까지 만족하면
                q.add(new P(nextY, nextX));     // 다음탐색을 위해 Queue에 저장
                v[nextY][nextX] = true;                
                temp.add(new P(nextY, nextX));  // 방문한 도시 임시저장
                sum += map[nextY][nextX];       // 방문한 도시 인구수의 합 저장
            }
        }
        sum /= temp.size();                             // 방문한 도시 (연결된 도시들의 합에 연결된 도시의 수를 나눔)
        for (P t : temp) arr.add(new P(t.y, t.x, sum)); // 연결된 도시들의 다음 값들을 arr 리스트에 저장 (한번에 수정해야되기 때문에 모아서 한번에 수정)
    }
}
