package algo1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 전체 진행과정
 * 
 * 1. 맵 생성
 * 2. 벽 3개를 맵에 배치하는 경우의 수를 모두 구현.
 * 3. 3개를 놓았다면 BFS 알고리즘을 통해 바이러스를 Map을 복사한 배열에 확산시키고,
 * 4. 안전 영역(0인 공간)의 수를 세어 가장 많은 max를 반환한다.
 * 
 */

public class BJ_연구소_14502 {
	static int N, M, max;
	static int[][] map, copy;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static Queue<Node> queue = new ArrayDeque<>();

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		wallSet(0);
		
		System.out.println(max);

		/*************** case ****************/

	}

	static void wallSet(int wall) {
		if (wall == 3) {
			// 기저 조건 벽이 3개가 되면 바이러스 확산
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					wallSet(wall + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	static void bfs() {
		int[][] copyMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 2) {
					queue.offer(new Node(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= M)
					continue;
				if (copyMap[ny][nx] == 0) {
					copyMap[ny][nx] = 2;
					queue.offer(new Node(ny, nx));
				}
			}
		}
		countSafe(copyMap);

	}

	public static void countSafe(int[][] copymap) {
		int cnt = 0; // 빈 공간
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copymap[i][j] == 0)
					cnt++;
			}
		}

		max = Math.max(max, cnt);

	}

}
