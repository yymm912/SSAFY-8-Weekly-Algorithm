package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ15684_사다리조작 {
	static int ans, N, M, H;
	static int[][] ladder;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		ans = Integer.MAX_VALUE;
		ladder = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			ladder[a][b] = 1; // 오른쪽으로 이동
			ladder[a][b + 1] = 2; // 왼쪽으로 이동
		}

		// 완전탐색
		for (int i = 0; i <= 3; i++) dfs(0, i);

		// 3개 이상이거나, 불가능한 경우
		if (ans == Integer.MAX_VALUE) ans = -1;

		// 출력
		System.out.println(ans);

	} // end main


	private static void dfs(int dep, int cnt) {
		if (dep == cnt) {
			if (check()) ans = Math.min(ans, dep);
			return;
		}

		for (int i = 1; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				// 사다리를 이미 놓은 곳은 가지 않는다.
				if (ladder[i][j] != 0) continue;
				if (ladder[i][j + 1] != 0) continue;

				// 사다리를 놓는다.
				ladder[i][j] = 1;
				ladder[i][j + 1] = 2;
				dfs(dep + 1, cnt);
				ladder[i][j] = 0;
				ladder[i][j + 1] = 0;
			}

		}

	} // end dfs


	// i -> i 번째로 가는지 확인하는 함수
	private static boolean check() {
		// 열(x)을 기준으로 훑어본다.
		// 행(y)을 따라 내려가며 0이 아닌 숫자가 있는지 확인한다. 다른 사다리로 넘어가는 지점.
		for (int i = 1; i <= N; i++) {

			int y = 1;
			int x = i;
			while (y <= H) {
				if (ladder[y][x] == 1) x++;
				else if (ladder[y][x] == 2) x--;
				y++;
			}

			if (x != i) return false;
		}

		return true;
	} // end check
}
