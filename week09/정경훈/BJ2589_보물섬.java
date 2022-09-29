package baekjoon.보물섬_2589;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, max;
	static char[][] map;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	static boolean[][] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		
		max = Integer.MIN_VALUE;
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {
					visit = new boolean[N][M];
					visit[i][j] = true;
					bfs(i, j);
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	static void bfs(int y, int x) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(y, x, 0));
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			if(node.cnt > max) max = node.cnt;
			
			for (int i = 0; i < 4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				
				if( ny < 0 || nx < 0 || ny >= N || nx >= M ) continue;
				if(!visit[ny][nx] && map[ny][nx] == 'L') {
					visit[ny][nx] = true;
					q.add(new Node(ny, nx, node.cnt+1));
				}
			}
		}
		
	}
	
	static class Node{
		int y,x,cnt;
		
		public Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
