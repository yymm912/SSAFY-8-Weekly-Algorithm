package _3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178_미로탐색 {
	
    static int N,M;
    static int [][] board;
    static int [][] visited;
    static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new int[N][M];
		board = new int[N][M];
		for(int i = 0;i<N;i++) {
			String s = br.readLine();
			for(int j = 0;j<M;j++) {
				board[i][j] = s.charAt(j)-'0';
			}
		}
		
		System.out.println(bfs());
	}
	static class Point{
		int r,c;
		Point(int r,int c){
			this.r = r;
			this.c = c;
		}
	}
	
	static boolean outOfRange(int nr,int nc) {
		return nr<0||nr>=N||nc<0||nc>=M;
	}
	
	static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		visited[0][0] = 1;
		q.add(new Point(0,0));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.r == N-1 && cur.c == M-1)
				return visited[cur.r][cur.c];
			
			for(int i = 0;i<4;i++) {
				int nr = cur.r + d[i][0];
				int nc = cur.c + d[i][1];
				
				//범위 바깥이거나 벽인 경우
				if(outOfRange(nr,nc) || board[nr][nc] == 0)
					continue;
				
				if(visited[nr][nc] == 0) {
					visited[nr][nc] = visited[cur.r][cur.c] + 1;
					q.add(new Point(nr,nc));
				}
			}
			
		}
		return -1;
	}

}
