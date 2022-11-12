import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA4193_수영대회결승전 {

	static int T, N, sec;
	static int[][] sea;
	static boolean[][] visit;
	static Node goal;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Node {
		int x, y, c;
		Node(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			sec = 0;
			sea = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken());
			int sy = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int ex = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			goal = new Node(ex, ey, 0); // 입력 끝
			
			System.out.println("#" + t + " " + bfs(sx, sy));
		}
	}

	static int bfs(int x, int y) {
		Queue<Node> qu = new ArrayDeque<>();
		visit = new boolean[N][N];
		qu.offer(new Node(x, y, 0));
		visit[x][y] = true;
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			if(n.x == goal.x && n.y == goal.y) return n.c;
			
			if(sea[n.x][n.y] == 3) // 머무르고 있다면 visit 임시 풀기
				visit[n.x][n.y] = false;
			
			for (int d = 0; d < 4; d++) {
				int tx = n.x + dx[d];
				int ty = n.y + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || sea[tx][ty] == 1 || visit[tx][ty]) continue;
				
				if(sea[tx][ty] == 2) {
					if((n.c+1)%3 == 0) { // 소용돌이가 잠잠해 졌으면 이동가능
						sea[n.x][n.y] = 0;
						visit[tx][ty] = true;
						qu.offer(new Node(tx, ty, n.c + 1));
					} else {  // 소용돌이 앞에서 머무르기
						sea[n.x][n.y] = 3; // 머무르는 상태 기록
						qu.offer(new Node(n.x, n.y, n.c + 1));
					}
				} else if (sea[tx][ty] == 0) {
					visit[tx][ty] = true;
					qu.offer(new Node(tx, ty, n.c + 1));
				}
				
				visit[n.x][n.y] = true;
			}
		}
		
		return -1;
	}
}
