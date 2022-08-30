package study;
import java.util.*;
import java.io.*;

public class Main {
    static int T, M, N, K;
    static int[][] arr;
    static boolean [][] visited;
    
    static int [] dy = { 0, 0, 1, -1};
    static int [] dx = { 1, -1, 0, 0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	M = Integer.parseInt(st.nextToken());
        	N = Integer.parseInt(st.nextToken());
        	K = Integer.parseInt(st.nextToken());
        	
        	arr = new int[N][M];
        	visited = new boolean [N][M];

        	int cnt = 0;
        	for(int i=0; i<K; i++) {
        		int x, y;
        		st = new StringTokenizer(br.readLine());
        		x = Integer.parseInt(st.nextToken());
        		y = Integer.parseInt(st.nextToken());
        		arr[y][x] = 1;
        	}
        	
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<M; j++) {
        			if(arr[i][j] == 1 && !visited[i][j]) {
        				cnt++;
        				dfs(i, j);
        			}
        		}
        	}
        	
        	System.out.println(cnt);
        }
    }
    
    static void dfs(int x, int y){
        visited[x][y] = true;
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx>=0 && ny>=0 && nx<N && ny<M){
                if(arr[nx][ny] == 1 && !visited[nx][ny]) dfs(nx, ny);
            }
        }
    }
}