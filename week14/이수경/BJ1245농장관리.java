package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1245농장관리 {

	static int map[][];
	static int N, M;
	static int dy[] = { 0 ,-1, 0, 1, -1, -1, 1, 1};
	static int dx[] = {-1,  0, 1, 0, -1,  1, 1,-1};
	static boolean visit[][];
	static boolean max;
	static int ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int z=0;z<N;z++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0;x<M;x++) {
				map[z][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][M];
		max = true; // 봉우리 
		int ans = 0;
		
		// 봉우리인지 탐색 , dfs 해서 8면이 모두 자신과 같거나 작아야 함 
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++){
				if( map[i][j] > 0 && !visit[i][j]) {
					
					dfs(i,j);
					
					if(max) ans++;
					max = true;
				}
			}
		}
		
		System.out.println(ans);
	}
	private static void dfs(int y, int x) {
	
		visit[y][x] = true;
		
		for(int i=0;i<8;i++) {
			int px = y+dx[i];
			int py = x+dy[i];
		
			if(px<0 || px>=N || py<0 || py>=M ) continue; 
			if( map[px][py] > map[y][x]) max=false;
			if( !visit[px][py] && map[y][x] == map[px][py]) {
				dfs(px,py);
			}
		}
	}

}
/*
8 7
4 3 2 2 1 0 1
3 3 3 2 1 0 1
2 2 2 2 1 0 0
2 1 1 1 1 0 0
1 1 0 0 0 1 0
0 0 0 1 1 1 0
0 1 2 2 1 1 0
0 1 1 1 2 1 0
*/