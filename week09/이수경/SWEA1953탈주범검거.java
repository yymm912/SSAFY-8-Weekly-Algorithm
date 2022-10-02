package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA1953탈주범검거 {

	static int T;
	static int N, M, R, C, L;
	static int map[][];
	static boolean visit[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static int ans;
	static Deque<Dist> q = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			ans = 0;
			q.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 입력 완 
			
			// 1 +  사방탐색 
			// 2 ㅣ  상 하 탐색 
			// 3 ㅡ  좌 우 탐색 
			// 4 ㄴ  상 우 탐색 
			// 5     우 하 탐색 
			// 6 ㄱ  좌 하 탐색 
			// 7    좌 상 탐색 
			
			//dfs(R, C, 0);
			
			
			bfs(R, C);
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(visit[i][j]) ans++;
				}
			}
			
			System.out.println("#" + t + " " + ans );
			
		} // testcase
		
		
		
		
	}
	static void dfs(int y, int x, int depth) {
	
		visit[y][x] = true;
		// 오른쪽으로 가는 경우, 그 다음 파이프가 2번, 4번, 5번, 인경우는 못감!
		// 왼쪽으로 가는 경우, 그 다음 파이프가 2번, 6번, 7번 인경우는 못감! 
		// 밑으로 가는 경우, 그 다음 파이프가 3번, 5번, 6번 인 경우는 못감! 
		// 위로 가는 경우, 그 다음 파이프가 3번, 4번, 7번 인 겨경우는 못감!
		if( map[y][x] == 1 ) {
			
			for (int d = 0; d < 4; d++) {
				int py = y + dy[d];
				int px = x + dx[d];
			
				if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0) continue;
				if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
				if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
				if ( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;
				if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;
				
				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
				
			}
		}
		else if( map[y][x] == 2 ) {
			// 상 하 
			
			for (int d = 0; d < 2; d++) {
				int py = y + dy[d];
				int px = x +dx[d];
				
				if( py < 0 || py >= N || px < 0 || px >= M   || map[py][px] == 0) continue;
				if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
				if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
				
				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
			}
		}
		else if ( map[y][x] == 3 ) {
			// 좌 우

			for (int d = 2; d < 4; d++) {
				
				int py = y + dy[d];
				int px = x +dx[d];
			
				
				if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0) continue;
				if ( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;
				if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;
			
				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
			}
		} 
		else if ( map[y][x] == 4 ) {
			// 상 우 

			for (int d = 0; d < 4; d+=3) { // 상 우 방향만 보기
				int py = y + dy[d];
				int px = x + dx[d];
			
				
				if( py < 0 || py >= N || px < 0 || px >= M   || map[py][px] == 0) continue;
				if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
				if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;

				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
			}
			
		}
		else if ( map[y][x] == 5 ) {
			// 하 우 

			for (int d = 1; d < 4; d+=2) { // 하 우 방향만 보기
				int py = y + dy[d];
				int px = x + dx[d];
				
				if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0) continue;
				if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
				if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;

				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
			}
		}
		else if ( map[y][x] == 6 ) {
			// 하 좌 

			for (int d = 1; d < 3; d++) { // 하 좌 방향만 보기
				int py = y + dy[d];
				int px = x + dx[d];
				
				if( py < 0 || py >= N || px < 0 || px >= M || map[py][px] == 0) continue;
				if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
				if( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;

				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
			}
		}
		else if ( map[y][x] == 7 ) {
			// 상 좌 

			for (int d = 0; d < 3; d+=2) { // 상 좌 방향만 보기
				int py = y + dy[d];
				int px = x + dx[d];
				
				if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0) continue;
				if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
				if( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;

				
				dfs(py, px, depth + 1);
				visit[py][px] = false;
			}
		}
	
		
	}
	
	static void bfs(int y, int x ) {
		
		
		// 오른쪽으로 가는 경우, 그 다음 파이프가 2번, 4번, 5번, 인경우는 못감!
		// 왼쪽으로 가는 경우, 그 다음 파이프가 2번, 6번, 7번 인경우는 못감! 
		// 밑으로 가는 경우, 그 다음 파이프가 3번, 5번, 6번 인 경우는 못감! 
		// 위로 가는 경우, 그 다음 파이프가 3번, 4번, 7번 인 겨경우는 못감!
		
		visit[y][x] = true;
		q.offer(new Dist(y, x)); // 시작점 - 하수구 위치 
		int cnt = 0;
		while(!q.isEmpty()) {
			
			int q_size = q.size();
			
			cnt++;
			if( cnt == L ) break;
			for (int i = 0; i < q_size; i++) {
				
				Dist e = q.poll();
				if( cnt == L ) break;
				if( map[e.y][e.x] == 1 ) {
					
					for (int d = 0; d < 4; d++) {
						int py = e.y + dy[d];
						int px = e.x + dx[d];
					
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
						if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
						if ( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;
						if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;
						
						
						visit[py][px] = true;
						q.offer(new Dist(py, px));
						
					}
				}
				else if( map[e.y][e.x] == 2 ) {
					// 상 하 
					
					for (int d = 0; d < 2; d++) {
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						
						if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
						if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
						
						visit[py][px] = true;
						q.offer(new Dist(py, px));
					}
				}
				else if ( map[e.y][e.x] == 3 ) {
					// 좌 우

					for (int d = 2; d < 4; d++) {
						
						int py = e.y + dy[d];
						int px = e.x + dx[d];
					
						
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						
						if ( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;
						if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;
					
						visit[py][px] = true;
						q.offer(new Dist(py, px));
					}
				} 
				else if ( map[e.y][e.x] == 4 ) {
					// 상 우 

					for (int d = 0; d < 4; d+=3) { // 상 우 방향만 보기
						int py = e.y + dy[d];
						int px = e.x + dx[d];
					
						
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						
						if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
						if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;

						visit[py][px] = true;
						q.offer(new Dist(py, px));
					}
					
				}
				else if ( map[e.y][e.x] == 5 ) {
					// 하 우 

					for (int d = 1; d < 4; d+=2) { // 하 우 방향만 보기
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						
						if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
						if( d == 3 ) if(map[py][px] == 2 || map[py][px] == 4 || map[py][px] == 5 )continue;

						visit[py][px] = true;
						q.offer(new Dist(py, px));
					}
				}
				else if ( map[e.y][e.x] == 6 ) {
					// 하 좌 

					for (int d = 1; d < 3; d++) { // 하 좌 방향만 보기
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						
						if( d == 1 )  if(map[py][px] == 3 || map[py][px] == 5 || map[py][px] == 6) continue;
						if( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;

						visit[py][px] = true;
						q.offer(new Dist(py, px));
					}
				}
				else if ( map[e.y][e.x] ==  7 ) {
					// 상 좌 

					for (int d = 0; d < 3; d+=2) { // 상 좌 방향만 보기
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						
						if( py < 0 || py >= N || px < 0 || px >= M  || map[py][px] == 0 || visit[py][px] ) continue;
						
						if( d == 0 )  if(map[py][px] == 3 || map[py][px] == 4 || map[py][px] == 7) continue;
						if( d == 2 ) if(map[py][px] == 2 || map[py][px] == 6 || map[py][px] == 7) continue;

						visit[py][px] = true;
						q.offer(new Dist(py, px));
					}
				}
				
			} // for i  (q_size)
			
			
			
		}
		
		
	}

	
	static class Dist {
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}


/*

5
5 6 2 1 3
0 0 5 3 6 0
0 0 2 0 2 0
3 3 1 3 7 0
0 0 0 0 0 0
0 0 0 0 0 0
5 6 2 2 6
3 0 0 0 0 3
2 0 0 0 0 6
1 3 1 1 3 1
2 0 2 0 0 2
0 0 4 3 1 1
10 10 4 3 9
0 0 0 0 0 0 0 0 0 0
0 0 0 7 5 0 5 0 0 0
0 0 3 2 2 6 0 0 0 0
0 4 7 2 2 2 7 0 0 4
0 3 0 1 1 2 2 0 0 5
0 5 6 1 1 1 1 6 2 5
7 4 1 2 0 0 4 6 0 0
5 3 1 7 0 2 2 6 5 7
7 3 2 1 1 7 1 0 2 7
3 4 0 0 4 0 5 1 0 1
20 20 13 11 13
0 0 0 1 4 4 4 0 0 0 0 0 0 0 0 1 2 3 1 0
0 0 0 0 0 0 0 0 0 0 0 4 2 7 7 2 0 1 1 0
0 0 0 0 0 0 0 0 0 6 2 4 4 2 0 4 7 0 6 0
0 0 0 7 5 5 3 0 0 7 5 0 5 6 4 2 6 3 1 5
0 0 0 1 2 6 3 3 7 0 3 6 2 4 5 6 7 7 5 7
0 0 0 3 7 6 1 5 3 3 4 5 7 6 0 4 3 3 1 1
0 1 2 1 5 6 1 6 1 6 5 1 6 0 0 3 4 1 7 6
0 2 3 2 2 7 3 0 0 3 2 5 2 1 0 6 5 1 6 5
0 2 5 7 0 7 1 3 3 4 1 3 3 0 2 3 3 2 4 1
4 0 0 7 2 4 2 2 1 3 1 6 5 5 6 2 5 1 1 6
5 6 4 0 3 6 5 2 2 6 1 2 0 1 7 5 7 2 2 2
1 6 3 1 4 4 1 0 3 0 4 2 7 2 0 2 3 6 2 5
1 5 7 2 1 1 4 4 2 1 0 2 7 1 6 2 6 6 2 2
3 7 0 6 5 0 4 0 6 6 7 1 3 1 1 1 5 1 6 6
0 4 0 1 6 2 1 0 7 0 4 2 5 2 7 0 2 7 1 6
0 7 3 0 1 7 6 2 0 0 4 2 4 1 3 3 7 0 1 3
0 1 1 4 3 7 4 5 2 2 4 7 4 7 7 4 6 0 1 6
0 5 2 2 1 4 6 3 7 0 6 3 5 0 0 6 4 4 2 1
0 1 2 4 5 6 0 2 0 0 5 6 2 4 6 4 7 6 3 7
7 7 4 2 3 0 0 4 0 0 7 2 7 5 6 1 4 5 5 4
50 50 20 12 18
0 0 0 0 0 0 0 0 0 0 0 0 4 5 0 0 0 0 0 4 2 0 5 2 1 5 3 3 0 0 4 0 5 1 7 2 6 0 7 0 0 0 2 0 0 0 0 0 0 0
6 7 0 0 0 0 0 0 0 0 0 0 4 5 5 3 6 3 0 2 3 3 0 0 5 6 1 5 3 4 7 6 2 2 1 1 6 5 6 4 6 2 0 0 0 0 2 3 1 0
0 2 6 5 7 6 0 0 0 0 0 0 6 2 0 5 6 2 0 4 1 5 0 0 2 0 7 7 0 6 0 6 2 2 4 1 2 2 1 6 6 6 0 2 2 5 0 6 5 0
0 0 0 4 7 2 7 3 7 0 0 0 0 6 7 6 5 1 1 1 2 2 1 3 1 2 7 6 1 2 1 2 4 1 6 1 1 7 3 1 6 6 6 1 1 1 7 0 0 0
0 0 0 5 4 0 6 3 3 7 0 0 0 6 4 3 2 5 3 1 6 1 0 4 1 0 5 7 6 3 1 1 3 6 1 1 6 3 6 7 3 3 6 5 0 7 2 2 4 6
0 6 0 7 6 0 7 4 0 5 3 0 4 3 2 0 5 7 3 0 1 3 6 7 7 5 1 7 5 2 0 5 3 1 3 7 1 1 1 5 2 5 1 3 6 7 7 6 4 3
5 2 0 2 6 5 0 5 6 1 6 5 5 1 7 1 2 3 6 5 1 6 7 7 6 4 1 7 5 2 0 1 3 4 6 4 5 7 2 6 5 6 2 5 6 5 6 5 1 6
1 2 0 7 0 5 5 0 7 6 2 2 1 3 5 5 3 6 3 7 6 4 1 3 1 3 7 0 3 7 0 2 5 6 1 3 4 1 5 1 7 4 1 7 7 0 4 7 5 5
7 6 0 3 5 1 4 0 5 2 5 0 1 3 5 5 4 4 6 1 6 5 7 6 2 1 6 5 5 3 0 5 7 1 1 3 6 2 2 2 4 5 7 4 5 1 1 0 7 3
2 5 4 0 3 1 4 5 6 3 7 0 4 5 3 6 4 5 1 7 4 7 3 1 1 7 7 1 1 5 6 4 7 1 2 6 4 1 7 2 7 1 6 0 5 0 0 0 1 0
3 0 2 5 1 7 1 1 1 6 5 1 3 1 3 1 1 7 1 3 6 5 5 3 1 3 1 6 2 3 2 6 6 1 1 7 5 7 5 7 1 6 0 3 5 1 5 3 0 0
0 0 3 2 0 1 4 1 4 1 0 7 3 2 2 4 2 4 4 6 1 1 1 7 2 4 7 4 3 6 3 5 1 6 1 3 7 7 2 6 3 2 1 0 4 6 2 6 3 0
0 0 5 4 7 2 4 6 4 1 6 7 2 2 1 6 2 1 5 4 7 2 2 1 0 7 6 1 7 2 5 7 0 4 1 6 4 0 3 0 0 5 5 0 7 7 0 3 0 0
0 0 6 4 3 1 3 1 4 7 2 1 2 4 3 4 1 6 2 1 5 1 1 6 0 7 2 7 2 4 7 4 0 3 7 7 3 3 5 2 0 4 3 0 4 2 0 1 3 5
0 1 0 5 6 4 4 6 5 7 0 6 1 4 5 6 2 1 2 4 4 1 1 2 6 1 6 2 0 3 7 3 0 0 5 1 7 6 6 6 1 3 4 2 1 0 7 0 5 5
0 7 2 1 4 2 7 3 0 2 1 4 3 5 1 1 1 1 7 1 4 4 1 7 6 0 1 2 0 5 2 0 0 0 5 4 0 3 7 5 3 1 4 1 2 7 2 6 6 4
0 1 3 0 3 4 6 3 4 2 4 0 7 5 1 1 2 7 1 6 4 2 2 0 5 6 3 3 1 1 0 0 0 3 0 4 5 4 3 1 1 6 1 6 2 0 1 4 7 7
0 3 0 0 2 6 1 4 7 5 1 4 3 2 5 1 4 3 6 3 0 2 4 5 7 5 6 2 0 5 6 3 6 4 6 2 0 0 6 0 7 2 2 6 0 0 0 0 0 0
0 6 7 1 6 4 3 6 0 2 6 7 6 2 1 6 6 6 2 0 0 7 3 0 1 1 2 0 0 0 3 1 6 7 5 6 4 1 7 5 2 0 2 6 0 0 0 0 4 0
0 6 7 7 3 3 0 2 0 1 6 4 1 1 1 6 2 3 3 4 2 3 5 0 5 7 7 6 2 7 2 7 3 1 0 5 6 7 1 6 4 1 5 0 0 0 0 0 0 0
0 7 3 0 4 3 0 0 6 6 0 5 1 1 1 1 1 6 0 0 7 0 0 0 2 4 3 2 3 3 6 0 0 1 0 2 6 7 3 4 0 3 2 4 0 0 0 0 0 7
0 0 4 7 2 0 0 0 1 4 2 4 7 7 2 4 2 4 0 5 6 0 0 0 7 0 2 7 4 4 1 6 1 4 2 3 6 2 0 6 5 3 5 0 3 5 6 0 0 1
0 0 7 4 7 0 3 0 4 4 6 2 4 7 0 5 7 1 3 6 5 6 6 7 3 3 6 6 4 2 0 0 3 0 4 7 2 6 4 0 6 2 4 6 7 1 7 2 7 1
0 0 2 6 0 0 6 5 0 4 1 2 2 2 2 7 2 1 0 4 6 4 1 0 1 1 2 2 0 4 4 2 0 0 3 0 3 6 2 2 7 6 6 0 4 6 0 2 2 2
0 0 4 4 7 1 1 1 7 3 7 6 2 3 3 0 5 0 0 6 1 2 6 3 1 7 0 4 7 4 3 6 1 5 1 0 3 7 4 0 3 0 5 6 2 0 0 3 0 5
0 0 7 3 0 5 4 0 7 4 0 0 4 5 7 1 3 2 3 3 5 3 5 3 5 5 5 5 4 2 3 6 0 3 1 7 2 4 5 3 0 0 5 3 6 0 0 7 3 6
0 0 3 5 0 0 1 1 1 0 0 0 5 3 5 5 1 2 7 0 4 3 1 6 7 1 5 7 4 4 5 7 0 3 6 3 3 7 7 4 1 3 5 2 0 0 0 7 7 4
0 0 7 6 3 5 0 7 2 7 7 5 4 0 0 7 0 4 0 0 3 2 3 1 5 7 4 6 0 3 5 5 2 0 6 0 0 0 2 1 1 4 3 6 2 0 5 1 1 6
0 0 1 0 4 1 0 2 5 0 0 0 6 7 3 7 0 0 0 0 4 3 3 3 0 1 0 0 0 1 5 1 5 4 5 1 7 0 0 5 0 5 6 0 3 2 5 0 3 4
0 0 0 0 0 4 0 2 3 1 6 6 6 3 5 3 6 0 0 0 4 7 0 6 1 7 1 0 0 5 5 2 5 1 0 1 1 3 3 4 1 4 2 0 6 3 0 0 6 4
6 4 2 2 0 0 0 3 3 0 0 1 4 0 5 0 2 0 7 0 1 7 7 1 5 7 0 0 0 3 1 5 5 6 0 6 2 6 4 0 7 6 5 1 3 3 7 0 2 5
0 0 0 7 7 0 0 4 4 3 1 6 1 0 1 3 3 1 4 5 7 3 7 0 0 4 0 0 0 7 3 7 2 2 0 1 5 0 7 5 5 2 5 1 0 2 0 0 3 2
0 0 0 3 0 0 0 0 1 2 6 7 1 6 7 0 3 5 2 7 3 0 4 5 2 0 0 0 0 2 5 7 3 7 5 6 0 0 2 2 5 4 7 6 4 5 1 4 4 6
0 4 3 0 0 0 0 3 5 6 3 2 0 3 6 0 6 0 0 1 4 3 6 2 4 7 4 7 1 5 0 4 0 0 2 0 0 0 3 7 6 1 2 5 3 5 2 3 3 3
0 0 0 1 4 0 0 2 1 0 2 0 0 1 7 3 4 3 3 4 7 0 6 7 4 7 3 1 6 1 7 3 4 4 7 5 2 1 3 7 2 5 2 3 3 2 3 0 1 2
0 0 0 0 1 1 0 0 5 7 3 6 6 0 0 6 5 4 2 7 0 0 4 5 5 0 5 7 3 3 0 3 5 5 3 6 0 0 3 5 4 0 0 7 5 1 6 0 0 7
0 0 0 0 5 6 3 1 5 2 0 7 7 7 0 0 1 0 3 6 4 1 6 7 2 1 6 5 2 0 0 7 4 5 0 0 0 0 0 6 6 0 0 5 6 0 2 3 4 5
0 0 7 1 0 1 6 5 6 0 0 5 4 5 7 1 1 6 5 2 2 0 3 7 4 5 2 6 4 0 0 3 4 0 0 0 0 0 0 7 7 7 7 6 4 3 4 4 0 0
0 0 0 1 3 0 0 3 7 1 1 0 4 1 4 4 2 6 1 6 2 2 7 4 2 4 1 7 1 6 4 3 3 1 3 4 0 0 3 2 0 2 0 1 3 3 4 7 1 5
0 0 0 3 4 0 0 2 0 5 5 0 0 1 4 4 0 4 0 1 6 6 4 2 1 0 0 3 7 0 4 3 3 2 3 5 3 5 0 4 0 5 0 3 0 7 7 3 5 6
0 0 0 7 2 0 0 4 2 2 6 2 2 5 0 5 0 3 4 3 5 5 2 0 4 0 0 5 0 0 4 1 6 4 4 3 4 0 0 5 0 1 1 2 0 7 3 4 0 4
0 0 1 1 4 4 1 0 0 0 3 5 4 5 4 2 7 4 6 1 6 7 0 3 0 7 1 7 6 6 3 0 5 7 6 6 4 7 3 4 5 0 0 0 0 6 1 1 5 3
0 0 4 2 5 7 4 4 2 1 2 1 3 4 7 2 7 2 1 6 3 3 0 7 5 6 6 4 5 5 3 3 2 7 5 3 1 4 7 0 0 0 0 0 0 3 1 5 6 5
0 0 0 4 4 1 0 0 6 0 0 7 5 7 5 1 7 3 6 0 2 4 3 4 7 7 3 0 0 0 1 5 5 0 6 7 7 7 4 4 3 6 3 7 5 0 1 1 0 2
0 0 0 1 3 4 7 2 5 0 0 4 4 0 5 2 2 0 1 7 0 1 1 3 6 5 2 6 2 7 7 3 6 7 1 3 4 6 7 5 3 7 4 6 0 0 0 4 3 1
0 0 0 2 1 6 3 5 4 0 0 6 0 0 6 7 0 0 5 2 0 7 7 0 7 0 0 7 7 6 0 0 1 1 0 1 0 1 3 1 0 0 4 7 7 0 0 0 2 6
0 0 0 2 4 0 6 7 2 4 1 5 6 3 0 0 0 0 4 2 7 1 1 5 2 0 0 7 2 2 3 1 3 5 5 7 7 4 0 3 4 2 3 0 0 4 6 6 0 1
0 0 0 4 6 1 0 3 6 4 7 3 5 0 0 0 0 0 0 7 0 0 3 6 2 1 0 2 3 4 6 7 5 0 7 0 5 4 5 1 5 0 0 0 0 4 5 3 1 0
1 3 6 5 5 2 3 7 6 1 0 6 7 3 2 5 6 7 6 6 0 0 7 1 0 5 5 0 3 0 2 0 7 4 5 3 2 5 1 5 3 0 0 0 1 2 0 1 0 0
1 7 3 0 2 0 7 0 4 6 1 1 5 0 7 0 5 7 7 2 6 0 0 1 0 2 3 3 4 2 7 5 3 7 0 0 4 6 6 6 3 0 0 0 7 7 7 5 7 2

*/