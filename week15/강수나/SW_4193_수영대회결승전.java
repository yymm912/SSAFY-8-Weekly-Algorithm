package a22_11_12;

import java.io.*;
import java.util.*;

public class SW_4193_수영대회결승전 {

	static int N, ans, ey, ex, sy, sx;
	static boolean[][] visited;
	static int[][] map;
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE;
			bfs();
			if (ans == -1) {
				System.out.println("#"+tc+" "+(-1));
			} else {
				System.out.println("#"+tc+" "+(ans-1));
			}
		}		
	}
	
	static void bfs() {
		Queue<Pos> queue = new ArrayDeque<> ();
		visited = new boolean[N][N];
		
		if (map[sy][sx] == 2) {
			queue.offer(new Pos(sy, sx, 3));
		} else if (map[sy][sx] == 0) {
			queue.offer(new Pos(sy, sx, 1));
		}
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			for (int d=0; d<4; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if (ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] || map[ny][nx] == 1) continue;
				if (map[ny][nx] == 2) {
					if (cur.sec % 3 == 0) {
						queue.offer(new Pos(ny, nx, cur.sec+1 ) );
						visited[ny][nx] = true;
					}
					else {
						queue.offer(new Pos(cur.y, cur.x, cur.sec+1));
					}
					
					if (ny==ey && nx==ex) {
						ans = cur.sec+1;
						return;
					}
				} else if (map[ny][nx] == 0) {
					queue.offer(new Pos(ny, nx, cur.sec+1));
					visited[ny][nx] = true;
					if (ny==ey && nx==ex) {
						ans = cur.sec+1;
						return;
					}
				}
			}
		}
		ans = -1;
	}
	
	static class Pos {
		int y, x, sec;
		Pos(int y, int x, int sec) {
			this.y = y;
			this.x = x;
			this.sec = sec;
		}
	}
}
