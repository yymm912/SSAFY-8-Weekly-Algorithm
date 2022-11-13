package swea.혁진이의프로그램검증_1824;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T, R, C;
	static char[][] map;
	
	static boolean ok, go;
	static boolean[][][][] visit;
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			go = false;
			ok = false;
			visit = new boolean[4][16][R][C];
			
			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if(map[i][j] == '@') {
						go = true;
						break;
					}
				}
				
			}
			
			if(go) {
				dfs(0,0,3,0);
			}
			
			
			System.out.println("#" + t + " " + (ok ? "YES" : "NO"));
			
		}
	}
	
	static void dfs(int y, int x, int d, int memory) {
		if(map[y][x] == '@') {
			ok = true;
			return;
		}
		if(ok) {
			return;
		}
		if(visit[d][memory][y][x]) {
			return;
		}
		visit[d][memory][y][x] = true;
		
		int ny = y;
		int nx = x;
		int nd = d;
		int nm = memory;
	
		if(map[y][x] == '<') {
			nd = 2;
		}else if(map[y][x] == '>') {
			nd = 3;
		}else if(map[y][x] == '^') {
			nd = 0;
		}else if(map[y][x] == 'v') {
			nd = 1;
		}else if(map[y][x] == '_') {
			nd = nm == 0 ? 3 : 2;
		}else if(map[y][x] == '|') {
			nd = nm == 0 ? 1 : 0;
		}else if(map[y][x] >= '0' && map[y][x] <= '9') {
			nm = map[y][x]-'0';
		}else if(map[y][x] == '-') {
			if(nm == 0) nm = 15;
			else nm--;
		}else if(map[y][x] == '+') {
			if(nm == 15) nm = 0;
			else nm++;
		}
		if(map[y][x] != '?') {
			ny = y + dy[nd];
			nx = x + dx[nd];
			if(ny < 0) ny = R-1;
			else if(ny >= R) ny = 0;
			else if(nx < 0) nx = C-1;
			else if(nx >= C) nx = 0;
			dfs(ny, nx, nd, nm);
		}else {
			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];
				if(ny < 0) ny = R-1;
				else if(ny >= R) ny = 0;
				else if(nx < 0) nx = C-1;
				else if(nx >= C) nx = 0;
				dfs(ny, nx, i, nm);
			}
		}
	}
}
