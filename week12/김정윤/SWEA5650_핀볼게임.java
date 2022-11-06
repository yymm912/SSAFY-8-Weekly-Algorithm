package week12.김정윤;

import java.io.*;
import java.util.*;

public class SWEA5650_핀볼게임 {
	static int T, N, ans;
	static int[][] map;
	static List<Pos>[] wormHole;
	static List<Pos> startPoint;
	
	// 상-좌-하-우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N+2][N+2];
			// 시작 가능 지점
			startPoint = new ArrayList<>();
			// 웜홀 생성
			wormHole = new ArrayList[11];
			for (int i = 6; i <= 10; i++) {
				wormHole[i] = new ArrayList<>();
			}
			
			// 1 ~ 5: 블록, 6 ~ 10: 웜홀, -1: 블랙홀
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					int n = Integer.parseInt(st.nextToken());
					map[i][j] = n;
					
					if (n == 0) { // 빈공간일 경우 시작 가능 지점
						startPoint.add(new Pos(i, j));
					}
					
					if (n >= 6) { // 웜홀일 경우
						wormHole[n].add(new Pos(i, j));
					}
				}
			}
			
			ans = 0;
			for (Pos p : startPoint) {
				for (int d = 0; d < 4; d++) {
					pinBole(p.x, p.y, d);
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	static void pinBole(int startX, int startY, int dir) {
		int tmp = 0;
		int x = startX;
		int y = startY;
		
		while (true) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 벽에 부딪힐 경우
			if (nx < 1 || ny < 1 || nx > N || ny > N) {
				dir = (dir + 2) % 4;
				
				tmp++;
				x = nx;
				y = ny;
				continue;
			}
			
			// 기저 조건 : 시작 지점 도달 or 블랙홀
			if (map[nx][ny] == -1 || nx == startX && ny == startY) {
				ans = Math.max(ans, tmp);
				break;
			}
			
			// 웜홀을 만날 경우
			if (map[nx][ny] >= 6) {
				int wn = map[nx][ny];
				for (int i = 0; i < 2; i++) {
					if (nx == wormHole[wn].get(i).x && ny == wormHole[wn].get(i).y) {
						nx = (i == 0) ? wormHole[wn].get(1).x : wormHole[wn].get(0).x;
						ny = (i == 0) ? wormHole[wn].get(1).y : wormHole[wn].get(0).y;
						break;
					}
				}
			}
			x = nx;
			y = ny;
			
			if (map[nx][ny] == 0) continue;
			
			// 블록을 만날 경우
			if (map[nx][ny] >= 1 && map[nx][ny] <= 5) {
				tmp++;
				switch(map[nx][ny]) {
				case 1:
					if (dir == 0) dir = 2;
					else if (dir == 1) dir = 3;
					else if (dir == 2) dir = 1;
					else if (dir == 3) dir = 0;
					break;
				case 2:
					if (dir == 0) dir = 1;
					else if (dir == 1) dir = 3;
					else if(dir == 2) dir = 0;
					else if (dir == 3) dir = 2;
					break;
				case 3:
					if (dir == 0) dir = 3;
					else if (dir == 1) dir = 2;
					else if(dir == 2) dir = 0;
					else if (dir == 3) dir = 1;
					break;
				case 4:
					if (dir == 0) dir = 2;
					else if (dir == 1) dir = 0;
					else if(dir == 2) dir = 3;
					else if (dir == 3) dir = 1;
					break;
				case 5:
					dir = (dir + 2) % 4;
					break;
				}
			}						
		}
	}
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}

}
