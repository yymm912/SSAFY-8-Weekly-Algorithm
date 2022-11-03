package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ15649_N과M1 {
	static int ans, N, M;
	static int[] tgt;
	static boolean[] select;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tgt = new int[M];
		select = new boolean[N + 1];

		perm(0);
	}


	private static void perm(int dep) {
		if (dep == M) {
			for (int n : tgt) System.out.print(n + " ");
			System.out.println();
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (select[i]) continue;

			tgt[dep] = i;
			select[i] = true;
			perm(dep + 1);
			select[i] = false;
		}
	} // end perm
}

/*
 * 조건을 만족하는 수열을 출력 
 * 1부터 N까지 자연수 중에서 중복없이 M 개를 고른 수열 ?
 * 즉슨, 순열.
 * */
