package _1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임개발 {
	static int N,M;
	static int [][] board;
	static int [][] visited;
	static int r,c,d;
	static int [][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new int[N][M];
		visited[r][c] = 1;
		int answer = 1;
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<M;j++) {
				board[i][j] = st.nextToken().charAt(0)-'0';
			}
		}
		
		while(true) {
			boolean go = false;
			for(int i = d+1;i<d+4;i++) {
				int nr = r+dir[i%4][0];
				int nc = c+dir[i%4][1];
				if(nr<0||nr>=N||nc<0||nc>=M)
					continue;
				if(board[nr][nc]!=1&&visited[nr][nc]!=1) {
					visited[nr][nc] = 1;
					go = true;
					r = nr;
					c = nc;
					d = i%4;
					answer++;
					break;
				}
			}
			
			if(!go) {
				r-=dir[d][0];
				c-=dir[d][1];
				if(board[r][c]==1) break;
			}
			
		}
		
		System.out.println(answer);
		

	}

}
