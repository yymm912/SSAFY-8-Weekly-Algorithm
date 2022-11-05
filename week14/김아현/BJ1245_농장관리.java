package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1245_농장관리 {

	 static int N, M, res;
	 static int[][] map;
	 static boolean[][] visit;
	 
	 static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	 static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	 
	 static class Node {
		 int x, y, h;
		 Node(int x, int y, int h) {
			 this.x = x;
			 this.y = y;
			 this.h = h;
		 }
	 }
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 끝
		
		
		// 모든 격자에 대해 산봉우리인지 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visit[i][j]) continue;
				bfs(i, j, map[i][j]);
			}
		}
		
		System.out.println(res);
	}

	static void bfs(int x, int y, int h) {
		Queue<Node> qu = new ArrayDeque<>();
		qu.offer(new Node(x, y, h));
		visit[x][y] = true;
		boolean maxPoint = true;
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			// 인접한 격자 8방 탐색
			for (int d = 0; d < 8; d++) {
				int tx = n.x + dx[d];
				int ty = n.y + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= M) continue;
				
				// 자신보다 높은 곳이 있으면 산봉우리가 아님
				if(n.h < map[tx][ty])
					maxPoint = false;
				
				// 같은 높이의 격자가 있으면 산봉우리인지 확인 필요
				if(!visit[tx][ty] && map[tx][ty] == n.h) {
					qu.offer(new Node(tx, ty, map[tx][ty]));
					visit[tx][ty] = true;
				}
			}
			
			// 산봉우리이고 더이상 같은 높이의 격자가 없다면 산봉우리 갯수 +1
			if(maxPoint && qu.isEmpty()) res++;
		}
	}
	
}
