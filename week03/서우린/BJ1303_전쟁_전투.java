package _3주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1303_전쟁_전투 {
	static int N,M;
	static char [][] board;
	static boolean [][] visited;
	static int [] sum;
	static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new boolean[N][M];
		sum = new int[2];
		for(int i = 0;i<N;i++) {
			String s = br.readLine();
			for(int j = 0;j<M;j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				if(visited[i][j])
					continue;
				
				bfs(i,j,board[i][j]);
			}
		}
		System.out.println(sum[0]+" "+sum[1]);

	}
	static class Point{
		int r,c;
		Point(int r,int c){
			this.r = r;
			this.c = c;
		}
	}
	static void bfs(int r,int c, char t) {
		int team = t == 'W'? 0:1;
		int num = 0;
		Queue<Point> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.add(new Point(r,c));
		while(!q.isEmpty()) {
			Point cur = q.poll();
			num++;
			for(int i = 0;i<4;i++) {
				int nr = cur.r + d[i][0];
				int nc = cur.c + d[i][1];
				
				if(nr<0||nr>=N||nc<0||nc>=M)
					continue;
				if(board[nr][nc] != t)
					continue;
				if(!visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Point(nr,nc));
				}
			}
		}
		sum[team] += (num*num);
	}

}
