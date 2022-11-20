package a22_11_20.study;

import java.io.*;
import java.util.*;

public class SW_4193_수영대회결승전 {

	static int N, sy, sx, ey, ex, ans;
	static int[][] map;
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			ey = Integer.parseInt(st.nextToken());
			ex = Integer.parseInt(st.nextToken());
			//입력 끝
			
			ans = -1;
			bfs();
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void bfs() {
		Queue<Pos> queue = new ArrayDeque<> ();
		boolean[][] visited = new boolean[N][N];
		
		queue.offer(new Pos(sy, sx, 0));
		visited[sy][sx] = true;
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int y= cur.y;
			int x= cur.x;
			int sec= cur.sec;
			
			if (y==ey && x==ex) {
				ans = sec;
				break;
			}
			
			for (int d=0; d<4; d++) {
				int ny = y+dy[d];
				int nx = x+dx[d];
				if (ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx]) continue;
				if (map[ny][nx] == 1) continue;
				
				if (map[ny][nx] == 0) {
					queue.offer(new Pos(ny, nx, sec+1));
					visited[ny][nx] = true;
				} else if (map[ny][nx] == 2) {
					if ( (sec+1)%3 == 0 ) {
						queue.offer(new Pos(ny, nx, sec+1));
						visited[ny][nx] = true;
					} else {
						queue.offer(new Pos(y, x, sec+1));
					}
				}
			}
		}
		
		
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
