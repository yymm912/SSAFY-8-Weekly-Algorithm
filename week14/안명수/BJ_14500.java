package algo.BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500 {
	static int[][] paper;
	static int n,m;
	static int result;
	
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,-1,1};
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		
		paper = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			stk = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visited[i][j] = true;
				solve(i,j,paper[i][j],1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(result);
	}
	
	static void solve(int i, int j, int sum, int cnt) {
		if(cnt == 4) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int k = 0; k < 4; k++) {
			int ny = i + dy[k];
			int nx = j + dx[k];
			if(ny < 0 || ny >= n || nx < 0 || nx >= m || visited[ny][nx]) continue;
			visited[ny][nx] = true;
			solve(ny,nx,sum + paper[ny][nx], cnt + 1);
			if( cnt == 2 ) solve(i,j,sum + paper[ny][nx], cnt + 1);
			visited[ny][nx] = false;
		}
	}
	
}
