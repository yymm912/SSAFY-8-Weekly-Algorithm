
public class DFS_이재혁 {

static int RESULT = Integer.MAX_VALUE;
static int[][] map = {
        {0,  0,  0,  0,  0,  0,  0},
        {0, 11, 12, 13, 14, 15, 16},
        {0, 21, 22, 23, 24, 25, 26},
        {0, 31, 32, 33, 34, 35, 36},
        {0, 41, 42, 43, 44, 45, 46},
        {0, 51, 52, 53, 54, 55, 56},
        {0, 61, 62, 63, 64, 65, 66},
 };
static int[] dx = {-1,1,0,0};
static int[] dy = {0,0,-1,1};
static boolean visit[][] = new boolean[7][7];

public static void main(String[] args) {
    dfs(1,1,0);
    System.out.println("DFS : "+ RESULT);
}

static void dfs(int x, int y,int cnt) {
    visit[x][y] = true;//visit
    cnt++;
    
    for(int d=0; d<4; d++) {
        int nx = x+dx[d];
        int ny = y+dy[d];
        if(nx < 1|| ny< 1 || nx>=7 || ny>=7 || visit[nx][ny]) continue;
        if(map[nx][ny]==66) {
            RESULT = Math.min(cnt,RESULT);
        }
        dfs(nx,ny,cnt);
        visit[nx][ny] = false;
    }
}
}

