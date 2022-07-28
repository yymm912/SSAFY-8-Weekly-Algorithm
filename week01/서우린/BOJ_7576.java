package _1주차;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int N,M;
	static int [][] visited;
	static int [][] board;
	static int [][] d = {{-1,0},{0,1},{1,0},{0,-1}};
	static class Cur{
		int r,c;
		Cur(int r,int c){
			this.r = r;this.c = c;
		}
	}
	static Queue<Cur> q = new LinkedList<>();
	static int rest = 0;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new int[N][M];
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					visited[i][j] = 1;
					q.add(new Cur(i,j));
				}else if(board[i][j]==0) {
					rest++;
				}
			}
		}
		
		System.out.println(bfs());
		
	}
	
	public static int bfs() {
		
		while(!q.isEmpty()) {
			Cur cur = q.poll();
			
			for(int i = 0;i<4;i++) {
				int nr = cur.r+d[i][0];
				int nc = cur.c+d[i][1];
				if(nr<0||nr>=N||nc<0||nc>=M)
					continue;
				if(board[nr][nc]!=0)
					continue;
				if(visited[nr][nc]==0) {
					visited[nr][nc] = visited[cur.r][cur.c] + 1;
					answer = Math.max(answer, visited[cur.r][cur.c]);
					rest--;
					q.add(new Cur(nr,nc));
				}
			}
		}
		if(rest==0) return answer;
		else return -1;
	}
}
