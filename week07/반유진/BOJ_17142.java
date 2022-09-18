// BOJ 17142번 연구소 3

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_17142 {
	static int N, M, min, time, zero_cnt;
	static int[][] map, copy_map;
	static int cnt = 0; // 바이러스를 놓을 수 있는 칸 수
	static ArrayList<Node> virusTemp = new ArrayList<>(); // 바이러스를 놓을 수 있는 칸 (현재는 빈 칸임)
	static Queue<Node> que = new ArrayDeque<>(); // 바이러스 M개를 실제로 둘 칸
	static int[] src, tgt;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		copy_map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input == 1) // 벽
					input = -2;
				else if (input == 2) { // 바이러스를 둘 수 있는 곳
					virusTemp.add(new Node(i, j, 0));
					cnt++;
					input = -3;
				} else if (input == 0)
					zero_cnt++;

				map[i][j] = input;
				copy_map[i][j] = input;
			}
		}

		tgt = new int[M];
		src = new int[cnt];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < cnt; i++)
			src[i] = i;

		// 입력, 초기화 끝

		/*
		 * 1. 조합으로 바이러스 어디다 둘 지 정하기 2. bfs로 바이러스 퍼뜨리기 3. 그 중 시간이 가장 최소가 되는 경우 구하기
		 */

		// 조합으로 바이러스 어디다 둘 지 정하기
		comb(0, 0);

		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	static void comb(int srcIdx, int tgtIdx) {
		if (tgtIdx == M) {
			for (int i = 0; i < M; i++) {
				Node v = virusTemp.get(tgt[i]);
				que.add(v);
				map[v.y][v.x] = -1; // 실제로 바이러스가 있는 칸
			}

			bfs();

			// map 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = copy_map[i][j];
				}
			}

			return;
		}

		for (int i = srcIdx; i < cnt; i++) {
			tgt[tgtIdx] = src[i];
			comb(i + 1, tgtIdx + 1);
		}
	}

	static void bfs() {
		int visit_cnt = 0;
		int maxTime = 0;

		while (!que.isEmpty()) {
			Node node = que.poll();

			if (zero_cnt == visit_cnt)
				break;

			for (int d = 0; d < 4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == -2 || map[ny][nx] == -1 || map[ny][nx] > 0)
					continue;

				if (map[ny][nx] == -3) { // 비활성화 상태
					que.offer(new Node(ny, nx, node.d + 1));
					map[ny][nx] = -1;
				} else {
					que.offer(new Node(ny, nx, node.d + 1));
					map[ny][nx] = node.d + 1;

					visit_cnt++;
				}
				time = node.d + 1;
				maxTime = Math.max(maxTime, time);

			}
		}

		if (zero_cnt == visit_cnt) {
			min = Math.min(min, maxTime);
		}

		que.clear();
	}

	static class Node {
		int y;
		int x;
		int d;

		public Node(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
