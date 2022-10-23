package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17144 {

	static int r, c, t;
	static int[][] map;
	static int[][] vacum = new int[2][2];
	static int[] dy = { 0, -1, 0, 1 }, dx = { 1, 0, -1, 0 };// 반시계방향

	static class node {
		int y, x, val;

		node(int y, int x, int val) {
			this.y = y;
			this.x = x;
			this.val = val;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		int vcnt = 0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				int x = Integer.parseInt(st.nextToken());
				map[i][j] = x;
				if (x == -1) {
					vacum[vcnt][0] = i;
					vacum[vcnt][1] = j;
					vcnt++;
				}
			}
		}
		for (int i = 0; i < t; i++) {
			expand();
			move();
//			for (int k = 0; k < r; k++) {
//				for (int j = 0; j < c; j++) {
//					System.out.print(map[k][j] + " ");
//				}
//				System.out.println();
//			}
		}

		int sum = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j]>0)
					sum += map[i][j];
			}
		}
		System.out.println(sum);
	}

	// 확산
	static void expand() {
		List<int[]> diff;
		Queue<node> q = new ArrayDeque<>();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0)
					q.offer(new node(i, j, map[i][j]));
			}
		}

		while (!q.isEmpty()) {
			node nd = q.poll();
			int y = nd.y;
			int x = nd.x;
			int val = nd.val;
			int dcnt = 0;
			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny >= 0 && nx >= 0 && ny < r && nx < c) {
					if (map[ny][nx] != -1) {
						dcnt++;
						map[ny][nx] += (val / 5);

					}
				}
			}
			map[y][x] -= (val / 5) * dcnt;
		}
	}

	// 이동
	static void move() {

		// 먼지 이동
		int vy1 = vacum[0][0];
		int vy2 = vacum[1][0];
		int vx = vacum[0][1];

		int y = vy1;
		int x = vx;
		int tmp1 = map[y][x];

		// 위
		for (int i = 0; i < 4; i++) {
			while (true) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= c || ny < 0 || nx < 0 || ny > vy1)
					break;

				if (map[ny][nx] == -1)
					break;

				int tmp2 = map[ny][nx];
				if (map[y][x] != -1) {
					map[ny][nx] = tmp1;
				} else
					map[ny][nx] = 0;

				tmp1 = tmp2;
				y = ny;
				x = nx;

			}
		}

		// 아래
		y = vy2;
		x = vx;
		tmp1 = map[y][x];
		int idx = 0;
		int cnt = 0;

		while (true) {
			int nx = x + dx[idx];
			int ny = y + dy[idx];

			if (nx >= c || ny < vy2 || nx < 0 || ny >= r) {
				idx--;
				if (idx < 0)
					idx = 3;

				continue;
			}

			if (map[ny][nx] == -1)
				break;

			int tmp2 = map[ny][nx];
			if (map[y][x] != -1) {
				map[ny][nx] = tmp1;
			} else
				map[ny][nx] = 0;

			tmp1 = tmp2;
			y = ny;
			x = nx;

		}
	}

}
