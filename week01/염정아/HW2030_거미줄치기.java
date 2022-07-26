package study.week01;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [입력] 
 * - N: 맵 크기
 * - map: (0 공백, 1 장애물)
 * return 거미줄 치기 시작한 위치 && 최댓값 
 * 
 * [주의] 
 * - 장애물에 거미줄 못침 
 * - 가로, 세로, 대각선 
 * - 한칸 건너서는 치기 가능, 그 이상은 불가능 
 * */


// 3-4 거미줄 치기
public class HW2030_거미줄치기 {
	public static int N, saveY, saveX;
	public static int[][] map;

	public static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };
	public static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

		}

		// max 값
		int max = Integer.MIN_VALUE;

		// 탐색
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 0) {
					int sum = 1;

					// 8방 탐색
					for (int d = 0; d < 8; d++) {
						int ny = y + dy[d];
						int nx = x + dx[d];

						if (ny < 0 || ny >= N || nx < 0 || nx >= N)
							continue;

						int prev = map[y][x];
						while (true) {
							if ((map[ny][nx] == 1) && (prev == 1)) break;

							if (map[ny][nx] == 0) sum++;

							prev = map[ny][nx];
							ny += dy[d];
							nx += dx[d];

							if (ny < 0 || ny >= N || nx < 0 || nx >= N) break;
						}

					}

					// 만약 최댓값을 갱신한다면
					if (max < sum) {
						saveY = y;
						saveX = x;
						max = sum;
					}

				}

			}

		} // 탐색 end

		System.out.println(max);
		System.out.println(saveY + "," + saveX);

	} // main end
} // class end
