package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20058 {
	static int N, Q, pow, cnt;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		Q = Integer.parseInt(stk.nextToken());
		
		pow = (int) Math.pow(2, N);
		map = new int[pow][pow];
		visit = new boolean[pow][pow];
		
		for(int i = 0; i < pow; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < pow; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		stk = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < Q; i++) {
			int tgtSize = (int)Math.pow(2, Integer.parseInt(stk.nextToken()));
			fireStorm(0,0,pow, tgtSize);
			
			int[][] copyMAP = new int[pow][pow];
			for(int y = 0; y < pow; y++)
				copyMAP[y] = map[y].clone();
			
			for(int y = 0; y < pow; y++) {
				for(int x = 0; x < pow; x++) {
					if(map[y][x] == 0) continue;
					int cnt = 0;
					for(int d = 0; d < 4; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];
						if(ny < 0 || ny >= pow || nx < 0 || nx >= pow || copyMAP[ny][nx] == 0) continue;
						cnt++;
					}
					
					if(cnt < 3) map[y][x]--;
				}
			}
		}
		
		int sum = 0;
		int maxCnt = 0;
		for(int y = 0; y < pow; y++) {
			for(int x = 0; x < pow; x++) {
				sum += map[y][x];
				if(!visit[y][x]) {
					cnt = 0;
					dfs(y,x);
					maxCnt = Math.max(maxCnt, cnt);
				}
			}
		}
				

		
		
		System.out.println(sum + "\n" + maxCnt);
	}
	
	static void fireStorm(int y, int x, int size, int tgtSize) {
		if(size == tgtSize) {
			int[][] copy = new int[size][size];
			for(int i = 0; i < size; i++)
				for(int j = 0; j < size; j++)
					copy[i][j] = map[y + i][x + j];
			
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					map[y + i][x + j] = copy[size - 1 - j][i];
				}
			}
			return;
		}
		
		fireStorm(y, x, size / 2, tgtSize);
		fireStorm(y + size / 2, x, size / 2, tgtSize);
		fireStorm(y, x + size / 2, size / 2, tgtSize);
		fireStorm(y + size / 2, x + size / 2, size / 2, tgtSize);
	}
	
	static void dfs(int y, int x) {
		if(visit[y][x] || map[y][x] == 0) return;
		visit[y][x] = true;
		
		cnt++;
		
		for(int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || ny >= pow || nx < 0 || nx >= pow || map[ny][nx] == 0 || visit[ny][nx]) continue;
			
			dfs(ny,nx);
		}
		
	}
}
