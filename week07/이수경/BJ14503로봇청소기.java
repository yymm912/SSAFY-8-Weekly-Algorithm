package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503로봇청소기 {

	static int N, M, r, c, d;
	static int map[][];
	static boolean visit[][];
	static int dy[] = {  -1, 0, 1, 0   }; // 북 동 남 서 
	static int dx[] = {   0, 1, 0, -1  };
	static int ans;
	static int cnt;
	static int py, px;
	static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		// 처음 시작 위치 
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken()); // 방향 
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완
		
		dfs(r, c, 0, d);
		
		System.out.println(ans + 1);
		
	}
	
	static void dfs(int y, int x, int depth, int d) {
		
		visit[y][x] = true; // 현재 위치를 청소
		
		ans = Math.max(ans, depth);
		
		// 현재 기준 방향 왼쪽방향으로 차례대로 탐색 진행
		cnt = 0;
		while(true) {
			
			if( cnt == 4 ) {
				// 네 방향 모두 청소가 되어있는 경우,
				// 바라보는 방향을 유지한 채 한 칸 후진, 2번 반복 
				py = y - dy[d];
				px = x - dx[d];
				if( py < 0 || py >= N || px < 0 || px >= M || map[py][px] == 1 ) {
					flag = true;
					return; // 뒤쪽 방향이 벽이라 후진도 못하면 작동 중지  
				}
				
				dfs( py, px, depth, d);
				
			}
			if(flag) return;
			d = d-1;
			if( d < 0 ) d = 3;
			
			py = y + dy[d]; 
			px = x + dx[d];
			cnt++;
			
			if( py < 0 || py >= N || px < 0 || px >= M || visit[py][px] || map[py][px] != 0) continue;
			
			dfs(py, px, depth + 1, d);
			
			
		}
		
	}

}
