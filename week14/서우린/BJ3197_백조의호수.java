package _14주차;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ3197_백조의호수 {
	static int R,C;
	static char [][] board;
	static int [][] visited;
	static Queue<Point> q = new ArrayDeque<>();
	static List<Point> coord = new ArrayList<>();
	static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new int[R][C];
		for(int i = 0;i<R;i++) {
			String s = br.readLine();
			board[i] = s.toCharArray();
			for(int j = 0;j<C;j++) {
				if(board[i][j] == '.') {
					q.add(new Point(j,i));
					visited[i][j] = 1;
				}
				if(board[i][j] == 'L') {
					q.add(new Point(j,i));
					visited[i][j] = 1;
					coord.add(new Point(j,i));
				}
			}
		}
		int hi = bfs();
		
		int lo = 0;
		
		while(lo <= hi) {
			int mid = (lo+hi)/2;
			
			if(solve(mid+1)) {
				hi = mid - 1;
			}else {
				lo = mid + 1;
			}
		}
		System.out.println(lo);
	}
	static boolean solve(int k) {
		q.clear();
		boolean [][] visit = new boolean[R][C];
		q.add(coord.get(0));
		visit[coord.get(0).y][coord.get(0).x] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if(cur.y == coord.get(1).y && cur.x == coord.get(1).x)
				return true;
			for(int i = 0;i<4;i++) {
				int nr = cur.y + d[i][0];
				int nc = cur.x + d[i][1];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;
				if(visited[nr][nc] > k) continue;
				if(!visit[nr][nc]) {
					visit[nr][nc] = true;
					q.add(new Point(nc,nr));
				}
				
			}
		}
		return false;
	}
	static int bfs() {
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int i = 0;i<4;i++) {
				int nr = cur.y + d[i][0];
				int nc = cur.x + d[i][1];
				
				if(nr < 0 || nr >= R || nc < 0 || nc >= C)
					continue;
				if(visited[nr][nc] == 0 || visited[nr][nc] > visited[cur.y][cur.x] + 1) {
					visited[nr][nc] = visited[cur.y][cur.x] + 1;
					q.add(new Point(nc,nr));
				}
			}
		}
		int ret = 0;
		for(int i = 0;i<R;i++) {
			for(int j = 0;j<C;j++) {
				ret = Math.max(ret, visited[i][j]);
			}
		}
		return ret-1;
	}

}
