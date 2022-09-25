package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17144미세먼지안녕2 {

	static int R, C, T;
	static int map[][];
	static int dust[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int circulater_y;
	static int circulater_x;
	static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		dust = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == -1 && circulater_y == 0) {
					circulater_y = i;
				}
			}
		}
		// 입력 완 
		
		
		for (int t = 0; t < T; t++) { // T초 만큼 반복
			// 미세먼지 확산 
			dust = new int[R][C];
			spread();
			
			// 공기청정기 작동
			clean();
			
		}
		
		// 남아있는 미세먼지 양 구하기 
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == -1) continue; // 공기청정기 제외 
				ans += map[i][j];
			}
		}
		System.out.println(ans);
	}

	static void spread() {

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {	
				// 인접한 네 방향으로 확산
				int cnt = 0;
				int spreadMount = 0;
				if(map[i][j] > 0 ) { // 공기 청정기는 제외
					spreadMount = map[i][j]/5;
					for (int d = 0; d < 4; d++) {
						// spreadMount = 확산되는 양은 map[i][j]/5 
						// r,c 에 남은 미세먼지 양은 map[i][j] - ( spreadMount*확산된 방향 개수 )
						// 공기 청정기가 있거나 칸이 없으면 그 방향으로는 확산 일어나지 않음
						int py = i + dy[d];
						int px = j + dx[d];
						if( py < 0 || py >= R || px < 0 || px >= C 
								|| map[py][px] == -1 )  continue;
						cnt++;
						
						dust[py][px] += spreadMount;
					}
					dust[i][j] -= spreadMount*cnt;
				}
			
			}
		}
		
		// 먼지는 한꺼번에 퍼진다 ( 위에서 퍼뜨리면 다음 배열 계산할때 문제 발생 )
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += dust[i][j];
			}
		}
	}
	
	static void clean() {
		// 위쪽 공기청정기는 반시계방향 순환 
		// 아래쪽 공기청정기는 시계방향 순환
		
		//////////////////////////////// 위쪽 먼지들의 이동 
		// 1. 왼쪽 -> 오른쪽으로 먼지 이동 
		int right = map[circulater_y][C - 1];
		for (int j = (C-1) - 1; j >= 0; j--) {
			map[circulater_y][j+1] =  map[circulater_y][j];
		}
		

		// 2. 밑 -> 위 로 이동
		int top = map[0][C - 1];
		for (int i = 0; i < circulater_y; i++) {
			map[i][C - 1] = map[i+1][C - 1]; 
		}
		map[circulater_y - 1][C - 1] = right;
		
		// 3. 오른쪽 -> 왼쪽으로 이동 
		int left = map[0][0];
		for (int j = 0; j < C - 1; j++) {
			map[0][j] = map[0][j+1];
		}
		map[0][C - 1 - 1] = top;

		
		// 4. 위 -> 아래로 이동
		for (int i = (circulater_y - 1) - 1; i > 0; i--) {
			map[i+1][0] = map[i][0];
		} 
		map[1][0] = left;
		
		map[circulater_y][1] = 0;
	
		
		
		//////////////////////////////////////////// 아래쪽 먼지들의 이동
		circulater_y++;
		
		// 1. 왼쪽 -> 오른쪽으로 먼지 이동 
		right = map[circulater_y][C-1]; // 맨 오른쪽 아래 먼지 
		for (int i = C-1; i > 1; i--) {
			map[circulater_y][i] = map[circulater_y][i-1];
		}
		
		// 2. 위 -> 아래로 이동
		int bottom = map[R-1][C-1];
		
		for (int i = R-1; i > circulater_y; i--) {
			map[i][C-1] = map[i-1][C-1];
		}
		map[circulater_y+1][C-1] = right;
		
		
		// 3. 오른쪽 -> 왼쪽으로 이동 
		left = map[R-1][0];
		
		for (int i = 0; i < C-1; i++) {
			map[R-1][i] = map[R-1][i+1];
		}
		map[R-1][C-2] = bottom;
		
		
		// bottom 은 공기청정기 속으로 사라지기 때문에 필요없음!
		
		// 4. 밑 -> 위 로 이동
		for (int i = circulater_y+1; i < R-1; i++) {
			// 밑에서 위로 
			map[i][0] = map[i+1][0];
		}
		map[R-2][0] = left;

		map[circulater_y][1] = 0;

		
		circulater_y --; // 공기청정기 위치 복구
	}
}
