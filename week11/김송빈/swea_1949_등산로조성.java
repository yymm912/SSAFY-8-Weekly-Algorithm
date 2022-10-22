package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class swea_1949_등산로조성 {
	static int n, k;
	static int[][] map;
	static int[] dy = { 0, 1, 0, -1 }, dx = { 1, 0, -1, 0 };
	static int starth;
	static List<node> list = new ArrayList<>();
	static boolean[][] visited;
	static int len;
	static int[] tgt;

	static class node {
		int y, x, h, d;

		public node(int y, int x, int h, int d) {
			this.y = y;
			this.x = x;
			this.h = h;
			this.d = d;
		}
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/swea_1949_input.txt"));
		StringBuilder sb=new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			list.clear();
			starth = 0;
			len = 0;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (starth < map[i][j]) {
						list.clear();
						list.add(new node(i, j, map[i][j], 0));
						starth = map[i][j];
					} else if (starth == map[i][j])
						list.add(new node(i, j, map[i][j], 0));
				}
			}
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < n; j++)
					Arrays.fill(visited[j], false);
				visited[list.get(i).y][list.get(i).x] = true;
				cal(list.get(i).y, list.get(i).x, list.get(i).h, 1, 0);

			}
			sb.append("#").append(t).append(" ").append(len).append("\n");
		}
		System.out.println(sb);
	}

	static void cal(int y, int x, int h, int d, int minus) {
		len = Math.max(len, d);
		// System.out.println(y+" "+x);
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx])
				continue;
			if (map[ny][nx] < h) {
				visited[ny][nx] = true;
				cal(ny, nx, map[ny][nx], d + 1, minus);
				visited[ny][nx] = false;
			} else {
				if (minus == 0) {
					
					if (map[ny][nx] - k < h) {
						visited[ny][nx] = true;
						cal(ny, nx, h - 1, d + 1, 1);
						visited[ny][nx] = false;
					}
				}
			}
		}
		return;
	}

}
