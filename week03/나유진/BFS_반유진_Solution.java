package 페어_dfs_bfs연습;

import java.util.ArrayDeque;
import java.util.Queue;

//출제자:반유진
/*
 * 해당 배열에서 bfs하며 -1사방에 있는 값들의 합을 구하라. 중복으로 더하면 안됨
 */

public class BFS_반유진_Solution {
	static int[][] arr = { // 배열에서 bfs하며 -1사방에 있는 수들의 합 구하기
			{ 0, 0, 0, 0, 0, 0, 0 }, //
			{ 0, 11, 12, 13, 14, 15, 16 }, //
			{ 0, 21, 22, 23, 24, 25, 26 }, //
			{ 0, 31, 32, -1, 34, 35, 36 }, //
			{ 0, 41, 42, 43, 44, 45, 46 }, //
			{ 0, 51, 52, 53, 54, -1, 56 }, //
			{ 0, 61, 62, 63, 64, 65, 66 }, //
	};

	static int[] dx = { -1, 1, 0, 0 }; // 상-하-좌-우
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visit = new boolean[7][7];
	static int N;

	public static void main(String[] args) {
		bfs(1,1);
		System.out.println(N);
	}

	static void bfs(int x, int y) {
		Queue<Node> queue = new ArrayDeque<>();

		queue.offer(new Node(x, y));
		visit[x][y] = true;

		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];

				if (nx < 1 || ny < 1 || nx >= 7 || ny >= 7 || visit[nx][ny])
					continue;

				if (arr[nx][ny] == -1) { //-1만나면 4방탐색하여 N에 저장
					for (int j = 0; j < 4; j++) {
						int X = nx + dx[j];
						int Y= ny + dy[j];

						if (X < 1 || Y < 1 || X >= 7 || Y >= 7 || visit[X][Y])continue;
						N += arr[X][Y];
					}
					
				}
				visit[nx][ny] = true;
				queue.offer(new Node(nx, ny));
			}
		}

	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}
}
