package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_알고스팟_1261 {
	
	static int N, M;
	static int[][] map;
	
	// dijkstra
	static PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.d - e2.d);
	static int[][] cost;
	static boolean[][] checked;
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		cost = new int[N][M];
		checked = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dijkstra();
		
		System.out.println(cost[N - 1][M - 1]);
	}
	
	static void dijkstra() {
		cost[0][0] = 0;
		pq.add(new Edge(0, 0, 0));
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if (checked[e.x][e.y] || e.d > cost[e.x][e.y]) continue;
			
			checked[e.x][e.y] = true; 
			
			for (int d = 0; d < 4; d++) {
				int nx = e.x + dx[d];
				int ny = e.y + dy[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !checked[nx][ny]) {
					if (map[nx][ny] == 1) cost[nx][ny] = Math.min(cost[nx][ny], cost[e.x][e.y] + 1);
					else if (map[nx][ny] == 0) cost[nx][ny] = cost[e.x][e.y];
					pq.add(new Edge(nx, ny, cost[nx][ny]));
				}
			}
		}
	}
	
	static class Edge {
		int x;
		int y;
		int d;
		
		public Edge (int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
