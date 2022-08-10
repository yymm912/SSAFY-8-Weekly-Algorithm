package _3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16234_인구이동 {
	static int N,L,R;
	static boolean [][][] adj;
	static int [][] board;
	static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	static boolean [][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				
			}
		}
		boolean flag = true;
		int answer = 0;
		while(flag) {
			flag = false;
			adj = new boolean[N][N][4];
			visited = new boolean[N][N];
			for(int i = 0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					int cur = board[i][j];
					
					for(int k = 0;k<4;k++) {
						int nr = i+d[k][0];
						int nc = j+d[k][1];
						if(nr<0||nr>=N||nc<0||nc>=N)
							continue;
						if(L<=Math.abs(board[nr][nc]-cur) &&Math.abs(board[nr][nc]-cur)<=R) {
							flag = true;
							adj[i][j][k] = true;
						}
						
					}
					
				}
			}
			for(int i = 0;i<N;i++) 
				for(int j = 0;j<N;j++)
					if(!visited[i][j])
						bfs(i,j);
					
			if(flag) answer++;	
		}
		System.out.println(answer);
	}
	
	static void bfs(int r,int c) {
		Queue<Point> q = new ArrayDeque<>();
		Queue<Point> track = new ArrayDeque<>();
		visited[r][c] = true;
		q.add(new Point(r,c));
		int cnt = 0;
		int sum = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			track.add(new Point(cur.r,cur.c));
			cnt++; sum+=board[cur.r][cur.c];
			for(int i = 0;i<4;i++) {
				int nr = cur.r+d[i][0];
				int nc = cur.c+d[i][1];
				if(nr<0||nr>=N||nc<0||nc>=N)
					continue;
				
				if(!visited[nr][nc]&&adj[cur.r][cur.c][i]) {
					visited[nr][nc] = true;
					q.add(new Point(nr,nc));
				}
			}
		}
		int ave = sum/cnt;
		while(!track.isEmpty()) {
			Point cur = track.poll();
			board[cur.r][cur.c] = ave;
		}
	}
	static class Point{
		int r,c;
		Point(int r,int c){
			this.r = r;
			this.c = c;
		}
	}

}
