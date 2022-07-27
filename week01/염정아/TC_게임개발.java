package study.week01;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [입력]
 * - N M (맵의 크기)
 * - y, x, d (캐릭터 초기위치, 바라보는 방향)
 * - 맵의 상태 (N개의 줄)
 * return 이동마치고 캐릭터가 방문한 칸의 수 출력 
 * 
 * [주의]
 * - 0123 북동남서
 * - 0: 육지, 1: 바다 
 * */

//4 4
//1 1 0
//1 1 1 1
//1 0 0 1
//1 1 0 1
//1 1 1 1
// -> 3

//3 4
//0 0 2
//0 0 0 1
//0 1 1 1
//0 1 1 1
// -> 3

//4 5
//1 2 0
//1 1 1 1 1
//1 1 0 0 1
//1 0 0 0 1
//1 1 1 1 1
// -> 4


public class TC_게임개발 {
	static int N, M, y, x, d;
	static int[][] map, visited;

	// 북동남서
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		System.out.println(N + " " + M);

		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		System.out.println(y + " " + x + " " + d);

		// 배열 입력
		map = new int[N][M];
		visited = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

			System.out.println(Arrays.toString(map[i]));
		}

		// 배열 깊은 복사
		for (int i = 0; i < N; i++) {
			visited[i] = map[i].clone();
		}

		int ans = 1; // 이동한 칸의 수
		int turn = 0; // 턴의 수
		visited[y][x] = 1; // 시작위치 visited

		// 탐색
		while (true) {
			// 현재 방향에서 왼쪽방향부터 일단 돌고 시작
			d = (d + 3) % 4;
			int ny = y + dy[d];
			int nx = x + dx[d];

			// System.out.println(d + " " + y + " " + x);
			// 유효하지 않은 범위
			if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

			// 만약 이동한 곳이 육지고, 아직 방문하지 않았다면
			if ((map[ny][nx] == 0) && (visited[ny][nx] == 0)) {
				// 그자리로 이동
				visited[ny][nx] = 1;
				y = ny;
				x = nx;
				ans++;
				turn = 0;
			} else turn++;

			// 네방향 모두 가본 곳거나 바다인
			if (turn == 4) {
				// 방향 유지한채로 뒤로 한칸 간다.
				ny = y - dy[d];
				nx = x - dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (map[ny][nx] == 1) break;

				y = ny;
				x = nx;
				turn = 0;
			}

		} // while end

		System.out.println("ans: " + ans);

	} // main end
}
