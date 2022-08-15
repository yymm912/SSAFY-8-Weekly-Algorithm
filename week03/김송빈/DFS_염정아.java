package basic;

public class DFSProblem {
    // dfs 를 이용해서 1이 들어간 수와 갯수를 찾아주세요.
    // ?? 안에 뭐가 들어가야할까요?
    static int[][] map = {
                    { 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 11, 12, 13, 14, 15, 16 },
                    { 0, 21, 22, 23, 24, 25, 26 },
                    { 0, 31, 32, 33, 34, 35, 36 },
                    { 0, 41, 42, 43, 44, 45, 46 },
                    { 0, 51, 52, 53, 54, 55, 56 },
                    { 0, 61, 62, 63, 64, 65, 66 },
    };

    static boolean[][] visit;
    static int N = 7, COUNT;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };


    private static void dfs(int y, int x) {
        visit[y][x] = true;
        
        System.out.println(y + " " + x);

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (ny < 1 || ny >= N || nx < 0 || nx >= N || visit[ny][nx] || ny != 1 || nx !=1 )
                continue;

            dfs(ny, nx);
        }
    }


    public static void main(String[] args) {

        visit = new boolean[N][N];

        for (int y = 1; y < N; y++) {
            for (int x = 1; x < N; x++) {
                int num10 = map[y][x] / 10;
                int num1 = map[y][x] % 10;

                if (num10 == 1 || num1 == 1) {
                    dfs(y, x);
                    COUNT++;
                }
            }
        }

        System.out.println("COUNT: " + COUNT);

    }
}
