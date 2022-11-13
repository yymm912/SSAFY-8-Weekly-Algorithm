package a22_11_12;

import java.io.*;
import java.util.*;

public class BOJ_12100_2048Easy {

	static boolean[][] visited;
	static int[][] map;
	static int[][][] savemap;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int N, ans;
	static Queue<Pos> queue = new ArrayDeque<> ();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		savemap = new int[5][N][N];
		visited = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		System.out.println(ans);
	}
	
	static void dfs(int cnt) {
		if (cnt == 5) {
			check();
			return;
		}
		
		for (int d=0; d<4; d++) {
			save(cnt, d);
			move(d, cnt);
			visitedInit();
			dfs(cnt+1);
			restore(cnt);
		}
	}
	
	static void save(int cnt, int d) {
		// savemap에  이동 전 map을 저장
		if (d == 0 || d==2) { // 상, 좌 : (0,0)시작
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					savemap[cnt][i][j] = map[i][j];
					if (map[i][j] != 0) {
						queue.offer(new Pos(i, j, map[i][j]));
					}
				}
			}
		} else if (d==1) { // 하 : (N-1,0)시작
			for (int i=N-1; i>=0; i--) {
				for (int j=0; j<N; j++) {
					savemap[cnt][i][j] = map[i][j];
					if (map[i][j] != 0) {
						queue.offer(new Pos(i, j, map[i][j]));
					}
				}
			}
		} else if (d==3) { // 우 : (0,N-1)시작
			for (int i=0; i<N; i++) {
				for (int j=N-1; j>=0; j--) {
					savemap[cnt][i][j] = map[i][j];
					if (map[i][j] != 0) {
						queue.offer(new Pos(i, j, map[i][j]));
					}
				}
			}
		}
	}
	
	static void move(int d, int cnt) {
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int ny = cur.y;
			int nx = cur.x;
			int num = cur.num;
			
			while (true) {
				ny += dy[d];
				nx += dx[d];
				if (ny<0 || ny>=N || nx<0 || nx>=N) {
					ny-=dy[d];
					nx-=dx[d];
					break;
				}
				if (map[ny][nx] != 0) break;				
			} //ny, nx가 정해짐
			
			if (!(cur.y == ny && cur.x == nx)) {
				if (map[ny][nx] == 0) {
					map[cur.y][cur.x] = 0;
					map[ny][nx] = num;
				} else if (map[ny][nx] == num) {
					
					if (visited[ny][nx] == false && visited[cur.y][cur.x] == false) { //처음 합쳐짐
						map[cur.y][cur.x] = 0;
						map[ny][nx] = num+num;
						visited[ny][nx] = true;
					} else { //이전에 이미 합쳐짐
						map[cur.y][cur.x] = 0;
						ny-=dy[d];
						nx-=dx[d];
						map[ny][nx] = num;
					}
					
				} else if (map[ny][nx] != num) {
					map[cur.y][cur.x] = 0;
					ny-=dy[d];
					nx-=dx[d];
					map[ny][nx] = num;
				}
			}
		}
	}
	
	static void restore(int cnt) {
		//이동 한 map에  이전에 저장한 savemap을 다시 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = savemap[cnt][i][j];
			}
		}
	}
	
	static void visitedInit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	static void check() {
		int max = 0;
		for (int i=0; i<N; i++) {
			for (int j = 0; j<N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}
		ans = Math.max(max, ans);
	}
	
	static class Pos {
		int y, x, num;
		Pos(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}
}
