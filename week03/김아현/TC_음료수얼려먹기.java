package forStudy.week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_음료수얼려먹기 {

	static int N, M;
	static int[][] arr;
	static int[] dx = {-1,1,0,0}; // 상-하-좌-우
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			for(int j=0; j<M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
	
	static void dfs(int x, int y) {
		arr[x][y] = 1;	// visited 처리
		
		for(int i=0; i<4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
			
			if(arr[tx][ty] == 0) {
				dfs(tx, ty);
			}
		}
	}

}
