import java.io.*;
import java.util.*;

public class Solution{
	static int T, N, M, L;
	static int[][] map;
	static int sy, sx;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			sx = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + tc + " " + bfs());
		}
	}
	static int[] dx = {0, 0, -1, 1}; // 상 하 좌 우
	static int[] dy = {-1, 1, 0, 0};
	/**
	 * 현재 상,하 일때 => 상 -> (하 터널), 하 -> (상 터널) 갈 수 있음.
	 * 상 : 1, 2, 4, 7
	 * 하 : 1, 2, 5, 6
	 * 좌 : 1, 3, 6, 7
	 * 우 : 1, 3, 4, 5
	 * */
	static int[][] dd = { // [dir][tunnel]
			{1, 2, 4, 7}, // 상
			{1, 2, 5, 6}, // 하
			{1, 3, 6, 7}, // 좌
			{1, 3, 4, 5}, // 우 뚫린 터널
	};
	
	static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][M];
		q.add(new int[] {sy, sx});
		visit[sy][sx] = true;
		
		int cnt = 0;
		while(!q.isEmpty() && L-- > 0) { // L시간동안
			int len = q.size();
			while(len-->0) {
				int[] cur = q.remove();
				cnt++;
				int dir = map[cur[0]][cur[1]];
				
				if(dir == 1 || dir == 2 || dir == 4 || dir == 7) { // 상
					int nx = cur[1] + dx[0];
					int ny = cur[0] + dy[0];
					if(!oor(ny, nx) && !visit[ny][nx] && isExpectedDir(1, map[ny][nx])) { // 다음 방향 -> 하
						q.add(new int[] {ny, nx});
						visit[ny][nx] = true;
					}
				}
				if(dir == 1 || dir == 2 || dir == 5 || dir == 6) { // 하
					int nx = cur[1] + dx[1];
					int ny = cur[0] + dy[1];
					if(!oor(ny, nx) && !visit[ny][nx] && isExpectedDir(0, map[ny][nx])) { // 다음 방향 -> 상
						q.add(new int[] {ny, nx});
						visit[ny][nx] = true;
					}					
				}
				if(dir == 1 || dir == 3 || dir == 6 || dir == 7) { // 좌
					int nx = cur[1] + dx[2];
					int ny = cur[0] + dy[2];
					if(!oor(ny, nx) && !visit[ny][nx] && isExpectedDir(3, map[ny][nx])) { // 다음 방향 -> 우
						q.add(new int[] {ny, nx});
						visit[ny][nx] = true;
					}					
				}
				if(dir == 1 || dir == 3 || dir == 4 || dir == 5) { // 우
					int nx = cur[1] + dx[3];
					int ny = cur[0] + dy[3];
					if(!oor(ny, nx) && !visit[ny][nx] && isExpectedDir(2, map[ny][nx])) { // 다음 방향 -> 좌
						q.add(new int[] {ny, nx});
						visit[ny][nx] = true;
					}					
				}
			}
		}
		return cnt;
	}
	static boolean isExpectedDir(int expectedDir, int tunnel) {
		for(int tn=0; tn<4; tn++) {
			if(dd[expectedDir][tn] == tunnel) return true;
		}
		return false;
	}
	static boolean oor(int ny, int nx) {
		return nx < 0 || nx >= M || ny < 0 || ny >= N;
	}
}