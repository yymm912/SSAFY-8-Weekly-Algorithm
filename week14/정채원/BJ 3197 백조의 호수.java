import java.io.*;
import java.util.*;

public class Main {
    /**
     * 1. 물 부분(bfs) -> union and find로 넘버링함 (빙하부분은 -1, 녹을 곳은 -2, 물 > 0, 방문 안한 부분 0(처음 init 할 때만 쓰임))
     *         1-1. 두 백조의 parent 를 저장해둠.
     *         1-2. 동시에, 처음 빙하가 녹은 부분(bfs) -> queue에 넣음
     * 3. queue를 따라서 빙하 녹은 부분을 
     *         3-1. 녹은 부분의 사방에 호수가 있는지 확인 -> 있으면, parent확인 -> 다르면 union함 (이어짐)
     *         3-2. 백조가 이어진지 확인 -> 안이어졌으면 4번
     * 4. queue에 있는 좌표를 기준으로 사방 확인 -> 다음 빙하를 녹임 (기존 좌표 없, 새 좌표 채움)-> 3번 회귀
     * */
    static int R, C;
    static int[][] parent; // [R][C]
    static char[][] map;
    static int getN(int x, int y) {
        return (y+1)*C+x; // > 0
    }
    static int getX(int n) {
        return n % C;
    }
    static int getY(int n) {
        return (n / C) - 1;
    }
    static int find(int x, int y) {
        if(parent[y][x] == getN(x, y)) return getN(x, y);
        int px = getX(parent[y][x]);
        int py = getY(parent[y][x]);
        return (parent[y][x] = find(px, py));
    }
    static boolean union(int ax, int ay, int bx, int by) {
        int pa = find(ax, ay);
        int pb = find(bx, by);
        if(pa == pb) return false;
        if(pa < pb) {        	
        	int pbx = getX(pb);
        	int pby = getY(pb);
        	parent[pby][pbx] = pa;
        } else {
        	int pax = getX(pa);
        	int pay = getY(pa);
        	parent[pay][pax] = pb;
        	
        }
        return true;
    }
    static void init(int x, int y) {
        int num = getN(x, y);
        
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {x, y});
        parent[y][x] = num;
        while(!q.isEmpty()) {
            int[] cur = q.remove();
            if(map[cur[1]][cur[0]] == 'L') { // 백조의 위치 구함
                if(swan[0][0] == -1) {
                    swan[0][1] = cur[1];
                    swan[0][0] = cur[0];
                }
                else{
                    swan[1][1] = cur[1];
                    swan[1][0] = cur[0];
                }
            }
            
            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx < 0 || nx >= C || ny < 0 || ny >= R || parent[ny][nx] > 0 || parent[ny][nx] == -2) continue;
                if(parent[ny][nx] == -1) {
                    parent[ny][nx] = -2; // 다음 턴에 녹을 부분
                    melt.add(new int[] {nx, ny, num}); // 녹을 곳, parent
                    continue;
                }
                parent[ny][nx] = num;
                q.add(new int[] {nx, ny});
            }
        }
    }
    static void melting(int x, int y, int num) {
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx < 0 || nx >= C || ny < 0 || ny >= R || parent[ny][nx] > 0 || parent[ny][nx] == -2) continue;
            parent[ny][nx] = -2;
            melt.add(new int[] {nx, ny, num}); // 녹을 곳, parent
        }
    }
    static int[][] swan;
    static Queue<int[]> melt;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        melt = new ArrayDeque<>();
        // init
        swan = new int[][] {{-1, -1},{-1,-1}};
        map = new char[R][C];
        parent = new int[R][C];
        for(int i=0; i<R; i++)    {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) if(map[i][j] == 'X') parent[i][j] = -1;
        }
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(parent[i][j] == 0) {
                    init(j, i);
                }
            }
        }
        
        int time = 0;
        while(true) {
            // 백조 parent 확인
            if(find(swan[0][0], swan[0][1]) == find(swan[1][0], swan[1][1])) {
                System.out.println(time);
                return;
            }
            // 빙하 녹은 부분 -> 주변 물과 union
            int len = melt.size();
            while(len-->0) {
                int[] cur = melt.remove();
                parent[cur[1]][cur[0]] = cur[2];
                
                for(int d=0; d<4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];
                    if(nx < 0 || nx >= C || ny < 0 || ny >= R || parent[ny][nx] <= 0) continue;
                    union(cur[0], cur[1], nx, ny); // 녹는부분과 이어진 물을 union한다.
                }
                melt.add(cur);
            }
        
            // 다음 빙하 녹이기
            len = melt.size();
            while(len-->0) {
                int[] cur = melt.remove();
                melting(cur[0], cur[1], cur[2]);
            }
            
            time++;
        }
    }
}