// BOJ 14500번 테트로미노 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_14500 {

	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;

	static int[] src;
	static int[] tgt = new int[3];

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 1. ㅜ 모양을 제외한 나머지 4개는 dfs로 완탐
		// 2. ㅜ 모양은 ㅓ, ㅏ, ㅗ 로도 가능 -> 인접한 4칸 중 3칸 선택 (조합)

		max = Integer.MIN_VALUE;
		visited = new boolean[N][M];
		src = new int[] { 0, 1, 2, 3 };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;

				comb(0, 0, i, j);
			}
		}
		
		System.out.println(max);
	}

	static void dfs(int y, int x, int dep, int sum) {
		if (dep == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || visited[ny][nx])
				continue;

			visited[ny][nx] = true;
			dfs(ny, nx, dep + 1, sum + map[ny][nx]);
			visited[ny][nx] = false;
		}
	}

	static void comb(int srcIdx, int tgtIdx, int y, int x) {
		int combSum = map[y][x];
		
		if (tgtIdx == 3) {
			for (int d = 0; d < 3; d++) {
				int ny = y + dy[tgt[d]];
				int nx = x + dx[tgt[d]];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					return;

				combSum += map[ny][nx];
				max = Math.max(max, combSum);
			}

			return;
		}

		for (int i = srcIdx; i < 4; i++) {
			tgt[tgtIdx] = src[i];
			comb(i + 1, tgtIdx + 1, y, x);
		}
	}

}
