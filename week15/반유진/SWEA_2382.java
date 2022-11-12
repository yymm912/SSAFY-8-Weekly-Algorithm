// SWEA 2382번 [모의 SW 역량테스트] 미생물 격리

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_2382 {

	static int T, N, M, K;
	static Node[][] map;
	static int[][] sum;

	static List<Node> list;

	// dummy-상-하-좌-우
	static int[] dy = { 0, -1, 1, 0, 0, };
	static int[] dx = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			map = new Node[N][N];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				Node input = new Node(y, x, n, d);
				list.add(input);
				map[y][x] = input;
			}

			sum = new int[N][N];
			for (int m = 0; m < M; m++) {
				bfs(); // 미생물 이동
				samePlace(); // 같은 곳에 있는 군집 합치기
			}

			int ans = 0;
			for (int i = 0; i < list.size(); i++) {
				ans += list.get(i).n;
			}

			System.out.println("#" + t + " " + ans);
		}

	}

	static void bfs() {
		int size = list.size();

		for (int i = size - 1; i >= 0; i--) {
			Node node = list.get(i);

			int ny = node.y + dy[node.d];
			int nx = node.x + dx[node.d];

			// 약품(벽)에 닿으면
			if (ny <= 0 || nx <= 0 || ny >= N - 1 || nx >= N - 1) {
				// 이동방향 바꾸기
				if (ny == 0 && node.d == 1) {
					node.d = 2;
				} else if (node.d == 2) {
					node.d = 1;
				} else if (nx == 0 && node.d == 3) {
					node.d = 4;
				} else if (node.d == 4) {
					node.d = 3;
				}

				// 미생물 절반 죽이기
				node.n /= 2;

				// 만약 미생물 수가 0이면 군집 삭제하기
				if (node.n == 0) {
					list.remove(node);
				}
			}

			// 옮겨간 미생물 위치 저장
			node.y = ny;
			node.x = nx;
		}
	}

	static void samePlace() {
		// map 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = null;
			}
		}

		// 같은 공간인지 확인하기 위해 map에 노드 표시하기
		int size = list.size();
		for (int i = size - 1; i >= 0; i--) {
			Node node = list.get(i);
			int y = node.y;
			int x = node.x;

			if (map[y][x] == null) { // 가려는 장소가 비어있으면
				map[y][x] = node;
				sum[y][x] = node.n;
			} else { // 같은 장소에 미생물 군집이 존재하면
				if (map[y][x].n < node.n) { // 현재 노드의 미생물 수가 map에 존재하는 미생물 수 보다 작으면 현재 node로 바꿔주기
					list.remove(map[y][x]);
					map[y][x] = node;
					sum[y][x] += node.n;
				} else {
					sum[y][x] += node.n;
					list.remove(node);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (sum[i][j] != 0) {
					for (int j2 = 0; j2 < list.size(); j2++) {
						if (list.get(j2).y == i && list.get(j2).x == j) {
							list.get(j2).n = sum[i][j];
						}
					}

					sum[i][j] = 0;
				}
			}
		}
	}

	static class Node {
		int y, x, n, d;

		public Node(int y, int x, int n, int d) {
			this.y = y;
			this.x = x;
			this.n = n;
			this.d = d;
		}
	}
}