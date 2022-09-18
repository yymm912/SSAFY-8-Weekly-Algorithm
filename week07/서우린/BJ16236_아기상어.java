package _8주차;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16236_아기상어 {
	static int N;
	static int [][] board;
	static int [] target;
	static int dist;
	static baby shark = new baby();
	static int [][] d = {{-1,0},{0,-1},{1,0},{0,1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 9) {
					shark.r = i;
					shark.c = j;
					shark.size = 2;
					board[i][j] = 0;
				}
			}
		}
		int answer = 0;
		while(true) {
			if(!search()) break;
			shark.r = target[0];
			shark.c = target[1];
			shark.exp++;
			board[target[0]][target[1]] = 0;
			answer += dist;
			if(shark.exp == shark.size) {
				shark.size++;
				shark.exp = 0;
			}
		}
		System.out.println(answer);
	}
	static boolean search() {
		Queue<Point> q = new ArrayDeque<>();
		int [][] visited = new int[N][N];
		q.add(new Point(shark.c,shark.r));
		dist = Integer.MAX_VALUE;
		for(int i = 0;i<N;i++)
			for(int j = 0;j<N;j++)
				visited[i][j] = Integer.MAX_VALUE;
		visited[shark.r][shark.c] = 0;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for(int i = 0;i<4;i++) {
				int nr = cur.y+d[i][0];
				int nc = cur.x+d[i][1];
				
				if(nr<0||nr>=N||nc<0||nc>=N)
					continue;
				if(board[nr][nc] > shark.size)
					continue;
				if(visited[nr][nc] > visited[cur.y][cur.x] + 1) {
					visited[nr][nc] = visited[cur.y][cur.x] + 1;
					q.add(new Point(nc,nr));
					if(board[nr][nc] > 0 && board[nr][nc] < shark.size) {
						if(dist > visited[nr][nc]) {
							target = new int []{nr,nc};
							dist = visited[nr][nc];
						}else if(dist == visited[nr][nc]) {
							if(target[0] > nr) {
								target[0] = nr;
								target[1] = nc;
							}else if(target[0] == nr) {
								if(target[1] > nc) target[1] = nc;
							}
						}
						
					}
				}
			}
		}
		if(dist != Integer.MAX_VALUE) return true;
		return false;
	}
	
	static class baby {
		int r, c , size, exp;
	}

}
