package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_16236_아기상어 {
	static int N, ans, eat;
	static int[][] map;
	static int[] dx = { 0, -1, 1, 0 }; // 순서 중요 X. 나중에 정답 후보들 list에 다 담아서 sort 해야함(우선순위 위쪽-왼쪽)
	static int[] dy = { -1, 0, 0, 1 };
	static Queue<Node> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		StringTokenizer st;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				map[i][j] = n;
				if (n == 9) {
					queue.offer(new Node(i, j, 2)); // 아기상어 level2
				}
			}
		}
		// 입력 끝
		while (true) {
			Node n = queue.peek();
			if (!bfs(n))
				break;
		}

		System.out.println(ans);
	}

	static boolean bfs(Node node) {

		int d = 0;

		boolean[][] visit = new boolean[N][N];
		visit[node.y][node.x] = true;

		ArrayList<Node> list = new ArrayList<>(); // 정답 답을 리스트. 나중에 y 기준 sort
		boolean flag = true;

		while (!queue.isEmpty() && flag) {
			int size = queue.size();
			d++;
			for (int i = 0; i < size; i++) {
				Node n = queue.poll();
				for (int j = 0; j < 4; j++) {
					int nx = n.x + dx[j];
					int ny = n.y + dy[j];

					if (!check(ny, nx))
						continue;

					if (visit[ny][nx])
						continue;

					if (map[ny][nx] != 0 && map[ny][nx] < node.s) {
						list.add(new Node(ny, nx, node.s));

						flag = false;
					}

					else if (map[ny][nx] == node.s || map[ny][nx] == 0) {// 같거나 빈칸이면 큐에 넣기
						visit[ny][nx] = true;
						queue.offer(new Node(ny, nx, map[ny][nx]));
					}

				}
			}
		}

		if (flag == false) {
			Collections.sort(list, (l1, l2) -> l1.y == l2.y ? l1.x - l2.x : l1.y - l2.y);
			Node ns = list.get(0);

			map[ns.y][ns.x] = 9; // 아기 상어 이동을 보기 위해 map에 넣어줌.
			map[node.y][node.x] = 0; // 물고기 먹은 자리는 빈칸

			queue.clear(); // 다음 계산을 위한 queue 준비( clear, 자기자신 삽입)

			eat++;
			if (ns.s == eat) { // 상어 레벨 올라갈지 말지 결정
				ns.s++;
				eat = 0;
			}

			queue.offer(ns); // 다음 계산을 위한 queue 준비
			ans += d; // 이동거리 누적 합
			return true; // 상어가 이동한 경우 true
		}

		return false; // 상어가 이동X 경우 더이상 먹을게 없다는 이야기. false반환
	}

	static boolean check(int y, int x) {
		return y >= 0 && y < N && x >= 0 && x < N;
	}

	static class Node {
		int y, x, s;

		public Node(int y, int x, int s) {
			this.y = y;
			this.x = x;
			this.s = s;
		}

	}
}
