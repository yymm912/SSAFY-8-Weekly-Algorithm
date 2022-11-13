package a22_11_08;

import java.io.*;
import java.util.*;

public class BOJ_18430_무기공학 {

	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dy1= {-1,-1, 0, 0};
	static int[] dx1= { 0, 0,-1, 1};
	static int[] dy2= { 0, 0, 1, 1};
	static int[] dx2= { 1,-1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		if(N<2 || M<2) {
			System.out.println(0);
			return;
		}
		
		dfs(0,0,0);
		System.out.println(ans);
	}
	
	static void dfs(int y, int x, int sum) {
		ans = Math.max(ans, sum);
		if (x>=M) {
			y++;
			x=0;
		}
		if (y>=N) return;
		
		if (visited[y][x]) {
			dfs(y,x+1,sum);
		} else {

			for (int type=0; type<4; type++) {
				int ny1 = y+dy1[type];
				int nx1 = x+dx1[type];
				int ny2 = y+dy2[type];
				int nx2 = x+dx2[type];
				if (ny1<0 || ny1>=N || nx1<0 || nx1>=M || visited[ny1][nx1]) continue;
				if (ny2<0 || ny2>=N || nx2<0 || nx2>=M || visited[ny2][nx2]) continue;
				
				visited[ny1][nx1] = true;
				visited[ny2][nx2] = true;
				visited[y][x] = true;
				
				int power = map[ny1][nx1] + map[ny2][nx2] + map[y][x]*2;
				dfs( y, x+1, sum+power);
				
				visited[ny1][nx1] = false;
				visited[ny2][nx2] = false;
				visited[y][x] = false;
			}
			dfs( y, x+1, sum);
		}
	}
}