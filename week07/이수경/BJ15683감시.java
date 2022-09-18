package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15683감시 {

	static int N, M;
	static int map[][];
	static boolean visit[][];
	static boolean visitCopy[][];
	static int dy[] = { -1, 0, 1,  0 }; // 상 우 하 좌 
	static int dx[] = {  0, 1, 0, -1 };
	static int tgt[]; 
	static int min = Integer.MAX_VALUE;
	static int cnt;
	static List<Dist> CCTV = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		visitCopy = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			 st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] >= 1 && map[i][j] <= 5) CCTV.add(new Dist(i,j,map[i][j]));
				if( map[i][j] >= 1 && map[i][j] <= 6) visitCopy[i][j] = true;
				
			}
		}
		// 입력 완
		
		tgt = new int[CCTV.size()]; // tgt (순열의 크기는) cctv 개수 만큼 
		
		perm(0); // cctv 방향 뽑기 (중복순열) 
		
		
		System.out.println(min);
		

	}
	
	static void cal( ) {
		
		
		int blindSpot = 0;
		// 현재 뽑힌 방향에 대해서 계산
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = visitCopy[i][j];
			}
		}
		
		
		for (Dist cctv : CCTV) {
			// n번째 cctv에 대하여 i방향으로 계산
			// cctv 번호에 따라 계산하는게 다름 
			int py = cctv.y;
			int px = cctv.x;
		
			if(cctv.num == 1) {  // 한 방향에 대해서만 고려 
						
					while(true) {
						py = py + dy[tgt[idx]];
						px = px + dx[tgt[idx]];
						
						if( py < 0 || py >= N || px < 0 || px >= M || map[py][px] == 6 ) break;
						if( map[py][px] >= 1 && map[py][px] <= 5 || visit[py][px] ) continue;
					
						visit[py][px] = true; // true 개수로 사각지대 개수 세기 
						
					}
				
				
			}
			else if(cctv.num == 2) { // 2번은 상하, 좌우  두방향 
				
				for (int i = 0; i < 4; i+=2) { // 두 방향에 대해서 고려 
					py = cctv.y;
					px = cctv.x;
					while(true) {
						py = py + dy[(tgt[idx] + i) % 4];
						px = px + dx[(tgt[idx] + i) % 4];
						
						if(  py < 0 || py >= N || px < 0 || px >= M || map[py][px] == 6) break;
						if( map[py][px] >= 1 && map[py][px] <= 5 || visit[py][px] ) continue;
					
						visit[py][px] = true; // true 개수로 사각지대 개수 세기 
					}
					
				}
			}
			else if(cctv.num == 3) { // 상 하 좌 우 고려 
				// idx 0일때 상우, 1일때 우하, 2일때 하좌, 3일때 좌상	
				for (int i = 0; i < 2; i++) { // 두 방향에 대해서 고려 
					py = cctv.y;
					px = cctv.x;
					while(true) {
						py = py + dy[(tgt[idx] + i) % 4];
						px = px + dx[(tgt[idx] + i) % 4];
						
						if(  py < 0 || py >= N || px < 0 || px >= M || map[py][px] == 6 ) break;
						if( map[py][px] >= 1 && map[py][px] <= 5 || visit[py][px] ) continue;
					
						visit[py][px] = true; // true 개수로 사각지대 개수 세기 
					}
					
					
				}
				
				
			}
			else if(cctv.num == 4) {
				
				for (int i = 0; i < 3; i++) { // 세 방향에 대해서 고려 
					py = cctv.y;
					px = cctv.x;
					while(true) {
						py = py + dy[(tgt[idx] + i) % 4];
						px = px + dx[(tgt[idx] + i) % 4];
						
						if( py < 0 || py >= N || px < 0 || px >= M || map[py][px] == 6 ) break;
						if( map[py][px] >= 1 && map[py][px] <= 5 || visit[py][px] ) continue;
					
						visit[py][px] = true; // true 개수로 사각지대 개수 세기 
					}
					
					
				}
			} 
			else if(cctv.num == 5) {
			
					for (int d = 0; d < 4; d++) { // 모든 방향에 대해서 
						py = cctv.y;
						px = cctv.x;
						while(true) {
							py = py + dy[d];
							px = px + dx[d];
							
							if( py < 0 || py >= N || px < 0 || px >= M ||map[py][px] == 6 ) break;
							if( map[py][px] >= 1 && map[py][px] <= 5 || visit[py][px] ) continue;
						
							visit[py][px] = true; // true 개수로 사각지대 개수 세기 
							
						}
					}
					
			}
			
			
			idx++;
		}
		
		cnt = 0;
		// visit 개수 세기(사각지대 개수) 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visit[i][j]) cnt++;
			}
		}
		
		
		
	}
	static void perm(int tgtIdx) {
		if(tgtIdx == tgt.length) {
			
			cal();
			min = Math.min(min, cnt);
			return;
		}
		
		
		for (int i = 0; i < 4; i++) {
			tgt[tgtIdx] = i; // 0 1 2 3 중 하나가 뽑힘 ( 상 우 하 좌 )
			perm(tgtIdx + 1);
		}	
		
		
	}
	
	static class Dist {
		int y, x, num;

		public Dist(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}

		
		
	}
}
