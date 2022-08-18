package problem.TC;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//4 5
//00110
//00011
//11111
//00000
//-> 3


public class TC0503_음료수얼려먹기 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	private static void dfs(int y, int x) {
		// 내가 할 수 있는 일
		visited[y][x] = true;

		// 탐색
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == 1 || visited[ny][nx])
			    continue;

			// 다음 재귀 호출
			dfs(ny, nx);
		}

	}


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}

		}

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] == 0 && !visited[y][x]) {
					dfs(y, x);
					ans++;
				}

			}

		}

		System.out.println(ans);

	} // end main
}
