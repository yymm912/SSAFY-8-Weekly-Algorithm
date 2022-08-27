package ssafy.algorithm.study.w04;

import java.io.*;
import java.util.*;

// (1, 1) --> (N, M) 으로 가는데 부수어야 하는 벽의 최소 수 --> bfs
public class BJ_알고스팟_1261 {
	
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] input, visited, dt = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static Deque<int[]> dq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		stk = new StringTokenizer(br.readLine());
		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		input = new int[N+1][M+1];
		visited = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(visited[i], 10000);
		}
		
		dq = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 1; j <= M; j++) {
				input[i][j] = tmp[j-1] - '0';
			}
		}
		
		bfs();
		
		System.out.println(visited[N][M]);
		
	}
	
	static void bfs() {
		dq.clear();
		visited[1][1] = 0;
		dq.addLast(new int[] {1, 1, 0});
		
		while (!dq.isEmpty()) {
			int[] cur = dq.removeFirst();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur[0] + dt[d][0];
				int nx = cur[1] + dt[d][1];
				
				if (ny<1 || nx<1 || ny>N || nx>M) continue;
				if (visited[ny][nx] > cur[2]) visited[ny][nx] = cur[2];
				else continue;
				if (input[ny][nx] == 1) {
					dq.add(new int[] {ny, nx, cur[2]+1});
				} else {
					dq.add(new int[] {ny, nx, cur[2]});
				}
			}
		}
	}
	
}
