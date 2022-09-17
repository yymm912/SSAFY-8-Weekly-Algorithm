package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class BJ3109_빵집 {
	static int ans, R, C;
	static char[][] map;

	// 우선순위가 존재
	// 오른쪽위, 오른쪽, 오른쪽아래
	static int[] dy = { -1, 0, 1 };
	static int[] dx = { 1, 1, 1 };

	static Queue<int[]> q = new ArrayDeque<>();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();

		// 탐색 dfs
		for (int i = 0; i < R; i++) if (dfs(i, 0)) ans++;

		// 탐색 bfs -> 틀렸다고 나옴
		// for (int i = 0; i < R; i++) if (bfs(i, 0)) ans++;

		System.out.println(ans);

	} // end main


	private static boolean dfs(int y, int x) {
		map[y][x] = 'x';

		if (x == C - 1) return true;

		for (int d = 0; d < 3; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
			if (map[ny][nx] == 'x') continue;

//			for (int i = 0; i < R; i++) System.out.println(Arrays.toString(map[i]));
//			System.out.println();

			if (dfs(ny, nx)) return true;
		}

		return false;

	} // end dfs


	private static boolean bfs(int sy, int sx) {
		q.offer(new int[] { sy, sx });
		map[sy][sx] = 'x';

		while (!q.isEmpty()) {
			int[] yx = q.poll();
			int y = yx[0];
			int x = yx[1];

			map[y][x] = 'x';

			if (x == C - 1) return true;

			for (int d = 0; d < 3; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
				if (map[ny][nx] == 'x') continue;

				q.offer(new int[] { ny, nx });
				break;
			}

		}

		return false;

	} // end bfs
}
