package ssafy.algorithm.study;

import java.io.*;
import java.util.*;

public class TC_음료수얼려먹기2 {
	
	static int N, M, total;
	static int[][] input, dt = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static Deque<Ice> dq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		input = new int[N][M];

		// 입력 받기
		for (int i = 0; i < N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				input[i][j] = tmp[j] - '0';
			}
		}
		
		// 초기화
		visited = new boolean[N][M];
		dq = new ArrayDeque<>();
		total = 0;
		
		// 순회
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (input[i][j] == 0 && !visited[i][j]) {
					dfs(i, j);
					total++;
				}
			}
		}
		
		System.out.println(total);
	}
	
	static void dfs(int y, int x) {
		visited[y][x] = true;
		dq.addLast(new Ice(y, x));
		
		while (!dq.isEmpty()) {
			Ice cur = dq.peekFirst();
			int cy = cur.y;
			int cx = cur.x;
			
			for (int d = 0; d < 4; d++) {
				int ny = cy + dt[d][0];
				int nx = cx + dt[d][1];
				
				if (nx<0 || ny<0 || nx>=M || ny>=N) continue;
				if (visited[ny][nx] || input[ny][nx] == 1) continue;
				
				visited[ny][nx] = true;
				dq.addLast(new Ice(ny, nx));
			}
			dq.removeFirst();
		}
	}
	
	static class Ice {
		int y;
		int x;
		
		public Ice(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
