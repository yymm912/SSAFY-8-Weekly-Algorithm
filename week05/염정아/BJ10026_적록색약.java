package lecture.day220824.problem;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


// NxN
// no: 적록색약이 아닌 사람
// yes: 적록색약인 사람 
public class BJ10026_적록색약 {
	static int N, no, yes;
	static char[][] map;
	static boolean[][] visit;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R' && !visit[i][j]) {
					noDfs('R', i, j);
					no++;
				} else if (map[i][j] == 'G' && !visit[i][j]) {
					noDfs('G', i, j);
					no++;
				} else if (map[i][j] == 'B' && !visit[i][j]) {
					noDfs('B', i, j);
					no++;
				}

			}

		}

		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R' && !visit[i][j]) {
					yesDfs('R', i, j);
					yes++;
				} else if (map[i][j] == 'G' && !visit[i][j]) {
					yesDfs('G', i, j);
					yes++;
				} else if (map[i][j] == 'B' && !visit[i][j]) {
					yesDfs('B', i, j);
					yes++;
				}

			}

		}

		System.out.println(no + " " + yes);

	} // end main


	static void noDfs(char color, int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
			if (visit[ny][nx]) continue;
			if (map[ny][nx] != color) continue;

			visit[ny][nx] = true;
			noDfs(color, ny, nx);

		}

	} // end noDfs


	static void yesDfs(char color, int y, int x) {
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
			if (visit[ny][nx]) continue;
			if (color == 'R' || color == 'G') {
				if (map[ny][nx] == 'B') continue;
			} else {
				if (map[ny][nx] != color) continue;
			}

			visit[ny][nx] = true;
			yesDfs(color, ny, nx);

		}

	} // yesDfs

}
