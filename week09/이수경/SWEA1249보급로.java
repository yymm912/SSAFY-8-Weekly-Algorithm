package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SWEA1249보급로 {

	static int T, N;
	static int map[][];
	static boolean visit[][];
	static int cost[][];
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static Deque<Dist> q = new ArrayDeque<>();
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visit = new boolean[N][N];
			cost = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(cost[i], Integer.MAX_VALUE);
			}
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			// 입력 완 
			
			// 도로 복구 작업 !
			// 파여진 깊이에 비례해서 복구 시간 증가 
			
			// 출발지 S에서 도착지 G 까지, 도로복구작업.
			
			q.offer(new Dist(0, 0, map[0][0]));
			visit[0][0] = true;
			while(!q.isEmpty()) {
				int q_size = q.size();
				for (int i = 0; i < q_size; i++) {
					Dist e = q.poll();
				
					for (int d = 0; d < 4; d++) {
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						
						
						if(py < 0 || py >= N || px < 0 || px >= N ) continue;
						
						// 더 작을 때만 갱신
						if(  e.v + map[py][px] < cost[py][px] ) { // 기존보다 더 가중치 작을때만
							visit[py][px] = true;
							cost[py][px] = e.v + map[py][px];
							q.offer(new Dist(py, px, e.v + map[py][px]));
								
						}
						
					}
				
				}
				
			}
			
			
			System.out.println("#" + t + " " + cost[N-1][N-1]);
		} // testcase 
		
		
	}
	
	static class Dist {
		int y, x, v;

		public Dist(int y, int x, int v) {
			super();
			this.y = y;
			this.x = x;
			this.v = v;
		}

		
		
	}

}
