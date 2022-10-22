// 5656번 [모의 SW 역량테스트] 벽돌 깨기

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_5656 {

	static int T, N, W, H, ans;
	static int[][] map, copy;
	static int[] src, tgt;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			copy = new int[H][W];
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					copy[i][j] = map[i][j];
				}
			}

			// src는 구슬을 던질 수 있는 곳들
			// tgt는 src 중에서 구슬을 던질 곳을 고른 배열 (tgt의 길이 : N)

			src = new int[W];
			for (int i = 0; i < W; i++) {
				src[i] = i;
			}

			ans = Integer.MAX_VALUE;
			tgt = new int[N];
			perm(0);

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
	}

	static void bfs(int idx) {
		Queue<Node> que = new ArrayDeque<>();

		// tgt[idx] : 구슬을 떨어뜨릴 x 좌표
		int sy = check_y(tgt[idx]); // 그때의 y 좌표
		if (sy == -1) // 그 줄이 모두 비어있으면 return
			return;

		que.offer(new Node(sy, tgt[idx], map[sy][tgt[idx]])); // 구슬을 떨어뜨릴 x 좌표

		while (!que.isEmpty()) {
			Node node = que.poll();
			int x = node.x;
			int y = node.y;

			int r = node.r;

			map[y][x] = 0;
			for (int i = 0; i < r; i++) {
				if (x - i >= 0) {
					que.offer(new Node(y, x - i, map[y][x - i]));
					map[y][x - i] = 0;
				}
				if (x + i < W) {
					que.offer(new Node(y, x + i, map[y][x + i]));
					map[y][x + i] = 0;
				}
				if (y - i >= 0) {
					que.offer(new Node(y - i, x, map[y - i][x]));
					map[y - i][x] = 0;
				}
				if (y + i < H) {
					que.offer(new Node(y + i, x, map[y + i][x]));
					map[y + i][x] = 0;
				}
			}
		}

		// 아래로 내리기
		fall();
	}

	static void fall() { // 빈공간 아래로 채워주는 함수
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (map[j][i] != 0) {
					while (true) {
						int nj = j + 1;
						if (nj < H && map[nj][i] == 0) {
							map[nj][i] = map[j][i];
							map[j][i] = 0;
							j = nj;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	static int check_y(int x) { // 젤 위의 y 좌표 찾는 함수
		for (int i = 0; i < H; i++) {
			if (map[i][x] > 0) {
				return i;
			}
		}

		return -1;
	}

	static void perm(int tgtIdx) { // 중복순열 - 구슬 어디어디 떨어뜨릴지 경우의 수 찾기
		if (tgtIdx == N) {
			for (int i = 0; i < N; i++) {
				bfs(i);
			}

			// 남은 벽돌 수 세기
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (map[i][j] > 0) {
						cnt++;
					}
				}
			}

			ans = Math.min(ans, cnt);

			copyMap(); // 맵 초기화

			return;
		}

		for (int i = 0; i < W; i++) {
			tgt[tgtIdx] = src[i];
			perm(tgtIdx + 1);
		}
	}

	static void copyMap() { // map 복사 함수
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = copy[i][j];
			}
		}
	}

	static class Node {
		int y;
		int x;
		int r;

		public Node(int y, int x, int r) {
			this.y = y;
			this.x = x;
			this.r = r;
		}
	}

}
