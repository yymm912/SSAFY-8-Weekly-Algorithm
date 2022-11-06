package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238 {
	static int N, M, fuel, dist;
	static int[][] map;
	static int[][] valueMap;
	static boolean[] visit;

	
	
	static int[][] tgt;
	static int[] texi = new int[2];
	
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		fuel = Integer.parseInt(stk.nextToken());
		
		
		map = new int[N][N];
		valueMap = new int[N][N];
		tgt = new int[M][4];
		for(int i = 0; i < N; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] == 1) map[i][j] = -1;
				else map[i][j] = Integer.MAX_VALUE;
			}
		}
		
		stk = new StringTokenizer(br.readLine());
		texi[0] = Integer.parseInt(stk.nextToken()) - 1;
		texi[1] = Integer.parseInt(stk.nextToken()) - 1;
		
		for(int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			tgt[i][0] = Integer.parseInt(stk.nextToken()) - 1;	
			tgt[i][1] = Integer.parseInt(stk.nextToken()) - 1;	
			tgt[i][2] = Integer.parseInt(stk.nextToken()) - 1;	
			tgt[i][3] = Integer.parseInt(stk.nextToken()) - 1;	
		}
		
		visit = new boolean[M];
		while(true) {
			calcValue();
			
			int idx = -1;
			int value = Integer.MAX_VALUE;
			for(int i = 0; i < M; i++) {
				if(visit[i]) continue;
				if(idx == -1 || value > valueMap[tgt[i][0]][tgt[i][1]]) {
					idx = i;
					value = valueMap[tgt[i][0]][tgt[i][1]];
				}else if(value == valueMap[tgt[i][0]][tgt[i][1]]) {
					if(tgt[i][0] < tgt[idx][0] || tgt[i][0] == tgt[idx][0] && tgt[i][1] < tgt[idx][1]) {
						idx = i;
						value = valueMap[tgt[i][0]][tgt[i][1]];
					}
				}
			}
			
			if(idx == -1) break;
			visit[idx] = true;
			if(fuel >= value) {
				texi[0] = tgt[idx][0];
				texi[1] = tgt[idx][1];
				
				fuel -= value;
				
				if(valueMap[tgt[idx][2]][tgt[idx][3]] != Integer.MAX_VALUE) {
					calcValue();
					
					if(fuel >= valueMap[tgt[idx][2]][tgt[idx][3]]) {
						fuel += valueMap[tgt[idx][2]][tgt[idx][3]];
						texi[0] = tgt[idx][2];
						texi[1] = tgt[idx][3];
						continue;
					}
				}
			}
			
			fuel = -1;
			break;
		}
		
		System.out.println(fuel);
	}
	
	
	public static void calcValue() {
		for(int i = 0; i < N; i++) {
			valueMap[i] = map[i].clone();
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {texi[0], texi[1], 0});
		valueMap[texi[0]][texi[1]] = 0;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int i = 0; i < 4; i++) {
				int ny = now[0] + dy[i];
				int nx = now[1] + dx[i];
				if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if(valueMap[ny][nx] == -1 || valueMap[ny][nx] <= now[2] + 1) continue;
				valueMap[ny][nx] = now[2] + 1;
				q.add(new int[] {ny,nx,now[2] + 1});
			}
		}
	}
	
}
