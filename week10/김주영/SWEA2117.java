package bfs.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 60min
 * 
 * 실수한 것 
 * 1. 운영 비용을 계산할 때 (K*K)+ (K-1)*(K-1)인데, 누적 운영 비용에 K*K를 계속 더해줌
 * 2. "손해를 보지 않으면서 홈방법 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾았을 때, 집의 수" 인데
 * 		"이익이 최대일 때, 집의 수를 계산함" -> 마음대로 문제 해석 -> 문제를 잘 읽자
 * 
 */

public class SWEA2117 {

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };

	static final int EMPTY = 0, HOME = 1;

	static int T, N, M, ans;
	static int[][] map, dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					dist = new int[N][N];
					ans = Math.max(ans, bfs(i, j));
				}
			}
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

	// 좌표 i, j를 중심으로 1,2,... 거리를 늘려나가면서 보안 회사의 이익을 찾고
	// 최대 이익을 return
	static int bfs(int i, int j) {
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { i, j });
		dist[i][j] = 1;
		int homeCnt = map[i][j];
		int maxHomeCnt=homeCnt;
		int cost = 1; // 운영비용
		int K=1;
		
		while (!q.isEmpty()) {

			int qSize = q.size();
			K++;
			
			for (int k = 0; k < qSize; k++) {
				int y = q.peek()[0];
				int x = q.poll()[1];

				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= N || dist[ny][nx] != 0)
						continue;

					dist[ny][nx] = dist[y][x] + 1;
					q.offer(new int[] { ny, nx });
					if (map[ny][nx] == HOME)
						homeCnt++;

				}
			}
			
			cost=(K*K)+(K-1)*(K-1);
			int profit=homeCnt*M-cost;
			if (profit>=0 && homeCnt>maxHomeCnt) {
				maxHomeCnt=homeCnt;
			}
		}
		return maxHomeCnt;

	}
}
