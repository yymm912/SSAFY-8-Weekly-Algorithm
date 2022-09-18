// BOJ 15683번 감시 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class No_15683 {

	static int N, M, min, cnt;
	static int[][] map, copy_map;

	static List<Node> list = new ArrayList<>();
	static int[] tgt;

	// 상-우-하-좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copy_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copy_map[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (1 <= map[i][j] && map[i][j] <= 5) {
					cnt++;
					list.add(new Node(i, j, map[i][j]));
				}
			}
		}

		// 입력 끝

		// 1. 순열로 모든 경우의 수 생각하기 (방향을 tgt로)
		// 2. 구해진 순열에서 해당 cctv 번호에 맞게 goCCTV 함수를 이용 -> map에서 cctv가 비추는 구역은 -1 로 변경시키기
		// 3. -1이 표시된 구역의 수 구하기
		// 4. 그 중 최소값 구하기

		tgt = new int[cnt];
		min = Integer.MAX_VALUE;

		perm(0);

		System.out.println(min);
	}

	static void perm(int tgtIdx) {
		if (tgtIdx == cnt) {
			for (int i = 0; i < cnt; i++) {
				Node cctv = list.get(i);
				if (cctv.n == 1) { // 1번 cctv
					goCCTV(cctv.y, cctv.x, tgt[i]);
				} else if (cctv.n == 2) { // 2번 cctv
					if (tgt[i] == 0 || tgt[i] == 1) {
						goCCTV(cctv.y, cctv.x, tgt[i]);
						goCCTV(cctv.y, cctv.x, tgt[i] + 2);
					} else if (tgt[i] == 2 || tgt[i] == 3) {
						goCCTV(cctv.y, cctv.x, tgt[i]);
						goCCTV(cctv.y, cctv.x, tgt[i] - 2);
					}
				} else if (cctv.n == 3) { // 3번 cctv
					if (tgt[i] == 3) {
						goCCTV(cctv.y, cctv.x, 3);
						goCCTV(cctv.y, cctv.x, 0);
					} else {
						goCCTV(cctv.y, cctv.x, tgt[i]);
						goCCTV(cctv.y, cctv.x, tgt[i] + 1);
					}
				} else if (cctv.n == 4) { // 4번 cctv
					if (tgt[i] == 0 || tgt[i] == 1) {
						goCCTV(cctv.y, cctv.x, tgt[i]);
						goCCTV(cctv.y, cctv.x, tgt[i] + 1);
						goCCTV(cctv.y, cctv.x, tgt[i] + 2);
					} else if (tgt[i] == 2) {
						goCCTV(cctv.y, cctv.x, 2);
						goCCTV(cctv.y, cctv.x, 3);
						goCCTV(cctv.y, cctv.x, 0);
					} else if (tgt[i] == 3) {
						goCCTV(cctv.y, cctv.x, 3);
						goCCTV(cctv.y, cctv.x, 0);
						goCCTV(cctv.y, cctv.x, 1);
					}
				} else if (cctv.n == 5) { // 5번 cctv
					goCCTV(cctv.y, cctv.x, 0);
					goCCTV(cctv.y, cctv.x, 1);
					goCCTV(cctv.y, cctv.x, 2);
					goCCTV(cctv.y, cctv.x, 3);
				}
			}

			int result = func_sum();
			min = Math.min(min, result);

			copy_map();

			return;
		}

		for (int i = 0; i < 4; i++) {
			tgt[tgtIdx] = i;
			perm(tgtIdx + 1);
		}
	}

	static void goCCTV(int y, int x, int d) { // 한 방향으로 쭉 나가기
		while (true) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == 6)
				return;

			if (map[ny][nx] != 0) {
				y = ny;
				x = nx;
				continue;
			}

			map[ny][nx] = -1;
			y = ny;
			x = nx;
		}
	}

	static int func_sum() { // 사각지대 개수 구하기
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					sum++;
			}
		}

		return sum;
	}

	static void copy_map() { // map 복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = copy_map[i][j];
			}
		}
	}

	static class Node {
		int y;
		int x;
		int n;

		public Node(int y, int x, int n) {
			this.y = y;
			this.x = x;
			this.n = n;
		}
	}

}