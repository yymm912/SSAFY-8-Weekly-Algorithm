import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,ans,max = Integer.MIN_VALUE;
	static int[][] map;
	static int h;
	static boolean[][] visit;
	static int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max,map[i][j]);
			}
		} // input end

		// initialize
		ans = Integer.MIN_VALUE;

		h = 0;

		while(h++ <= max) {
			int cnt = 0;
			visit = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visit[i][j])continue;
					if(map[i][j] >= h) {
						bfs(i,j);
						cnt++;
					}
				}
			}
			ans = Math.max(ans, cnt);
		}

		System.out.println(ans);
	}

	static void bfs(int y,int x) {
		Queue<Integer> Q = new ArrayDeque<>();
		Q.offer(y);
		Q.offer(x);
		visit[y][x] = true;

		while(!Q.isEmpty()) {
			y = Q.poll();
			x = Q.poll();
			for(int[] adj : dir) {
				int ny = y + adj[0];
				int nx = x + adj[1];

				if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if(visit[ny][nx])continue;
				if(map[ny][nx] < h)continue;
				Q.offer(ny);
				Q.offer(nx);
				visit[ny][nx] = true;
			}
		}
	}
}
