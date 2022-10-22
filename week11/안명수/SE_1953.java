package algo.SE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SE_1953 {
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int[][] dtype = {
			{0,0,0,0},
			{1,1,1,1},
			{1,1,0,0},
			{0,0,1,1},
			{1,0,0,1},
			{0,1,0,1},
			{0,1,1,0},
			{1,0,1,0}
	};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			L = Integer.parseInt(stk.nextToken());
			
			map = new int[N][M];
			visit = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			int cnt = 0;
			
			Queue<int[]> q = new ArrayDeque<>();
			q.add(new int[] {R,C, 1});
			while(!q.isEmpty()) {
				int[] now = q.poll();
				
				if(visit[now[0]][now[1]]) continue;
				visit[now[0]][now[1]] = true;
				cnt++;
				if(now[2] == L) continue;
				
				int type = map[now[0]][now[1]];
				
				
				for(int i = 0; i < 4; i++) {
					if(dtype[type][i] == 0) continue;
					int ny = now[0] + dy[i];
					int nx = now[1] + dx[i];
					if(ny < 0 || ny >= N || nx < 0 || nx >= M || visit[ny][nx] || map[ny][nx] == 0) continue;
					int thereType = map[ny][nx];
					if(i == 0 && dtype[thereType][1] == 1 ||
							i == 1 && dtype[thereType][0] == 1 ||
							i == 2 && dtype[thereType][3] == 1 ||
							i == 3 && dtype[thereType][2] == 1) {
						q.add(new int[] {ny,nx, now[2] + 1});
					}
				}
			}
			
			
			sb.append('#').append(t).append(' ').append(cnt).append('\n');
		}
		
		System.out.println(sb);
	}
}
