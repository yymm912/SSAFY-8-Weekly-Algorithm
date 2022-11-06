package p221026;

import java.io.*;
import java.util.*;

public class SW_1227_미로2 {

	static int[][] map;
	static int ans, sy, sx, ey, ex;
	
	static int[] dy= {-1,1,0,0};
	static int[] dx= {0,0,-1,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int tc=1; tc<=10; tc++) {
			int T = Integer.parseInt(br.readLine());
			//1:벽 0:길 2:출발 3:도착
			map = new int[100][100];
			visited = new boolean[100][100];
			for (int i=0; i<100; i++) {
				String s = br.readLine();
				for (int j=0; j<100; j++) {
					map[i][j] = s.charAt(j) - '0';
					if (map[i][j] == 2) {
						sy = i;
						sx = j;
					} else if (map[i][j] == 3) {
						ey = i;
						ex = j;
					}
				}
			} //입력 끝
			
			ans = 0;
			dfs(sy, sx);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void dfs(int y, int x) {
		visited[y][x] = true;
		if (y==ey && x==ex) {
			ans = 1;
			return;
		}
		
		for (int d=0; d<4; d++) {
			int ny= y+dy[d];
			int nx= x+dx[d];
			if (ny<0 || ny>=100 || nx<0 || nx>=100 || visited[ny][nx]) continue;
			if (map[ny][nx] == 1) continue;
			dfs(ny, nx);
		}
	}
}
