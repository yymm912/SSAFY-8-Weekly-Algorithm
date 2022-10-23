package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ10971_외판원순회2 {
	static int ans, N;
	static int[][] W; // 인접행렬
	static int[] tgt;
	static boolean[] select;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) W[i][j] = Integer.parseInt(st.nextToken());
		}

		// 초기화
		tgt = new int[N];
		select = new boolean[N];
		ans = Integer.MAX_VALUE;

		// 순조부
		perm(0);

		// 출력
		System.out.println(ans);

	} // end main


	private static void perm(int dep) {
		if (dep == N) {
			int sum = 0;
			for (int i = 0; i < N - 1; i++) {
				if (W[tgt[i]][tgt[i + 1]] == 0) return; // 가는 길이 없으니 리턴
				sum += W[tgt[i]][tgt[i + 1]];
			}

			if (W[tgt[N - 1]][tgt[0]] == 0) return;
			else sum += W[tgt[N - 1]][tgt[0]];

			ans = Math.min(ans, sum);

			return;
		}

		for (int i = 0; i < N; i++) {
			if (select[i]) continue;
			tgt[dep] = i;

			select[i] = true;
			perm(dep + 1);
			select[i] = false;
		}

	} // end perm
}
