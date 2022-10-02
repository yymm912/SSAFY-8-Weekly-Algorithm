package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA5656 {

	static int dy[] = {0,0,1,-1};
	static int dx[] = {1,-1,0,0};
	
	static int T,N,W,H, ans=Integer.MAX_VALUE;
	static int[][] map;
	static int[] tgt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=null;

		T=Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
		
			ans=Integer.MAX_VALUE;
			st=new StringTokenizer (br.readLine());
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			
			map=new int[H][W];
			tgt=new int[W];
			
			for (int i=0; i<H; i++) {
				st=new StringTokenizer (br.readLine());
				for (int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}			
			dfs (0);
			
			if (ans==Integer.MAX_VALUE) 
				ans=0;
			System.out.println("#"+tc+" "+ans);
		}	
	}
	
	private static void dfs (int tgtIdx) {
		if (tgtIdx==N) {
			
			//print(map);
			
			int ret=count();
			
			//System.out.println("***********ret "+ ret+"***********");
			ans=Math.min(ans, ret);
			return ;
		}
		
		for (int i=0; i<W; i++) {
			int x=i; int y=0;
			
			while (y<H && map[y][x]==0)
				y++;
			if (y==H) continue;
			
			int [][] tmp=new int[H][W];
			copy_map (map, tmp);				
			destroy (y,x);
			gravity();	
			dfs (tgtIdx+1);		
			copy_map (tmp, map);
		}
		
	}
	

	private static void destroy (int i, int j) {
		

		boolean[][] visited=new boolean[H][W];
		visited[i][j]=true;
		
		Queue <Pos> q=new ArrayDeque<>();
		q.add(new Pos(i,j,map[i][j]));
		
		while (!q.isEmpty()) {

			Pos p=q.poll();
			int y=p.y;
			int x=p.x;
			int power=p.p;
			
			map[y][x]=0;
			for (int d=0; d<4; d++) {
				
				for (int P=1; P<power; P++) {
					int ny=y+dy[d]*P;
					int nx=x+dx[d]*P;
					
					if (!isInRange (ny, nx)) break;
			
					if (!visited[ny][nx] && map[ny][nx]>1) {
						visited[ny][nx]=true;
						q.add(new Pos (ny, nx, map[ny][nx]));
					}
					
					map[ny][nx]=0;		
				}
			}
		}
	}
	
	private static void gravity () {
		
		for (int i=H-2; i>=0; i--) {
			for (int j=0; j<W; j++) {
				
				if (map[i][j]==0) continue;
				
				int y=i+1;
				while (y<H && map[y][j]==0)
					y++;
				
				// y==H 이거나, map[y][j]!=0 아닐 때 멈춤
				// 따라서 y-- 해주어야 함
				if (y==i+1) continue;
				y--;
				
				map[y][j]=map[i][j];
				map[i][j]=0;		
			}
		}
	}
	
	private static int count () {
		int cnt=0;
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (map[i][j]!=0) 
					cnt++;
			}
		}
		return cnt;
	}
	
	private static boolean isInRange (int y, int x) {
		if (y<0 || x<0 || y>=H || x>=W) return false;
		return true;
	}
	
	private static void copy_map (int [][]a, int[][]b) {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				b[i][j]=a[i][j];
			}
		}
	}
	
	private static void print (int[][] a) {
		System.out.println("===========================");
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Pos {
		int y, x;
		int p;
		
		Pos (int y, int x, int p) {
			this.y=y;
			this.x=x;
			this.p=p;
		}
	}
}
