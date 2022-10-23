package a22_10_16;

import java.io.*;
import java.util.*;

public class SW_1949_등산로조성 {

	static int[][] map, cmap;
	static int N, K, maxHeight, max;
	static boolean[][] visited;
	
	//하우상좌
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			maxHeight=0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			} //입력끝
			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						visited[i][j] = true;
						dfs(i,j, maxHeight,1,false);
						visited[i][j] = false;
					}
				}
			}
			System.out.println("#"+tc+" "+max);
		}
	}
	
	static void dfs(int y, int x, int height, int len, boolean use) {
		max = Math.max(max, len);
		for (int d = 0; d < 4; d++) {
			int ny = y+dy[d];
			int nx = x+dx[d];
			if (ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx]) continue;
			
			if ( map[ny][nx] < height ) {
				visited[ny][nx] = true;
				dfs(ny, nx, map[ny][nx], len+1, use);
				visited[ny][nx] = false;
			} else { //새로운 높이가 현재 높이보다 크거나 같을 때
				if (use==false && map[ny][nx]-height < K) { 
					visited[ny][nx] = true;
					dfs(ny, nx, height-1, len+1, true); //K사용
					visited[ny][nx] = false;
				}
			}
		}
	}
}
