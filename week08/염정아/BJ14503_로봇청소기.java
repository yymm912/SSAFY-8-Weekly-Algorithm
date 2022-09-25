package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// ry, rx, rd: 로봇 청소기 위치, 방향 
public class BJ14503_로봇청소기 {
	static int ans, N, M, ry, rx, rd;
	static int turn;
	static int[][] map;

	// 상우하좌
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		ry = Integer.parseInt(st.nextToken());
		rx = Integer.parseInt(st.nextToken());
		rd = Integer.parseInt(st.nextToken());
		// System.out.println(ry + " " + rx + " " + rd);

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
			// System.out.println(Arrays.toString(map[i]));
		}

		// 초기화
		ans = 1;
		turn = 0;
		map[ry][rx] = 3;

		// 시뮬레이션
		while (true) {
			if (turn == 4) {
				// 후진이 가능하다면 후진
				int backDir = turnBack();

				int ny = ry + dy[backDir];
				int nx = rx + dx[backDir];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) break;
				if (map[ny][nx] == 1) break;

				ry = ny;
				rx = nx;
				turn = 0;
				continue;
			}

			turnLeft();
			turn++;

			int ny = ry + dy[rd];
			int nx = rx + dx[rd];

			if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
			if (map[ny][nx] == 1) continue;

			if (map[ny][nx] == 0) {
				ry = ny;
				rx = nx;

				map[ny][nx] = 3; // 청소 처리
				turn = 0;
				ans++;
			}

		}

		System.out.println(ans);

	} // end main


	private static void turnLeft() {
		rd = (rd + 3) % 4;
	} // end turnLeft


	private static int turnBack() {
		return (rd + 2) % 4;
	}

}
