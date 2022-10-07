package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_3190 {
	static int N, K, L, y, x, d, endTime;
	static boolean flag;
	
	static int[][] map;
	static Deque<int[]> q = new ArrayDeque<>();
	
	static int[] dy = {0,1,0,-1};
	static int[] dx = {1,0,-1,0};
	
	static StringTokenizer stk;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N + 1][N + 1];
		
		for(int i = 0, r, c; i < K; i++) {
			stk = new StringTokenizer(br.readLine());
			
			r = Integer.parseInt(stk.nextToken());
			c = Integer.parseInt(stk.nextToken());
			map[r][c] = 1;
		}
		
		// 위치
		y = x = 1;
		
		map[y][x] = -1;
		q.add(new int[] {y,x});
		
		// 방향
		d = 0;
		
		endTime = 0;
		L = Integer.parseInt(br.readLine());
		for(int i = 0; i < L && !flag; i++) {
			stk = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(stk.nextToken());
			char c = stk.nextToken().charAt(0);
		
			endTime = run(x, c);
		}
		
		if(!flag) {
			while(true) {
				endTime++;
				
				int[] head = q.getLast();
				int[] tail = q.getFirst();
				
				int ny = head[0] + dy[d];
				int nx = head[1] + dx[d];
				
				
				if(ny <= 0 || ny > N || nx <= 0 || nx > N || map[ny][nx] == -1) break;
				
				q.add(new int[] {ny, nx});
				
				if(map[ny][nx] != 1) {
					q.poll();
					map[tail[0]][tail[1]] = 0;
				}
				
				map[ny][nx] = -1;
			}
		}
		
		System.out.println(endTime);
	}
	
	static int run(int time, char dir) {
		
		for(int i = endTime; i < time; i++) {
			int[] head = q.getLast();
			int[] tail = q.getFirst();
			
			int ny = head[0] + dy[d];
			int nx = head[1] + dx[d];
			
			
			if(ny <= 0 || ny > N || nx <= 0 || nx > N || map[ny][nx] == -1) {
				flag = true;
				return i + 1;
			}
			
			q.add(new int[] {ny, nx});
			
			if(map[ny][nx] != 1) {
				q.poll();
				map[tail[0]][tail[1]] = 0;
			}
			
			map[ny][nx] = -1;
				
		}
		
		switch(dir) {
		case 'L':
			d = (d + 3) % 4;
			break;
		default:
			d = (d + 1) % 4;
			break;
		}
		
		
		return time;
	}
}
