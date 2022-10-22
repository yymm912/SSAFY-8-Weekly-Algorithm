package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SW7793오나의여신님 {

	static int T;
	static int N, M;
	static char map[][];
	static Dist S, D;
	static List<Dist> devil = new ArrayList<>();
	static Deque<Dist> q = new ArrayDeque<>();
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = {  0, 0,-1, 1 };
	static boolean visit[][];
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			devil.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == 'S') S = new Dist(i, j, 0, 'S');
					if(map[i][j] == 'D') D = new Dist(i, j, 0, 'D');
					if(map[i][j] == '*') devil.add(new Dist(i, j, 0, '*'));
				}
			}
			
			// 악마의손아귀 먼저 큐에 넣기 
			
			for (Dist e : devil) {
				q.offer(e);
				visit[e.y][e.x] = true;
			}
			q.offer(S);
			visit[S.y][S.x] = true;
			
			while(!q.isEmpty()) {
				int q_size = q.size();
				for (int i = 0; i < q_size; i++) {
					Dist e = q.poll();
					if( e.y == D.y && e.x == D.x && e.c == 'S' ) { // 도착지 도달  
						min = Math.min(min, e.depth);
					}
					for (int d = 0; d < 4; d++) {
						int py = e.y + dy[d];
						int px = e.x + dx[d];
						if( py < 0 || px < 0 || py >= N || px >= M || visit[py][px] || map[py][px] == 'X' ) continue;
						if( e.c == '*' && py == D.y && px == D.x ) continue;
						
						q.offer(new Dist(py, px, e.depth + 1, e.c));
						visit[py][px] = true;
					}
				}
				
				
			}
			
			System.out.print("#" + t + " ");
			System.out.println(min == Integer.MAX_VALUE ? "GAME OVER" : min);
			
		} // testcase 
	
		
	}
	
	static class Dist {
		int y, x, depth;
		char c;
		public Dist(int y, int x, int depth, char c) {
			super();
			this.y = y;
			this.x = x;
			this.depth = depth;
			this.c = c;
		}
		
	}

}

/*
2
5 3
D*S
.X.
.X.
.X.
...
5 3
D*S
...
.X.
.X.
...
*/