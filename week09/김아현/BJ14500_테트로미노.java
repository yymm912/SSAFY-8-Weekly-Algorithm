package bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 한 칸마다 dfs를 이용해 깊이 4의 테트로미노를 만든다. => 최대값 갱신
// ㅓ ㅏ ㅗ ㅜ 모양의 테트로미노는 dfs로 한번에 탐색이 안돼서 따로 dfs를 더 해야 한다.

public class BJ14500_테트로미노 {
	
	static int N, M, res;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		res = Integer.MIN_VALUE;
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visit[i][j] = false;
			}
		}
		
		System.out.println(res);
	}
	
	static void dfs(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			res = Math.max(res, sum);
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= M || visit[tx][ty]) continue;
			
			// ㅓ ㅏ ㅗ ㅜ 를 만들기 위해 2번째 위치에서 dfs 수행
			if(cnt == 2) {
				visit[tx][ty] = true;
				dfs(tx, ty, cnt+1, sum + map[tx][ty]);
				visit[tx][ty] = false;
			}
			
			visit[tx][ty] = true;
			dfs(tx, ty, cnt+1, sum + map[tx][ty]);
			visit[tx][ty] = false;
		}
	}

}
