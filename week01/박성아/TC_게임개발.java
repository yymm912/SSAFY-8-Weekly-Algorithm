import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, x, y, dir;
    public static int[][] visited = new int[50][50];
    public static int[][] arr = new int [50][50];

    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        visited[x][y] = 1;


        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int res = 1;
        int cnt = 0;
        while (true) {
            dir -= 1;
            if (dir == -1) dir = 3;
            
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (visited[nx][ny] == 0 && arr[nx][ny] == 0) {
                visited[nx][ny] = 1;
                x = nx;
                y = ny;
                res += 1;
                cnt = 0;
                continue;
            }
            else cnt += 1;
            if (cnt == 4) {
                nx = x - dx[dir];
                ny = y - dy[dir];
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }
                else break;
                cnt = 0;
            }
        }

        System.out.println(res);
    }

}