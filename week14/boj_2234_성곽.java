package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2234_성곽 {

	static int n, m;
	static int[][] room, arr;
	static int max = 0;
	static int[] area;
	static int[] y = { 0, 1, 0, -1 };
	static int[] x = { 1, 0, -1, 0 };
	static class Pos {

		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	
		StringTokenizer st=new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[m][n];
		room = new int[m][n];
		area = new int[2501];//1-2500

		
		for (int i = 0; i < m; i++) {
			st=new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int c = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (room[i][j] == 0) {
					bfs(new Pos(i, j), c);
					max = Math.max(max, area[c]);
					c++;
				}
			}
		}

		int sum = 0;
	
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					int nr = i + y[k];
					int nc = j + x[k];

					if ((0 <= nr && nr < m) && (0 <= nc && nc < n) && room[i][j] != room[nr][nc]) {
						sum = Math.max(sum, area[room[i][j]] + area[room[nr][nc]]);
					}
				}
			}
		}

		System.out.println((c - 1) + "\n" + max + "\n" + sum + "\n");

	}

	public static void bfs(Pos start, int c) {

		Queue<Pos> q = new ArrayDeque<>();

		q.offer(start);
		room[start.r][start.c] = c;

		while (!q.isEmpty()) {

			Pos pos = q.poll();
			area[c]++;

			int num = arr[pos.r][pos.c];
			for (int i = 0; i < 4; i++) {
				Pos p;
				if (num % 2 == 0) {
					switch (i) {
					case 0:
						p = new Pos(pos.r, pos.c - 1);
						break;
					case 1:
						p = new Pos(pos.r - 1, pos.c);
						break;
					case 2:
						p = new Pos(pos.r, pos.c + 1);
						break;
					default:
						p = new Pos(pos.r + 1, pos.c);
						break;
					}
					if ((0 <= p.r && p.r < m) && (0 <= p.c && p.c < n) && room[p.r][p.c] == 0) {
						q.offer(p);
						room[p.r][p.c] = c;
					}
				}
				num = num / 2;
			}

		}
	}
}
