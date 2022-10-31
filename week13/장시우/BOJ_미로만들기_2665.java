package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class BOJ_미로만들기_2665 {
	
	static int n;
	static char[][] map;
	
	static int[][] dist;
	
	static Queue<Node> q = new ArrayDeque<>();
	
	// delta
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new char[n][];
		dist = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		bfs(0, 0);
		
		System.out.println(dist[n - 1][n - 1]);
	}
	
	static void bfs(int i, int j) {
		q.add(new Node(i, j));
		dist[0][0] = 0;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if (nx >= 0 && nx < n && ny >= 0 && ny < n && dist[nx][ny] > dist[node.x][node.y]) {
					if (map[nx][ny] == '1') {
						dist[nx][ny] = dist[node.x][node.y];
					} else {
						dist[nx][ny] = dist[node.x][node.y] + 1; 
					}
					
					q.add(new Node(nx, ny));
				}
			}
		}
	}
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
