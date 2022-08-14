package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 음료수얼려먹기 {

	static final char EMPTY='0', WALL='1';
	
	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static int N,M,ans;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
	
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new char [N][];
		
		for (int i=0; i<N; i++) {
			String str=br.readLine();
			map[i]=str.toCharArray();
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j]==EMPTY) {
					ans++;
					bfs (i,j);
				}
			}
		}
		System.out.println(ans);
	}
	
	private static void bfs (int i, int j) {
		map[i][j]=WALL;
		Queue<int[]> q=new ArrayDeque<>();
		q.add(new int[] {i,j});
		
		while (!q.isEmpty()) {
			int y=q.peek()[0];
			int x=q.poll()[1];
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=M || map[ny][nx]==WALL) continue;
				
				map[ny][nx]=WALL;
				q.add(new int[] {ny, nx});
			}
		}
	}
}
