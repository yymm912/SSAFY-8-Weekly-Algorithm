package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA4193수영대회결승전 {

	static int T;
	static int N;
	static int map[][]; // 수영장 
	static int mapCopy[][];
	static boolean visit[][];
	static int A, B, C, D;
	static int ans; // 이동시간 
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			ans = -1;
			
			N = Integer.parseInt(br.readLine());
			mapCopy = new int[N][N];
			map = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					mapCopy[i][j] = Integer.parseInt(st.nextToken());
					if(mapCopy[i][j] == 1 ) visit[i][j] = true;
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 시작위치 A, B 
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			// 도착위치 C, D
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			// 공간 안에서 가장 빠른 길을 찾아야 함
			// 주기적으로 사라졌다 나타나는 소용돌이 같은 장애물 존재
			// 소용돌이는 생성 후 2초 유지, 1초간 잠잠
			// 0초에 생성된 소용돌이 0초, 1초까지 유지되고 2초에 사라지게 됨. 3초 4에는 생성되고 5초에 사라짐 
			// 한 번 통과한 소용돌이 위에서는 머물러 있을 수 있다
			
			
			bfs();
			
			
			
			System.out.println("#" + t +  " " + ans);
			
		} // testcase
		
	}
	
	static void bfs() {
		
		Deque<Dist> q = new ArrayDeque<>();
		
		q.offer(new Dist(A,B));
		visit[A][B] = true;

		
		int time = 0;
		while(!q.isEmpty()) {
			int q_size = q.size();
			
			for (int i = 0; i < q_size; i++) {
				
				Dist e = q.poll();
				
				if( time % 3 == 0 ) {
					copyOfMap(mapCopy);
				}
				
				if(e.y == C && e.x == D) {
					ans = time;
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int py = e.y + dy[d];
					int px = e.x + dx[d];
					if( py < 0 || px < 0 || py >= N || px >= N || visit[py][px] ) continue;
					
					if(map[py][px] > 0) {
						q.offer(new Dist(e.y, e.x));
					}
					else if(map[py][px] == 0) {
						q.offer(new Dist(py, px));
						visit[py][px] = true;
					}
					
				}
				
			}
			time++;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] > 0 && !visit[i][j]) map[i][j]--;
				}
			}
			
		} // while
		
	}
	
	static void copyOfMap(int[][] mapCopy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = mapCopy[i][j];
			}
			
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
3
5
0 0 0 0 0
0 0 0 1 0
0 0 0 1 0
2 2 1 1 0
0 0 0 0 0
4 0
2 0
6
0 0 0 0 0 0
0 1 1 0 0 0
0 0 0 1 2 0
1 1 0 1 0 1
0 0 0 1 0 1
0 0 0 2 0 1
5 0
2 5
6
0 0 0 0 0 0
0 0 0 0 0 0
1 0 1 1 1 0
1 0 0 0 0 0
1 0 1 1 1 0
0 0 2 0 2 0
5 0
3 5
*/