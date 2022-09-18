package ssafy.algorithm.study.w06;

import java.io.*;
import java.util.*;

public class SW_탈주범검거_1953 {

	static StringBuilder sb = new StringBuilder();
	static int T, N, M, R, C, L, ans, INF=987654321;
	static int[][] input, visited, dt = {{-1, 0}, {0, 1}, {0, -1}, {1, 0}};
	static List[] pdt = {
			new ArrayList<>(),	// 0 dummy
			Arrays.asList(0, 1, 2, 3),
			Arrays.asList(0, 3),
			Arrays.asList(1, 2),
			Arrays.asList(0, 1),
			Arrays.asList(3, 1),
			Arrays.asList(3, 2),
			Arrays.asList(0, 2),
		};
	static Deque<int[]> dq;
	
	static void solve() {
		dq = new ArrayDeque<>();
		visited[R][C] = 1;
		ans++;
		dq.addLast(new int[] {R, C, 1});
		
		while(!dq.isEmpty()) {
			int[] cur = dq.removeFirst();
			if (cur[2] == L) return;
			int dd = input[cur[0]][cur[1]];
			List<Integer> dl = pdt[dd];
			
			for(int d : dl) {
				int ny = cur[0] + dt[d][0];
				int nx = cur[1] + dt[d][1];
				int nt = cur[2] + 1;
				
				if (ny<0 || nx<0 || ny>=N || nx>=M) continue;
				if (input[ny][nx] == 0) continue;
				if (visited[ny][nx] <= nt) continue;
				if (!(pdt[input[ny][nx]].contains(3-d))) continue;
				
				visited[ny][nx] = nt;
				ans++;
				dq.addLast(new int[] {ny, nx, nt});
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			R = Integer.parseInt(stk.nextToken());
			C = Integer.parseInt(stk.nextToken());
			L = Integer.parseInt(stk.nextToken());
			input = new int[N][M];
			visited = new int[N][M];
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], INF);
			}
			
			for(int i=0; i<N; i++) {
				stk = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					input[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			
			ans = 0;
			solve();
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		
		System.out.print(sb);
		
	}
}
