package day220802.problem;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class HW_달팽이숫자 {
	static int T, N;
	static int[][] map, visited;

	// 우 -> 하 -> 좌 -> 상
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };


	public static void go(int num, int y, int x, int d) {
		// 기저 조건
		if (num == N * N + 1) return;

		// 내가 할 수 있는 일
		map[y][x] = num;

		// d 를 설정하고 ny, nx 를 구한다.
		int ny = y + dy[d];
		int nx = x + dx[d];

		if (ny < 0 || ny >= N || nx < 0 || nx >= N || map[ny][nx] != 0) {
			d = (d + 1) % 4;
			ny = y + dy[d];
			nx = x + dx[d];
		}

		// 재귀 호출
		go(num + 1, ny, nx, d);

	}


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];

			go(1, 0, 0, 0);

			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
