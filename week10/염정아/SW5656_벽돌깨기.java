package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class SW5656_벽돌깨기 {
	static int ans, T, N, W, H;
	static int[][] map, mapTemp;
	static int[] tgt;

	// 상하좌우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			mapTemp = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = mapTemp[i][j] = Integer.parseInt(st.nextToken());
				}

				// System.out.println(Arrays.toString(map[i]));
			}

			// 초기화
			ans = Integer.MAX_VALUE;
			tgt = new int[N];

			// 시뮬레이션
			perm(0);

			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}

		System.out.println(sb.toString());

	} // end main


	// 중복 순열
	private static void perm(int dep) {
		if (dep == N) {
			// complete code
			// System.out.println(Arrays.toString(tgt));
			copyMap();

			for (int tx : tgt) {
				for (int y = 0; y < H; y++) {
					if (map[y][tx] != 0) {
						// 벽돌 깨기
						stroke(y, tx);

						// 칸 내리기
						down();

						break;
					}

				}

			}

			ans = Math.min(ans, check());

			return;
		}

		for (int i = 0; i < W; i++) {
			tgt[dep] = i;
			perm(dep + 1);
		}

	} // end comb


	// 벽돌을 수만큼 깨는 함수
	private static void stroke(int sy, int sx) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { sy, sx, map[sy][sx] });
		map[sy][sx] = 0;

		while (!q.isEmpty()) {
			int[] yx = q.poll();
			int y = yx[0];
			int x = yx[1];
			int size = yx[2];

			for (int i = 1; i < size; i++) {
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d] * i;
					int nx = x + dx[d] * i;

					if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
					if (map[ny][nx] == 0) continue;

					q.offer(new int[] { ny, nx, map[ny][nx] });
					map[ny][nx] = 0;
				}

			}

		}

	} // end stroke


	// 없어진 벽돌을 내리는 함수
	private static void down() {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) if (map[j][i] != 0) stack.add(map[j][i]);

			for (int j = H - 1; j >= 0; j--) {
				if (stack.isEmpty()) map[j][i] = 0;
				else map[j][i] = stack.pop();
			}

		}

	} // end down


	// mapTemp -> map 카피 함수
	private static void copyMap() {
		for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) map[i][j] = mapTemp[i][j];
	} // copyMap


	// 남은 벽돌의 개수를 세는 함수
	private static int check() {
		int sum = 0;
		for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) if (map[i][j] != 0) sum++;
		return sum;
	} // end check
}
