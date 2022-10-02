package week9.김정윤;

import java.io.*;
import java.util.*;

public class BJ2589_보물섬 {
	static int N, M, ans;
	static char[][] map;
	static boolean[][] v;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static class Position {
		int x;
		int y;
		int cnt;
		
		public Position(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {
					v = new boolean[N][M];
					int total = bfs(i, j);
					ans = Math.max(total, ans);
				}
			}
		}
		System.out.println(ans);
	}
	static int bfs(int x, int y) {
		Queue<Position> q = new LinkedList<>();
		int t = 0;
		v[x][y] = true;
		q.add(new Position(x, y, 0));
		
		while (!q.isEmpty()) {
			Position pq = q.poll();
			System.out.println(pq.cnt);
			for (int d = 0; d < 4; d++) {
				int nx = pq.x + dx[d];
				int ny = pq.y + dy[d];
				if (nx >= 0 && ny >= 0 && nx < N && ny < M && !v[nx][ny] && map[nx][ny] == 'L') {
					v[nx][ny] = true;
					q.add(new Position(nx, ny, pq.cnt + 1));
					t = Math.max(t, pq.cnt+1);
				}
			}
		}
		return t;
	}
	
}
