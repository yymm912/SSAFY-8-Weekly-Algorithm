package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ18405_경쟁적전염 {
    private static int n,k;
    private static int[][] map;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static int s,x,y;

    private static ArrayList<Spread> virusList = new ArrayList<>();
    private static Queue<Spread> virus = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0) {
                    virusList.add(new Spread(map[i][j], 0, i, j));
                }
            }
        }

        Collections.sort(virusList);
        for (int i=0; i<virusList.size(); i++) {
            virus.offer(virusList.get(i));
        }

        st = new StringTokenizer(reader.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(map[x][y]);
    }

    private static void bfs() {

        while (!virus.isEmpty()) {

            Spread spread = virus.poll();
            int qn = spread.num;
            int qs = spread.second;
            int qx = spread.x;
            int qy = spread.y;

            if (qs == s) break;

            for (int i=0; i<dx.length; i++) {
                int nx = qx + dx[i];
                int ny = qy + dy[i];

                if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] != 0) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    map[nx][ny] = qn;
                    virus.add(new Spread(qn, qs+1, nx, ny));
                }
            }

        }

    }

    static class Spread implements Comparable<Spread>{
        int x, y, second, num;
        public Spread(int num, int second, int x, int y) {
            this.num = num;
            this.second = second;
            this.x = x;
            this.y = y;
        }

        //중요!
        //이것이 있어야 객체 정렬 가능
        @Override
        public int compareTo(Spread other) {
            if (this.num < other.num) {
                return -1;
            }
            return 1;
        }
    }
}