package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2589 {
	static int N, M, MaxDist;
	static char[][] map;
	static int[][] logMap;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		map = new char[N][];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L') {

					logMap = new int[N][M];
					for (int k = 0; k < N; k++)
						Arrays.fill(logMap[k], 100_000_000);

					Queue<int[]> q = new ArrayDeque<>();
					q.add(new int[] { i, j, 0 });

					while (!q.isEmpty()) {
						int[] now = q.poll();
						int y = now[0];
						int x = now[1];
						int cnt = now[2];

						if (logMap[y][x] <= cnt)
							continue;

						logMap[y][x] = cnt;
						MaxDist = Math.max(cnt, MaxDist);

						for (int k = 0; k < 4; k++) {
							int ny = y + dy[k];
							int nx = x + dx[k];
							if (ny < 0 || ny >= N || nx < 0 || nx >= M || map[ny][nx] == 'W'
									|| logMap[ny][nx] <= cnt + 1)
								continue;

							q.add(new int[] { ny, nx, cnt + 1 });
						}

					}
				}
			}
		}

		System.out.println(MaxDist);
	}

}
