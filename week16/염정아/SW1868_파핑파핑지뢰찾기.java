package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;


public class SW1868_파핑파핑지뢰찾기 {
	static int ans, T, N, size;
	static char[][] map;
	static boolean[][] visit;

	static Queue<Node> q = new ArrayDeque<>();

	// 8방
	static int[] dy = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dx = { 0, 0, -1, 1, -1, 1, 1, -1 };

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			// 초기화
			ans = 0;
			q.clear();

			map = new char[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

			// 탐색
			countMap();

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (map[y][x] == '0' && !visit[y][x]) {
						bfs(y, x);
						ans++;
					}

				}

			}

			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					if (map[y][x] != '*' && !visit[y][x]) ans++;
				}

			}

			// 출력
			sb.append("#" + t + " " + ans + "\n");

			// break;

		}

		System.out.println(sb.toString());

	} // end main


	private static void bfs(int sy, int sx) {
		q.offer(new Node(sy, sx));
		visit[sy][sx] = true;

		while (!q.isEmpty()) {
			Node node = q.poll();
			int y = node.y;
			int x = node.x;

			for (int d = 0; d < 8; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (visit[ny][nx]) continue;
				if (map[ny][nx] == '*') continue;

				visit[ny][nx] = true;

				if (map[ny][nx] == '0') q.offer(new Node(ny, nx));
			}

		}

		// while (!q.isEmpty()) {
		// Node node = q.poll();
		// int y = node.y;
		// int x = node.x;
		//
		// // for (int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
		// // System.out.println();
		//
		// /*일단, 1) 주변 8방향에 지뢰가 없는지 확인한다.
		// 만약에 지뢰가 없다면 'X' 을 넣어준다.
		// 해당 좌표에 문자열 넣거 -> 주변 8방향에도 문자열 넣고
		//
		// 넣어주는 주변도 8방향을 확인한다. -> 만약에 주변 8방향에 지뢰가 있다면 -> 큐에 넣어주지 않는다. */
		//
		// // 주변 8방향에 지뢰가 없는지 확인
		// int cnt = 0;
		// for (int d = 0; d < 8; d++) {
		// int ny = y + dy[d];
		// int nx = x + dx[d];
		//
		// if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
		// if (map[ny][nx] == '*') cnt++;
		// }
		//
		// // 만약 8방향에 지뢰가 없다면 해당 좌표와, 주변에 표시 하기
		// if (cnt == 0) {
		// map[y][x] = 'X';
		//
		// for (int d = 0; d < 8; d++) {
		// int ny = y + dy[d];
		// int nx = x + dx[d];
		//
		// if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
		// if (map[ny][nx] != '.') continue;
		//
		// map[ny][nx] = 'X';
		//
		// // 해당 그 주변 8 방향도 살펴보면서 만약 지뢰가 없다면 큐에 넣는다.
		// // 지뢰가 있다면 큐에 넣지 않는다.
		// int cnt2 = 0;
		// for (int d2 = 0; d2 < 8; d2++) {
		// int nny = ny + dy[d2];
		// int nnx = nx + dx[d2];
		//
		// if (nny < 0 || nny >= N || nnx < 0 || nnx >= N) continue;
		// if (map[nny][nnx] == '*') cnt2++;
		// }
		//
		// if (cnt2 == 0) q.offer(new Node(ny, nx));
		//
		// }
		//
		// }
		//
		// }

	} // end bfs


	private static void countMap() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				int cnt = 0;

				if (map[y][x] == '*') continue;

				for (int d = 0; d < 8; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;

					if (map[ny][nx] == '*') cnt++;
				}

				map[y][x] = (char) (cnt + '0');
			}

		}

	} // end countMap


	static class Node {
		int y, x;


		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}


		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + "]";
		}

	} // end Node
}

/*

1. 문제 설명 
NxN
지뢰찾기
최대 8개의 칸 
0이라면 -> 근처의 8방향에 지뢰가 없음
그 8방향 칸도 자동으로 숫자 표시 

2. 자료구조 
- bfs
- map, queue, visit 

3. 예제 
[C, 0, *, 0, C]
[0, 0, *, 0, 0]
[., *, ., ., *]
[., *, 0, 0, 0]
[., *, 0, C, 0]
=> 8 

4. 로직 
bfs 를 사용
. 인 부분을 큐에 넣는다. 

일단, 1) 주변 8방향에 지뢰가 없는지 확인한다. 
만약에 지뢰가 없다면 'X' 을 넣어준다.
해당 좌표에 문자열 넣거 -> 주변 8방향에도 문자열 넣고 

넣어주는 주변도 8방향을 확인한다. -> 만약에 주변 8방향에 지뢰가 있다면 -> 큐에 넣어주지 않는다. 


*/
