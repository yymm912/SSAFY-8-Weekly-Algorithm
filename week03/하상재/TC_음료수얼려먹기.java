import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class TC_음료수얼려먹기 {

    static char[][] arr;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,-1,1};
    static int cnt, n, m;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        cnt = 0;
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        arr = new char[n][m];

        for(int i=0; i<n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j]=='0') bfs(i,j);
            }
        }



        System.out.print(cnt);
    }

    static void bfs(int s1, int s2) {
        cnt++;
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {s1,s2});
        int nr=0, nc=0;
        arr[s1][s2] = '1';
        while(!que.isEmpty()) {
            int[] rc = que.poll();

            for(int i=0; i<4; i++) {
                nr = rc[0]+dr[i];
                nc = rc[1]+dc[i];
                if( nr < 0 || nc < 0 || nr >= n || nc >= m) continue;
                if(arr[nr][nc]=='0') {
                    arr[nr][nc]='1';
                    que.add(new int[] {nr,nc});
                }
            }
        }
    }

}