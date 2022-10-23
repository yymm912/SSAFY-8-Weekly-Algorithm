package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_21610 {
	static int N, M, d, s, WATER;
	static int[][] A;
	
	//delta 
	static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	static boolean[][] isRain;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		A = new int[N + 1][N + 1];
		isRain = new boolean[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		List<POS> cloud = new ArrayList<>();
		cloud.add(new POS(N, 1));
		cloud.add(new POS(N, 2));
		cloud.add(new POS(N-1, 1));
		cloud.add(new POS(N-1, 2));
		while(M > 0) {
			stk = new StringTokenizer(br.readLine());
			d = Integer.parseInt(stk.nextToken());
			s = Integer.parseInt(stk.nextToken());
			
			// 1,2,3.
			for(POS c : cloud) {
				// 구름 이동
				int sy = dy[d] * s;
				int sx = dx[d] * s;
				
				for(int i = 0; i < s; i++) {
					c.y += dy[d];
					c.x += dx[d];
					
					if(c.y == N+1) c.y = 1;
					else if(c.y == 0) c.y = N;
					
					if(c.x == N+1) c.x = 1;
					else if(c.x == 0) c.x = N;
				}
				
				A[c.y][c.x]++;
				isRain[c.y][c.x] = true; 
			}
			
			
			// 4. 물복사버그
			while(!cloud.isEmpty()) {
				POS c = cloud.remove(0);
				
				int count = 0;
				for(int i = 2; i <=8; i += 2) {
					int ny = c.y + dy[i];
					int nx = c.x + dx[i];
					if(ny < 1 || ny > N || nx < 1 || nx > N)continue;
					if(A[ny][nx] > 0) count++;
				}
				
				A[c.y][c.x] += count; 
			}
			
			
			// 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
			// 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
			for(int y = 1; y <= N; y++) {
				for(int x = 1; x <= N; x++) {
					if(isRain[y][x]) {
						isRain[y][x] = false;
						continue;
					}
					
					if(A[y][x] >= 2) {
						A[y][x] -= 2;
						cloud.add(new POS(y,x));
					}
				}
			}
			
			
			M--;
		}
		
		for(int y = 1; y <= N; y++)
			for(int x = 1; x <= N; x++)
				WATER += A[y][x];
		
		System.out.println(WATER);
	}
	
	static class POS{
		int y;
		int x;
		public POS(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
