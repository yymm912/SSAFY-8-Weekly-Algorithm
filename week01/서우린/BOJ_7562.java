package _1주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562 {
	
	static int T,I;
	static int [] src = new int[2];
	static int [] dst = new int[2];
	static int [][] d = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
	static int [][] visited;
	static class Cur{
		int r,c;
		Cur(int r,int c){
			this.r =r; this.c = c;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0;t<T;t++) {
			I = Integer.parseInt(br.readLine());
			visited = new int[I][I];
			
			st = new StringTokenizer(br.readLine());
			src[0] = Integer.parseInt(st.nextToken());
			src[1] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			dst[0] = Integer.parseInt(st.nextToken());
			dst[1] = Integer.parseInt(st.nextToken());
			
			System.out.println(bfs());
		}
	}
	public static int bfs() {
		Queue<Cur> q = new LinkedList<>();
		
		q.add(new Cur(src[0],src[1]));
		visited[src[0]][src[1]] = 1;
		
		while(!q.isEmpty()) {
			Cur cur = q.poll();
			
			if(cur.r == dst[0] && cur.c == dst[1]) 
				return visited[cur.r][cur.c] - 1;
			
			for(int i = 0;i<8;i++) {
				int nr = cur.r+d[i][0];
				int nc = cur.c+d[i][1];
				
				if(nr<0||nr>=I||nc<0||nc>=I)
					continue;
				if(visited[nr][nc] == 0) {
					visited[nr][nc] = visited[cur.r][cur.c]+1;
					q.add(new Cur(nr,nc));
				} 
			}
		}
		return -1;
	}
}
