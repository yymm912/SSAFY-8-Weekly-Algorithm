package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
	
public class BJ17070파이프옮기기 {
	
	static int N;
	static int map[][];
	static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map  = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		// 입력 완
		
		
		// \   :(y+1, x) (y, x+1) , (y+1, x+1) 3칸이 비어있어야 함
		// ㅡ  : ( y, x+1 ) 비어있어야
		// |   : ( y+1, x ) 비어있어야
		
		// 45도 까지만 방향 전환 가능.
		// ㅡ : ㅡ  \
		// ㅣ : ㅣ  \
		// \ : ㅡ ㅣ \
		
		dfs(0, 0, 0, 1);
		
		
		System.out.println(cnt);
		
	}
	
	static void dfs(int start_y, int start_x, int end_y, int end_x) {
		
		
			// 목적지 도달하면 종료 
			if(end_y == N-1 && end_x == N-1) {
				cnt++;
				return;
			}
			
			
			// 가로 방향일 때
			if(start_y == end_y && start_x + 1 == end_x) {
				 				// 갈 수 있는 길인지 판단

				// 오른쪽 방향이 비어있으면 
				if(end_x + 1 < N) {
					if( map[end_y][end_x + 1] == 0 ) {
						dfs( end_y, end_x, end_y, end_x + 1 );
						}	
					}
				
				
				
				// 아래쪽, 오른쪽, 대각선 방향이 비어있으면
				if( end_y + 1 < N && end_x + 1 < N ) {
					if( map[end_y+1][end_x] == 0 && map[end_y][end_x+1] == 0 && map[end_y+1][end_x+1] == 0 ) {
						dfs( end_y, end_x, end_y + 1, end_x + 1 );
					}	
				}
				
			}
			
			// 세로 방향일 때
			if(start_y+1 == end_y && start_x == end_x) {
				// 아래쪽 방향이 비어있으면
				if(end_y + 1 < N) {
					if( map[end_y + 1][end_x] == 0 ) {
						dfs( end_y, end_x, end_y + 1, end_x );
					}	
				}
				
				// 아래쪽, 오른쪽, 대각선 방향이 비어있으면
				if( end_y + 1 < N && end_x + 1 < N ) {
					if( map[end_y+1][end_x] == 0 && map[end_y][end_x+1] == 0 && map[end_y+1][end_x+1] == 0 ) {
						dfs( end_y, end_x, end_y + 1, end_x + 1 );
					}	
				}
				
			}
			
			
			// 대각선 방향일 때 
			if(start_y+1 == end_y && start_x+1 == end_x) {
				// 오른쪽 방향이 비어있으면 
				if(end_x + 1 < N) {
					if( map[end_y][end_x + 1] == 0 ) {
						dfs( end_y, end_x, end_y, end_x + 1);
					}	
				}
				
				// 아래쪽 방향이 비어있으면
				if(end_y + 1 < N) {
					if( map[end_y + 1][end_x] == 0 ) {
						dfs( end_y, end_x, end_y + 1, end_x );

					}
				}

				// 아래쪽, 오른쪽, 대각선 방향이 비어있으면
				if( end_y + 1 < N && end_x + 1 < N ) {
					if( map[end_y+1][end_x] == 0 && map[end_y][end_x+1] == 0 && map[end_y+1][end_x+1] == 0 ) {
						dfs( end_y, end_x, end_y + 1, end_x + 1 );
					}
				}
				
			}
		
	}
	
	static class Dist{
		int y, x;

		public Dist(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}

}
