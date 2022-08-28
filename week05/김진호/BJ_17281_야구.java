import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17281_야구 {
	static int N;
	static int[] ining;
	static int[][] result;
	static int[] base;
	static int outCnt;
	static int answer = Integer.MIN_VALUE;

	static int iningCnt;

	// 타순
	static int[] taga;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		ining = new int[10];
		result = new int[N][10];
		base = new int[4];
		taga = new int[10];
		visited = new boolean[10];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 1; j < 10; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(1);
		System.out.println(answer);

	}

	static void playBaseball(int[] taga) {
		// 삼진이 N만큼 나오면 종료
		// 아웃을 당하면 인덱스 기억 해야함
		int score = 0;
		int idx = 1;

		for (int i = 0; i < N; i++) {// 이닝
			Arrays.fill(base, 0);
			int outCnt = 0;
			while (true) {
				if (outCnt == 3)
					break;

				int curTaga = taga[idx];
				int curResult = result[i][curTaga];

				if (curResult == 0) {
					outCnt++;
				}

				else {
					base[0] = curTaga;
					for (int k = 0; k < curResult; k++) {
						// 1만큼 진루하기
						int temp = base[3];
						// 3루석에 누군가 있다면
						if (temp != 0) {
							score++;
						}
						for (int j = 3; j > 0; j--) {
							base[j] = base[j - 1];
						}
						base[0] = 0;

					}
				}
				idx++;
				if (idx == 10)
					idx = 1;

			}

		}
		answer = Math.max(answer, score);

	}

	static void perm(int cnt) {
		if (cnt == 10) {
			if (taga[4] == 1) {
//				taga = new int[] { 0, 2, 3, 4, 1, 5, 6, 7, 8, 9 };
				playBaseball(taga);
				return;
			}

		}

		for (int i = 1; i < 10; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			taga[cnt] = i;
			perm(cnt + 1);
			visited[i] = false;

		}
	}

}
