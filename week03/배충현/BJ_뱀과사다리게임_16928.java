package ssafy.algorithm.study;

import java.io.*;
import java.util.*;

public class BJ_뱀과사다리게임_16928_2 {
	
	static int N, M;
	static int[] board, visited; 
	static Deque<P> dq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		board = new int[101]; 
		visited = new int[101]; 
		Arrays.fill(visited, 100);
		dq = new ArrayDeque<>();
		
		// 사다리 정보 x --> y
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			board[Integer.parseInt(stk.nextToken())] = Integer.parseInt(stk.nextToken());
		}
		
		// 뱀 정보 u --> v
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			board[Integer.parseInt(stk.nextToken())] = Integer.parseInt(stk.nextToken());
		}
		
		dq.addLast(new P(1, 0));
		bfs();
		
	}
	
	static void bfs() {
		
		while (!dq.isEmpty()) {
			P cur = dq.peekFirst();
			int cp = cur.position;
			int cc = cur.cnt;
			if (cp == 100) {
				System.out.println(cc);
				return;
			}
			
			for (int i = 1; i <= 6; i++) {
				int np = cp + i;
				int nc = cc + 1;
				if (np > 100) break;
				if (board[np] != 0) np = board[np];
				if (visited[np] > nc) { 
					visited[np] = nc;
				} else continue;
				
				dq.addLast(new P(np, nc));
			}
			
			dq.removeFirst();
		}
	}
	
	static class P {
		int position;
		int cnt;
		
		public P(int p, int c) {
			this.position = p;
			this.cnt = c;
		}
	}
	
}
