package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ15651_N과M3 {
	static int ans, N, M;
	static int[] tgt;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tgt = new int[M];

		perm(0);

		System.out.println(sb.toString());
	}


	private static void perm(int dep) {
		if (dep == M) {
			for (int n : tgt) sb.append(n).append(" ");
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			tgt[dep] = i;
			perm(dep + 1);
		}
	} // end perm
}

/*
 * 조건을 만족하는 수열을 출력 
 * 1부터 N 까지 자연수 중에서 중복없이 M 개를 고른 수열 => 순열
 * => 중복 순열 
 * */
