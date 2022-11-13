package a22_11_11;

import java.io.*;
import java.util.*;

public class SW_1868_파핑파핑지뢰찾기 {

	static int N, ans;
	static char[][] map;
	static boolean is;
	
	static int[] dy = {-1,-1,-1, 0,0, 1,1,1};
	static int[] dx = {-1,0,1, -1,1, -1,0,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			
			for (int i=0; i<N; i++) {
				String s = br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j] = s.charAt(j);
				}
			} //입력 끝

			ans = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					if (map[i][j] == '.') {
						is = false;
						bfs(i, j);
						if (is == true) ans++;
					}
				}
			}
			
			for (int i=0; i<N; i++) {
				for (int j = 0; j<N; j++) {
					if (map[i][j] == '.') ans++;
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void bfs(int y, int x) {
		Queue<Pos> queue = new ArrayDeque<> ();
		queue.offer(new Pos(y, x));
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			
			boolean state = true;
			for (int d=0; d<8; d++) {
				int ny = cur.y+dy[d];
				int nx = cur.x+dx[d];
				if (ny<0 || ny>=N || nx<0 || nx>=N) continue;
				if (map[ny][nx] == '*') {
					state = false;
					break;
				}
			}
			if (state == true) {
				is = true;
				map[cur.y][cur.x] = 'o';
				
				for (int d=0; d<8; d++) {
					int ny = cur.y+dy[d];
					int nx = cur.x+dx[d];
					if (ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx] == 'o') continue;
					map[ny][nx] = 'o';
					
					boolean state2 = true;
					for (int nd=0; nd<8; nd++) {
						int nny = ny+dy[nd];
						int nnx = nx+dx[nd];
						if (nny<0 || nny>=N || nnx<0 || nnx>=N || map[nny][nnx] == 'o') continue;
						if (map[nny][nnx] == '*') {
							state2 = false;
							break;
						}
					}
					if (state2 == true) {
						queue.offer(new Pos(ny, nx));
					}
				}
			}
		}
	}
	
	static class Pos {
		int y, x;
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
