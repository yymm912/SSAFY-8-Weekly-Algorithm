import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// visit 엉뚱한 곳에 하진 않았는지 확인하기 ..

public class SWEA4727_견우와직녀 {

	static int T, N, M, res;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dx = {1, 0, -1, 0}; // 하우상좌
	static int[] dy = {0, 1, 0, -1};
	
	static class Node {
		int x, y, t;
		Node (int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 끝
			
			res = Integer.MAX_VALUE;
			
			// 절벽인 곳에 하나씩 오작교 놓아보기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 0) {
						
						map[i][j] = M;
						bfs();
						map[i][j] = 0;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void bfs() {
		Queue<Node> qu = new ArrayDeque<>();
		visit = new boolean[N][N];
		qu.offer(new Node(0, 0, 0));
		visit[0][0] = true;
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			if(n.x == N-1 && n.y == N-1) { // 도착지일 때 최소값 갱신
				res = Math.min(res, n.t);
				return;
			}
			
			if(map[n.x][n.y] == -1) { // 머무르는 중이라면 방문 임시 해제
				map[n.x][n.y] = 1;
				visit[n.x][n.y] = false;
			}
			
			for (int d = 0; d < 4; d++) {
				int tx = n.x + dx[d];
				int ty = n.y + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] == 0 || visit[tx][ty]) continue;
				
				if(map[tx][ty] == 1) { // 바로 이동할 수 있는 길
					visit[tx][ty] = true;
					qu.offer(new Node(tx, ty, n.t + 1));
					
				} else if(map[tx][ty] >= 2 && map[n.x][n.y] == 1) { // 오작교가 놓아진 길
					if((n.t + 1) % map[tx][ty] == 0) { // 이동가능
						visit[tx][ty] = true;
						qu.offer(new Node(tx, ty, n.t + 1));
						
					} else {  // 더 기다리기
						map[n.x][n.y] = -1;
						qu.offer(new Node(n.x, n.y, n.t + 1));
					}
				}
				
				visit[n.x][n.y] = true;
			}
		}
	}
}
