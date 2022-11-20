import java.io.*;
import java.util.*;

public class SWEA4193_수영대회결승전 {
	static int T, N, startR, startC, endR, endC, ans;
	static int[][] pool;
	static boolean[][] v;
	
	// 상-하-좌-우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			pool = new int[N][N];
			v = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					pool[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			startR = Integer.parseInt(st.nextToken());
			startC = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			endR = Integer.parseInt(st.nextToken());
			endC = Integer.parseInt(st.nextToken());
			
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		v[startR][startC] = true;
		q.offer(new Node(startR, startC));
		
		int time = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Node cur = q.poll();

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					// 범위를 넘어서거나 이미 방문한 곳, 또는 장애물인 경우 패스
					if (nr < 0 || nc < 0 || nr >= N || nc >= N || v[nr][nc] || pool[nr][nc] == 1) continue;
					
					if (nr == endR && nc == endC) { // 도착지점에 도달한 경우
						ans = time;
						return;
					}

					if (pool[nr][nc] == 2) {
						if (time%3 != 0) {
							q.offer(cur);
							continue;
						}	
					}
					
					v[nr][nc] = true;
					q.offer(new Node(nr, nc));
					
				}
			}
			time++;
		}
		ans = -1;
	}
	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}	
	}
}
