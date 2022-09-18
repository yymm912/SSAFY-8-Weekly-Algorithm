

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638_치즈 {

	static int N, M, cnt, startX, startY;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][M+2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			//2.다음 시작 위치 찾기
			if (!find_start()) break; //false -> 1이 없다, 치즈 다녹았다
			
			//1.외부 공기 표시
			back_bfs(1, M);
			//testPrint();
			//3. bfs
			cheese_bfs(startY, startX);
			cnt++;
		}
		System.out.println(cnt);
	}
	
	static void cheese_bfs(int y, int x) {
		Queue<Node> queue = new ArrayDeque<> ();
		Queue<Node> change = new ArrayDeque<> ();
		visited = new boolean[N+1][M+1];
		int c = 0;
		
		queue.offer(new Node(y, x));
		visited[y][x] = true;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny <= 0 || nx <= 0 || ny > N || nx > M ||visited[ny][nx]) continue;
			if (map[ny][nx] == 9) c++;
		}
		if (c >= 2) 
			change.offer(new Node(y, x));
		
		while (!queue.isEmpty()) {
			c = 0;
			Node n = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ny = n.y + dy[d];
				int nx = n.x + dx[d];
				if (ny <= 0 || nx <= 0 || ny > N || nx > M || visited[ny][nx]) continue;
				if (map[ny][nx] == 9) c++;
				if (map[ny][nx] != 9 && !visited[ny][nx]) queue.offer(new Node(ny, nx));
			}
			visited[n.y][n.x] = true;
			if (c >= 2) 
				change.offer(new Node(y, x));
			
		}
	}
	
	static void back_bfs(int y, int x) {
		Queue<Node> queue = new ArrayDeque<> ();
		visited = new boolean[N+1][M+1];
		
		queue.offer(new Node(y, x));
		visited[y][x] = true;
		map[y][x] = 9;
		
		while (!queue.isEmpty()) {
			Node n = queue.poll();
			for (int d = 0; d < 4; d++) {
				int ny = n.y + dy[d];
				int nx = n.x + dx[d];
				if (ny <= 0 || nx <= 0 || ny > N || nx > M || visited[ny][nx]) continue;
				if (map[ny][nx] != 0) continue;
				visited[ny][nx] = true;
				queue.offer(new Node(ny, nx));
				map[ny][nx] = 9;
			}
		}
		
	}
	
	static boolean find_start() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 1) {
					startX = j;
					startY = i;
					return true;
				}
			}
		}
		return false;
	}
	
	static class Node {
		int y, x;
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static void testPrint() {
		System.out.println("===========");
		for (int i = 0; i <= N+1; i++) {
			for (int j = 0; j <= M+1; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("===========");
	}
}
