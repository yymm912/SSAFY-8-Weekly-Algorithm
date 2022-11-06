package bfs;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 1. 2인 좌표에서부터 탐색해서 x=0일 때의 y좌표 구하기 -> 입력받을 때 시작좌표 저장
// 2. 해당 위치에서 왼쪽 또는 오른쪽에 1이 없다면 위로, 있으면 왼쪽 또는 오른쪽으로 이동한다. (좌우이동이 우선)
// 3. 좌 또는 우로 이동하다가 위에 1이 있으면 위로 이동한다.

public class SW1210_Ladder1 {

	static int T, N=100, res;
	static int[][] map;
	static boolean[][] visit;
	static Node start;
	
	static int[] dx = {0, 0, -1}; // 좌우상
	static int[] dy = {-1, 1, 0};
	
	static class Node {
		int x, y, d;
		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", d=" + d + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int t = 1; t <= 10; t++) {
			
			br.readLine();
			
			res = -1;
			map = new int[N][N];
			visit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i == N-1 && map[i][j] == 2)
						start = new Node(i, j, 2);
				}
			} // 입력 끝
			
			
			bfs();
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void bfs() {
		Queue<Node> qu = new ArrayDeque<>();
		qu.offer(start);
		visit[start.x][start.y] = true;
		
		while(!qu.isEmpty()) {
			Node n = qu.poll();
			
			if(n.x == 0) { // 출발점 도착
				res = n.y;
				break;
			}
			
			// 삼방 체크
			for (int d = 0; d < 3; d++) {
				if(!qu.isEmpty()) break; // 다음 이동방향이 정해져 있다면 탐색 중단
				
				int tx = n.x + dx[d];
				int ty = n.y + dy[d];
				
				if(tx < 0 || ty < 0 || tx >= N || ty >= N || map[tx][ty] == 0 || visit[tx][ty]) continue;
				qu.offer(new Node(tx, ty, d));
				visit[tx][ty] = true;
			}
		}
	}
}
