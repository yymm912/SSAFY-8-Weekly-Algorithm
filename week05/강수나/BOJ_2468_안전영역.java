package algo_study._8월4주;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* bfs
 * 아무지역도 물에 잠기지 않을 수 있다
 * */
public class BOJ_2468_안전영역 {
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int[][] map;
	static int N, min, max, ans = 1;
	static boolean[][] visited;
	static int[] safety = new int[101];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1]; //0 dummy
		min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (min > map[i][j]) min = map[i][j];
				if (max < map[i][j]) max = map[i][j];
			}
		}
		//min~max 일 때까지 각 지점마다 dfs 수행, cnt 세줌
		for (int line = min; line <= max; line++) {
			
			//visited 초기화
			visited = new boolean[N+1][N+1]; //0 dummy
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i][j] || map[i][j] <= line) continue;
					dfs(i,j, line);
					safety[line]+=1;
				}
			}
			//System.out.println("line:"+line+" -> "+safety[line]);
			ans = Math.max(ans, safety[line]);
		}
		System.out.println(ans);
	}
	
	static void dfs(int y, int x, int line) {
		visited[y][x] = true;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny <= 0 || nx <= 0 || ny > N || nx > N || visited[ny][nx] || map[ny][nx] <= line) continue;
			dfs(ny, nx, line);
		}
	}
}
