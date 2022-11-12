import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1949_등산로조성 {
	
	static int T, N, K, maxH, res;
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			visit = new boolean[N][N];
			res = Integer.MIN_VALUE;
			maxH = Integer.MIN_VALUE;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxH = Math.max(maxH, map[i][j]);
				}
			} // 입력 끝
			
			// 모든 최대 높이의 산봉우리 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == maxH) {
						visit[i][j] = true;
						dfs(i, j, maxH, 1, true);
						visit[i][j] = false;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
	}
	
	static void dfs(int x, int y, int prev, int cnt, boolean can) {
		
		for (int d = 0; d < 4; d++) {
			int tx = x + dx[d];
			int ty = y + dy[d];
			
			if(tx < 0 || ty < 0 || tx >= N || ty >= N || visit[tx][ty]) continue;
			
			if(prev <= map[tx][ty]) {  // 깎아야만 갈 수 있는 길 (한 번)
				if(can) {
					if(map[tx][ty] - K < prev) { // 최대로 깎았을 때 이동가능하다면 일단 이동가능한 길
						visit[tx][ty] = true;
						dfs(tx, ty, prev - 1, cnt + 1, !can); // 최소로 깎아서 다음 이동 기회 최대로
						visit[tx][ty] = false;
					}
				}
				continue;
			} else {  // 깎지 않고 갈 수 있는 길
				visit[tx][ty] = true;
				dfs(tx, ty, map[tx][ty], cnt + 1, can);
				visit[tx][ty] = false;
			}
		}
		
		res = Math.max(res, cnt); // 최대한 낮은 곳으로 내려온 뒤 등산로 최대길이 갱신
	}
}
