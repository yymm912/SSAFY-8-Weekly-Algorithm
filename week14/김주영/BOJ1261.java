package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1261 {
	
	static final char WALL='1';
	static final char EMPTY='0';
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N,M;
	static char [][] maze;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		visited=new boolean[N][M];
		
		maze=new char [N][];
		for (int i=0; i<N; i++) {
			String line=br.readLine();
			maze[i]=line.toCharArray();
		}
		
		PriorityQueue<Pos> pq=new PriorityQueue<>( (Pos p1, Pos p2)-> p1.wall-p2.wall);
		pq.offer(new Pos (0,0,0));
		visited[0][0]=true;
		
		while (!pq.isEmpty()) {
			Pos pos=pq.poll();
			int y=pos.y;
			int x=pos.x;
			int wall=pos.wall;
			
			if (y==N-1 && x==M-1) {
				System.out.println(wall);
				return ;
			}
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=M || visited[ny][nx]) 
					continue;
				
				visited[ny][nx]=true;
				if (maze[ny][nx]==WALL) {
					pq.offer(new Pos (ny, nx, wall+1));
				} else {
					pq.offer(new Pos (ny, nx, wall));
				}		
			}
		}
	}
	
	static class Pos {
		int y,x;
		int wall;
		
		Pos (int y, int x, int wall) {
			this.y=y;
			this.x=x;
			this.wall=wall;
		}
	}

}
