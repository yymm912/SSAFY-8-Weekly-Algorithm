package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2105_디저트카페 {

	static int T, N, res;
	static int[][] map;
	static boolean[][] visit;
	static boolean[] dessert;
	
	static int[] dx = {1, 1, -1, -1}; // 좌하-우하-우상-좌상
	static int[] dy = {-1, 1, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			res = Integer.MIN_VALUE;
			for (int i = 0; i < N-2; i++) {
				for (int j = 1; j < N-1; j++) {
					visit = new boolean[N][N];
					dessert = new boolean[101];
					
					visit[i][j] = true;
					dessert[map[i][j]] = true;
					
					dfs(i, j, i, j, 1, 0);
				}
			}
			
			if(res == Integer.MIN_VALUE) res = -1;
			
			System.out.println("#" + t + " " + res);
		}
	}

	static void dfs(int sx, int sy, int x, int y, int cnt, int dir) {
		
		for (int d = dir; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx == sx && ty == sy && cnt > 2) {
				res = Math.max(res, cnt);
				return;
			}

			if(tx < 0 || ty < 0 || tx >= N || ty >= N
					|| visit[tx][ty] || dessert[map[tx][ty]]) continue;
			
			visit[tx][ty] = true;
			dessert[map[tx][ty]] = true;
			
			dfs(sx, sy, tx, ty, cnt + 1, d);
			
			visit[tx][ty] = false;
			dessert[map[tx][ty]] = false;
			
		}
	}
	
}
