package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2638 {
	static int R, C;
	static int[][] map;
	
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		map = new int[R][C];
		
		int cheeseCnt = 0;
		for(int i = 0; i < R; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == 1) cheeseCnt++;
			}
		}
		
		int time = 0;
		
		
		map[0][0] = -1;
		while(cheeseCnt > 0) {
			findAir();
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if(map[i][j] == 1) {
						int cnt = 0;
						for(int k = 0; k < 4; k++) {
							int ny = i + dy[k];
							int nx = j + dx[k];
							if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
							if(map[ny][nx] == -1) {
								cnt++;
							}
						}
						if(cnt >= 2) {
							map[i][j] = 0;
							cheeseCnt--;
						}
					}
				}
			}
			time++;
		}
		
		
		System.out.println(time);
	}
	
	static void findAir() {
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == -1) {
					for(int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];
						if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
						if(map[ny][nx] == 0) {
							q.add(new int[] {ny,nx});
						}
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			if(map[now[0]][now[1]] == -1) continue;
			map[now[0]][now[1]] = -1;
			for(int i = 0; i < 4; i++) {
				int ny = now[0] + dy[i];
				int nx = now[1] + dx[i];
				if(ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
				if(map[ny][nx] == 0) {
					q.add(new int[] {ny,nx});
				}
			}
			
		}
	}
}
