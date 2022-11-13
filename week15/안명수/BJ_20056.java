package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_20056 {
	static int N, M, K;
	
	static ArrayList<ArrayList<Queue<fireBall>>> map;
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		map = new ArrayList<>();
		
		for(int i = 0; i <= N; i++)
			map.add(new ArrayList<>());
		
		for(int i = 0; i <= N; i++) {
			for(int j = 0; j <= N; j++) {
				map.get(i).add(new ArrayDeque<fireBall>());
			}
		}
		
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			
			
			fireBall fb = new fireBall(r,c,
					Integer.parseInt(stk.nextToken()),
					Integer.parseInt(stk.nextToken()),
					Integer.parseInt(stk.nextToken())
					);
			
			map.get(r).get(c).add(fb);
		}
		
		
		while(K > 0) {
			
			//// 파이어볼 이동
			
			// 모든 파이어볼 모으기
			Queue<fireBall> q = new ArrayDeque<>();
			
			// 파이어볼 찾기
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					Queue<fireBall> tmp = map.get(i).get(j);
					while(!tmp.isEmpty()) {
						q.add(tmp.poll());
					}
				}
			}
			
			// 모은 파이어볼 날리기
			while(!q.isEmpty()) {
				fireBall now = q.poll();
				
				now.r += dy[now.d]*(now.s % N);
				now.c += dx[now.d]*(now.s % N);
				
				if(now.r > N) now.r -= N;
				if(now.r < 1) now.r += N;
				if(now.c > N) now.c -= N;
				if(now.c < 1) now.c += N;
				
				map.get(now.r).get(now.c).add(now);
			}
			
			
			
			
			//// 파이어볼 나누기
			// 2개 이상의 파이어볼이 모인 칸 찾기
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					Queue<fireBall> tmp = map.get(i).get(j);
					if(tmp.size() > 1) {
						fireBall big = new fireBall(i,j,0,0,0);
						int size = tmp.size();
						
						int odd = 0, even = 0;
						while(!tmp.isEmpty()) {
							fireBall now = tmp.poll();
							big.m += now.m;
							big.s += now.s;
							
							if(now.d % 2 == 1) odd++;
							else even++;
						}
						
						if(big.m < 5) continue;
						big.m /= 5;
						big.s /= size;
						
						if(even == 0 || odd == 0) {
							for(int d = 0; d < 8; d+=2)
								tmp.add(new fireBall(i,j,big.m,big.s,d));
						}else {
							for(int d = 1; d < 8; d+=2)
								tmp.add(new fireBall(i,j,big.m,big.s,d));
						}
					}
				}
			}
			
			K--;
		}
		
		int sumMess = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				Queue<fireBall> tmp = map.get(i).get(j);
				while(!tmp.isEmpty()) {
					sumMess += tmp.poll().m;
				}
			}
		}
		
		System.out.println(sumMess);
	}
	
	static class fireBall{
		int r, c, m, s, d;
		public fireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
