// SWEA 1824번 혁진이의 프로그램 검증 

package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1824 {

	static int T, R, C;
	static boolean check;
	static char[][] map;

	static boolean[][][][] visit;

	// 상-하-좌-우
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			boolean endAt = false;
			map = new char[R][];
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();

				for (int j = 0; j < C; j++) {
					if (map[i][j] == '@')
						endAt = true;
				}
			}

			if (endAt) {
				check = false;
				visit = new boolean[R][C][4][16];
				dfs(0, 0, 3, '0');
			} else {
				check = false;
			}

			if (check)
				System.out.println("#" + t + " YES");
			else
				System.out.println("#" + t + " NO");
		}

	}

	static void dfs(int y, int x, int d, char mem) {
		if (check) {
			return;
		}
		if (map[y][x] == '@') {
			check = true;
			return;
		}

		int visitMem = mem - '0';
		if (visit[y][x][d][visitMem])
			return;
		visit[y][x][d][visitMem] = true;

		if (map[y][x] == '<')
			d = 2;
		else if (map[y][x] == '>')
			d = 3;
		else if (map[y][x] == '^')
			d = 0;
		else if (map[y][x] == 'v')
			d = 1;
		else if (map[y][x] == '_') {
			if (mem == '0')
				d = 3;
			else
				d = 2;
		} else if (map[y][x] == '|') {
			if (mem == '0')
				d = 1;
			else
				d = 0;
		} else if (map[y][x] == '.') {

		} else if (map[y][x] == '+') {
			int intMem = mem - '0';
			if (intMem == 15)
				intMem = 0;
			else
				intMem += 1;

			mem = (char) (intMem + '0');
		} else if (map[y][x] == '-') {
			int intMem = mem - '0';
			if (intMem == 0)
				intMem = 15;
			else
				intMem -= 1;

			mem = (char) (intMem + '0');
		} else if (map[y][x] == '0' || map[y][x] == '1' || map[y][x] == '2' || map[y][x] == '3' || map[y][x] == '4'
				|| map[y][x] == '5' || map[y][x] == '6' || map[y][x] == '7' || map[y][x] == '8' || map[y][x] == '9') {

			mem = map[y][x];

		} else if (map[y][x] == '?') {
			for (int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];

				if (ny < 0)
					ny = R - 1;
				if (ny >= R)
					ny = 0;
				if (nx < 0)
					nx = C - 1;
				if (nx >= C)
					nx = 0;

				dfs(ny, nx, dir, mem);
			}

			return;
		}

		int ny = y + dy[d];
		int nx = x + dx[d];

		if (ny < 0)
			ny = R - 1;
		if (ny >= R)
			ny = 0;
		if (nx < 0)
			nx = C - 1;
		if (nx >= C)
			nx = 0;

		dfs(ny, nx, d, mem);
	}

}
