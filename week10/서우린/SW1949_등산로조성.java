package _10주차;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1949_등산로조성 {
	static int [][] board;
	static int T,N,K;
	static Point [] maxH = new Point[5];
	static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean [][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T;t++) {
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			visited = new boolean[N][N];
			int idx = 0;
			int m = -1;
			for(int i = 0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0;j<N;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					m = Math.max(m, board[i][j]);
				}
			}
			
			for(int i = 0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					if(board[i][j] == m) {
						maxH[idx++] = new Point(j,i);
					}
				}
			}
			
			int ans = 0;
			
			for(int i = 0; i < idx; i++) {
				int r = maxH[i].y;
				int c = maxH[i].x;
				ans = Math.max(ans, dfs(r,c,board[r][c],true));
			}
			
			System.out.println("#"+t+" "+ans);
			
		}

	}
	
	static int dfs(int r,int c,int h,boolean k) {
		int ret = 1;
		visited[r][c] = true;
		for(int i = 0; i < 4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if(visited[nr][nc]) continue;
			if(h > board[nr][nc]) {
				ret = Math.max(ret, dfs(nr,nc,board[nr][nc],k)+1);
			}
			
			if(k && h <= board[nr][nc]) {
				if(board[nr][nc] - K < h) {
					ret = Math.max(ret, dfs(nr,nc,h-1,false)+1);
				}
			}
		}
		visited[r][c] = false;
		return ret;
	}

}
