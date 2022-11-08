package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ6987_월드컵 {
	static final int T = 4, N = 6, M = 3;

	static int ans;
	static boolean flag;

	static int[] team1 = new int[15];
	static int[] team2 = new int[15];
	static int[] win = new int[N];
	static int[] draw = new int[N];
	static int[] lose = new int[N];


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 6C2 = 15
		// [0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4]
		// [1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5]
		// 총 2개의 팀이 30개의 승무패가 난다.
		int idx = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = i + 1; j < 6; j++) {
				team1[idx] = i;
				team2[idx] = j;
				idx++;
			}

		}

		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int sum = 0;
			for (int i = 0; i < N; i++) {
				// win: [5, 3, 2, 0, 4, 1]
				// draw: [0, 0, 0, 0, 0, 0]
				// lose: [0, 2, 3, 5, 1, 4]

				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());

				sum += win[i] + draw[i] + lose[i];
			}

			flag = false;
			if (sum == 30) dfs(0);

			// 출력
			if (flag) System.out.print(1 + " ");
			else System.out.print(0 + " ");

			break;

		}

	} // end main


	private static void dfs(int dep) {
		if (flag) return; // 가지 치기

		// 15번 경기를 다 치뤘으면
		if (dep == 15) {
			flag = true;
			return;
		}

		// 겨루는 나라 2개
		int t1 = team1[dep];
		int t2 = team2[dep];

		// t1 win
		if (win[t1] > 0 && lose[t2] > 0) {
			win[t1]--;
			lose[t2]--;
			dfs(dep + 1);
			win[t1]++;
			lose[t2]++;
		}

		// t1 draw t2
		if (draw[t1] > 0 && draw[t2] > 0) {
			draw[t1]--;
			draw[t2]--;
			dfs(dep + 1);
			draw[t1]++;
			draw[t2]++;
		}

		// t1 lose
		if (lose[t1] > 0 && win[t2] > 0) {
			lose[t1]--;
			win[t2]--;
			dfs(dep + 1);
			lose[t1]++;
			win[t2]++;
		}

	} // end dfs

}
