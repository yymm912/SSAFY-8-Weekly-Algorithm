package bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA1953_탈주범검거 {

	static int T, N, M, R, C, L, res;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0}; // 상-좌-하-우
	static int[] dy = {0, -1, 0, 1};
	
	static int[][] tunnelType = {
			{0, 0, 0, 0}, // 0 dummy
			{1, 1, 1, 1},
			{1, 0, 1, 0},
			{0, 1, 0, 1},
			{1, 0, 0, 1},
			{0, 0, 1, 1},
			{0, 1, 1, 0},
			{1, 1, 0, 0}
	};
	
	static class Node {
		int x, y, no;
		
		public Node(int x, int y, int no) {
			this.x = x;
			this.y = y;
			this.no = no;
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
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			res = 0;
			map = new int[N][M];
			visit = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			System.out.println("#" + t + " " + res);
		}
	}
	
	static void bfs() {
		Queue<Node> qu = new ArrayDeque<>();
		qu.offer(new Node(R, C, map[R][C]));
		visit[R][C] = true;
		int hour = 1;
		res++;
		
		while(!qu.isEmpty()) {
			if(hour == L) break;
			
			int size = qu.size();
			while(size-- > 0) { // 시간 당 갈 수 있는 위치 갯수
				Node n = qu.poll();
				
				int[] curTunnel = tunnelType[n.no];
				for (int d = 0; d < 4; d++) {
					if(curTunnel[d] == 0) continue; // 해당 터널에서 갈 수 없는 방향 제외
					
					int tx = n.x + dx[d];
					int ty = n.y + dy[d];
					
					if(!isok(tx, ty)) continue;
					
					// 다음에 갈 터널과 현재터널이 이어져 있는지 확인
					if(tunnelType[map[tx][ty]][(d+2)%4] == 1) {
						visit[tx][ty] = true;
						qu.offer(new Node(tx, ty, map[tx][ty]));
						res++;
					}
				}
			}
			
			hour++;
		}
	}
	
	static boolean isok(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= M || map[x][y] == 0 || visit[x][y]) return false;
		return true;
	}
}
