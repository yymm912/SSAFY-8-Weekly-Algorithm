package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16929TwoDots {

	static int N, M;
	static char map[][];
	static boolean visit[][];
	static int dy[] =  { -1, 1, 0, 0 };
	static int dx[] =  {  0, 0,-1, 1 };
	static boolean flag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		// 입력 완
		
		
		//4게 이상, 사이클이면 YES
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit = new boolean[N][M];
				if(flag) break;
				dfs(i, j, i, j, 0);
			}
		}
		
		System.out.println(flag ? "Yes" : "No");

	}
	
	static void dfs(int y, int x, int start_y, int start_x, int depth) {
		
		// 처음 지점으로 돌아올 수 있다면 사이클이 있음 
		if ( depth > 0 && y == start_y && x == start_x ) {
			flag = true;
			return;
		}
		// 처음 지점은 방문처리 하지않음!
		if( !(y == start_y && x == start_x) )visit[y][x] = true;
		
		for (int d = 0; d < 4; d++) {
			int py = y + dy[d];
			int px = x + dx[d];
			
			if(py < 0 || py >= N || px < 0 || px >= M || visit[py][px] 
					|| ( depth == 1 && py == start_y && px == start_x)
					|| map[py][px] != map[y][x]) continue;
			dfs(py, px, start_y, start_x, depth+1);
			
		}
		
		
	}

}

/*
3 3
AAB
BBB
AAB
 
 */
