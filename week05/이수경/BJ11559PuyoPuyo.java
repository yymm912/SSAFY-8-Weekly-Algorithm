package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ11559PuyoPuyo {

	static char map[][];
	static boolean visit[][];
	static int[] dx = { 0, 0, -1, 1 }; // 상 하 좌 우
	static int[] dy = { -1, 1, 0, 0 };
	static int cnt;
	static int ans;
	static boolean flag;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 필드에 여러 가지 색깔의 뿌요를 놓는다.
		// 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.

		// 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다.
		// 이때 1연쇄가 시작된다.

		// 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
		// 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할
		// 때마다 1연쇄씩 늘어난다.

		map = new char[12][6];
		visit = new boolean[12][6];
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		// 입력 완

		while (true) {
			
			//초기화 
			flag = false;
			for (int k = 0; k < 12; k++) Arrays.fill(visit[k], false);

			
			// 1. 뿌요 제거 
			for (int i = 0; i <12; i++) {
				for (int j = 0; j < 6; j++) {
					
					// 4개 이상 연결된 뿌요 탐색
					cnt = 0;
					if (map[i][j] != '.')	dfs(i, j);
					// cnt가 4를 넘지 않으면 visit초기화
					if (cnt < 4) {
						for (int k = 0; k < 12; k++) Arrays.fill(visit[k], false);
					} else {
						// 뿌요 제거
						if (cnt >= 4) {
							// visit 체크 된 뿌요들 삭제
							for (int y = 0; y < 12; y++)
								for (int x = 0; x < 6; x++) {
									if (visit[y][x]) map[y][x] = '.';
								}
							flag = true;
						}
					}
					
				}
			}
			if (flag) ans++;
			else break;

			
			// 2. 뿌요들이 제거된 자리위에 뿌요들이 있다면 아래로 이동
			for (int y = 10; y >= 0; y--) {
				for (int x = 0; x < 6; x++) {
					if (map[y][x] != '.') { // 뿌요일 경우

						int py = y;
						char pyo = map[y][x];
						
						while (true) {
							// 맨 아래까지
							py++;
							if (py == 12 || map[py][x] != '.')	break;

							map[py - 1][x] = '.';
							map[py][x] = pyo;

						}

					}
				}
			}

		} // while

		System.out.println(ans);

	}

	static void dfs(int y, int x) {
		if (visit[y][x])
			return;

		cnt++;
		visit[y][x] = true;

		for (int d = 0; d < 4; d++) {

			int py = y + dy[d];
			int px = x + dx[d];

			if (py < 0 || py >= 12 || px < 0 || px >= 6 || map[y][x] != map[py][px] || visit[py][px])
				continue;
			dfs(py, px);
		}

	}
}
