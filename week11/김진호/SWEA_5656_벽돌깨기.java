
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {
	static int T;
	static int N, W, H;
	static int[][] map;
	static int[][] copyMap;
	static int[] tgt;
	static int answer ;

	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			answer = Integer.MAX_VALUE;
			map = new int[H][W];
			copyMap = new int[H][W];
			tgt = new int[N];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			perm(0);
			System.out.println("#" + t + " " + answer);

		}

	}

	static int remain() {
		int cnt = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (copyMap[r][c] > 0)
					cnt++;
			}
		}
		return cnt;
	}

	static void perm(int cnt) {
		if (cnt == N) {
			copy();
			for (int i = 0; i < N; i++) {
				down(tgt[i]);
			}
			remain();
			int remainCnt = remain();
			answer = Math.min(answer, remainCnt);
			return;
		}

		for (int i = 0; i < W; i++) {
			tgt[cnt] = i;
			perm(cnt + 1);
		}

	}

	static void down(int target) {
		for (int row = 0; row < H; row++) {

			// 구슬이 밑으로 진행하면서 1을 만나면 그 부분만 삭제함
			if (copyMap[row][target] == 1) {
				copyMap[row][target] = 0;
				return;
			} else if (copyMap[row][target] > 1) {
				// 벽돌의 개수가 1보다 크다면 사방탐색으로 통해 제거해줘야함
				boom(row, target, copyMap[row][target]);
				reLocation();
				return;
			}
		}

	}

	static void boom(int y, int x, int cnt) {
		// ny nx >
		Deque<Node> q = new ArrayDeque<>();

		q.offer(new Node(y, x, cnt));
		copyMap[y][x] = 0;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ny = cur.y;
				int nx = cur.x;

				for (int k = 1; k < cur.cnt; k++) {
					ny += dy[d];
					nx += dx[d];
					if (ny < 0 || nx < 0 || ny >= H || nx >= W || copyMap[ny][nx] == 0)
						continue;

					// cnt가 1보다 크다면 q에 넣음
					if (copyMap[ny][nx] > 1) {
						q.offer(new Node(ny, nx, copyMap[ny][nx]));
					}

					// 벽돌이 있는 저리를 0으로 변경 : 빈칸으로 만들어서변경처리
					copyMap[ny][nx] = 0;

				}

			}
		}

	}

	static void reLocation() {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (copyMap[j][i] > 0) {
					stack.add(copyMap[j][i]);
					copyMap[j][i] = 0;
				}
			}

			int k = H - 1;
			while (!stack.isEmpty()) {
				copyMap[k][i] = stack.pop();
				k--;
			}
		}
	}

	static void copy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}

	static class Node {
		int y;
		int x;
		int cnt;

		public Node(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}

}
