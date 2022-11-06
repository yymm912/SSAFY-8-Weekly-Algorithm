package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ15650_N과M2 {
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

		comb(0, 1);
	}


	private static void comb(int dep, int idx) {
		if (dep == M) {
			for (int n : tgt) System.out.print(n + " ");
			System.out.println();
			return;
		}

		for (int i = idx; i <= N; i++) {
			tgt[dep] = i;
			comb(dep + 1, i + 1);
		}
	} // end comb
}

/*
 * 조건을 만족하는 수열을 출력 
 * 1부터 N 까지 자연수 중에서 중복없이 M 개를 고른 수열 => 순열
 * 순열인데 고른 수열이 오름차순이어야 한다.
 * => 조합 ?
 * */
