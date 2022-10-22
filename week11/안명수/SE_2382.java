package algo.SE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SE_2382 {
	static int N, M, K;
	static int[][] map;
	static int[][] valMap;
	static Tmp[] tmpArr;
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t <= T; t++) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			K = Integer.parseInt(stk.nextToken());
			
			map = new int[N][N];
			valMap = new int[N][N];
			tmpArr = new Tmp[K + 1];
			for(int i = 1; i <= K; i++) {
				stk = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(stk.nextToken());
				int c = Integer.parseInt(stk.nextToken());
				int val = Integer.parseInt(stk.nextToken());
				int dir = Integer.parseInt(stk.nextToken());
				
				map[r][c] = i;
				tmpArr[i] = new Tmp(r,c,val,dir - 1);
			}
			
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++){
					Arrays.fill(valMap[j], 0);
					Arrays.fill(map[j], 0);
				}
				
				// 이동
				for(int j = 1; j <= K; j++) {
					if(tmpArr[j] == null) continue;
					Tmp tmp = tmpArr[j];
					tmp.y += dy[tmp.dir];
					tmp.x += dx[tmp.dir];
					
					if(tmp.y == 0 || tmp.y == N - 1 || tmp.x == 0 || tmp.x == N-1) {
						tmp.val /= 2;
						if(tmp.val == 0) {
							tmpArr[j] = null;
							continue;
						}
						if(tmp.dir < 2) {
							tmp.dir = tmp.dir == 0 ? 1 : 0;
						}else {
							tmp.dir = tmp.dir == 2 ? 3 : 2;
						}
					}
				}
				
				// 통합
				for(int j = 1; j <= K; j++) {
					if(tmpArr[j] == null) continue;
					Tmp tmp = tmpArr[j];
					if(map[tmp.y][tmp.x] == 0) {
						map[tmp.y][tmp.x] = j;
						valMap[tmp.y][tmp.x] = tmp.val;
						continue;
					}
					

					valMap[tmp.y][tmp.x] += tmp.val;
					
					int there = map[tmp.y][tmp.x];
					if(tmp.val > tmpArr[there].val) {
						map[tmp.y][tmp.x] = j;
						tmpArr[there] = null;
					}else if(tmp.val < tmpArr[there].val){
						tmpArr[j] = null;
					}
				}
				
				for(int j = 1; j <= K; j++) {
					if(tmpArr[j] == null) continue;
					Tmp tmp = tmpArr[j];
					tmp.val = valMap[tmp.y][tmp.x];
				}
			}
			
			int sum = 0;
			for(int i = 1; i <= K; i++) {
				if(tmpArr[i] == null) continue;
				sum += tmpArr[i].val;
			}
			sb.append('#').append(t).append(' ').append(sum).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static class Tmp{
		int y, x, val, dir;

		public Tmp(int y, int x, int val, int dir) {
			this.y = y;
			this.x = x;
			this.val = val;
			this.dir = dir;
		}
		
	}
}
