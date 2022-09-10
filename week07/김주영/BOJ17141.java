package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17141 {

	static final int EMPTY=0, WALL=1, VIRUS=2;
	static final int dy[] = {0,0,1,-1};
	static final int dx[] = {1,-1,0,0};
	
	static int N,M,total,ans=Integer.MAX_VALUE;
	static int[][] map;
	static int[][] dist;
	static List<Pos> pos;
	static int[] tgt;
	
	static Queue<Pos> q=new ArrayDeque<>();
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		StringTokenizer st=new StringTokenizer (br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][N];
		dist=new int[N][N];
		pos=new ArrayList<>();
		tgt=new int[M];
		total=N*N;
		
		for (int i=0; i<N; i++) {
			st=new StringTokenizer (br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				
				if (map[i][j]==VIRUS) {
					pos.add(new Pos (i,j));
					map[i][j]=EMPTY;
				}
				if (map[i][j]==WALL) total--;
			}
		}
		
		comb (0,0);
		if (ans==Integer.MAX_VALUE) ans=-1;
		System.out.println(ans);
	}
	
	private static void comb (int tgtIdx, int srcIdx) {
		if (tgtIdx==M) {
			
			for (int i=0; i<N; i++)
				Arrays.fill(dist[i], -1);
			
			for (int i=0; i<M; i++) {
				int n=tgt[i];
				int y=pos.get(n).y;
				int x=pos.get(n).x;
				
				map[y][x]=VIRUS;
				dist[y][x]=0;
				q.add(new Pos (y,x));
		
			}
			
			dfs ();
			
			for (int i=0; i<M; i++) {
				int n=tgt[i];
				int y=pos.get(n).y;
				int x=pos.get(n).x;
				
				map[y][x]=EMPTY;
			}
			
			return ;
		}
		
		if (srcIdx==pos.size()) return ;
		tgt[tgtIdx]=srcIdx;
		comb (tgtIdx+1, srcIdx+1);
		comb (tgtIdx, srcIdx+1);
		
	}
	
	private static void dfs () {
		
		int cnt=0;
		int latest=0;
		
		while (!q.isEmpty()) {
			int y=q.peek().y;
			int x=q.poll().x;
			
			for (int d=0; d<4; d++) {
				int ny=y+dy[d];
				int nx=x+dx[d];
				
				if (ny<0 || nx<0 || ny>=N || nx>=N || dist[ny][nx]!=-1) continue;
				if (map[ny][nx]==0) {
					cnt++;
					dist[ny][nx]=dist[y][x]+1;
					latest=dist[ny][nx];
					q.add(new Pos (ny, nx));
				}
				
			}
		}
		
//		print();
//		System.out.println(total-M+" "+cnt);
		
		if (total-M==cnt) {
			ans=Math.min(latest, ans);
		}
	}
	
	
	static class Pos {
		int y; int x;
		Pos (int y, int x) {
			this.y=y;
			this.x=x;
		}
	}
	
	static void print () {
		System.out.println("===================================");
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.printf("%3d", dist[i][j]);
			}
			System.out.println();
		}
	}

}
