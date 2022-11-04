package baekjoon.상어중학교_21609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, ans, sy, sx, maxCnt, maxRainCnt;
	static int[][] map;
	
	static boolean[][][] visit;
	static Count cnt;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ans = 0;
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		start();
		
		System.out.println(ans);
		
	}
	
	static void start() {
		// #1. 가장 큰 블록 그룹 찾기 -> 없으면 멈추기
		// #2. 블록을 제거한다. 블록 개수 ^2 만큼 점수 획득
		// #3. 중력
		// #4. 반시계로 돌린다.
		// #5. 중력
		
		while(true) {
			// #1
			if(!findGroup()) {
				return;
			}
			ans += maxCnt*maxCnt;
			// #2
			removeBlock(sy, sx, map[sy][sx]);
			
			// #3
			gravity();
			
			// #4
			rotation();
			
			// #5
			gravity();
		}
	}
	
	static boolean findGroup() {
		visit = new boolean[M+1][N][N]; // 이미 탐색을 한놈인지 체크 이때 무지개(0)인 애들은 체크하지 않는다.
		maxCnt = -1; // 가장 큰 개수 점수로 분류
		maxRainCnt = -1;
		cnt = new Count();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 방문했거나 검은블록(-1)이거나 무지개블록(0)이면 스킵
				if(visit[0][i][j] || map[i][j]<1) continue;
				visit[0][i][j] = true;
				cnt.clear();
				cnt.color = map[i][j];
				dfs(i,j);
				if(cnt.totalCnt >= 2) {
					if(maxCnt < cnt.totalCnt) {
						maxCnt = cnt.totalCnt;
						maxRainCnt = cnt.rainbowCnt;
						sy = i;
						sx = j;
					}else if(maxCnt == cnt.totalCnt) {
						if(maxRainCnt < cnt.rainbowCnt) {
							maxRainCnt = cnt.rainbowCnt;
							sy = i;
							sx = j;
						}else if(maxRainCnt == cnt.rainbowCnt) {
							if(sy < i) {
								sy = i;
								sx = j;
							}else if( sy == i ) {
								if(sx < j) {
									sx = j;
								}
							}
						}
					}
				}
				
				
			}
		}
		if(maxCnt == -1) return false;
		return true;
	}
	
	static void dfs(int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny<0 || nx<0 || ny>=N || nx>=N || visit[0][ny][nx] || map[ny][nx] < 0) continue;
			if(map[ny][nx] == 0 && !visit[cnt.color][ny][nx]) { // 방문하지 않은 무지개면
				visit[cnt.color][ny][nx] = true;
				cnt.totalCnt++;
				cnt.rainbowCnt++;
				dfs(ny, nx);
			}else if(map[ny][nx] == cnt.color) {
				visit[0][ny][nx] = true;
				cnt.totalCnt++;
				dfs(ny, nx);
			}
		}
	}
	
	static void removeBlock(int y, int x, int color) {
		map[y][x] = -2;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny<0 || nx<0 || ny>=N || nx>=N) continue;
			if(map[ny][nx] == 0 || map[ny][nx] == color) {
				removeBlock(ny, nx, color);
			}
		}
	}
	
	static void gravity() {
		for (int i = 0; i < N; i++) {
			int idx = N-1;
			int cnt = 0;
			while(idx>=0) {
				if(map[idx][i] == -2) {
					cnt++;
				}
				else if(map[idx][i] == -1) {
					cnt = 0;
				}else {
					if(cnt!=0) {
						int tmp = map[idx][i];
						map[idx][i] = map[idx+cnt][i];
						map[idx+cnt][i] = tmp;
					}	
				}
				idx--;
				
			}
		}
	}
	
	static void rotation() {
		int n = N/2;
		for (int i = 0; i < n; i++) {
			int[] tmp = new int[N-(i*2)];
			for (int j = 0; j < N-(i*2); j++) {
				tmp[j] = map[i][i+j];
			}
		
			for (int k = i; k < N-i-1; k++) {
				map[i][k] = map[k][N-i-1]; // 우 -> 상
				map[k][N-i-1] = map[N-i-1][N-k-1]; // 하 -> 우
				map[N-i-1][N-k-1] = map[N-k-1][i]; // 좌 -> 하
				map[N-k-1][i] = tmp[k-i]; // 상 -> 좌
			}
				
		}
	}
	
	static class Count{
		int rainbowCnt;
		int totalCnt;
		int color;
		
		Count(){
			this.totalCnt = 1;
			this.rainbowCnt = 0;
		}
		
		public void clear() {
			rainbowCnt = 0;
			totalCnt = 1;
			color = 0;
		}
	}
}
