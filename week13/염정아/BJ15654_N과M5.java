package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ15654_N과M5 {
	static int ans, N, M;
	static int[] tgt, src;
	static boolean[] select;

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		tgt = new int[M];
		src = new int[N];
		select = new boolean[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) src[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(src);

		perm(0);

		System.out.println(sb.toString());
	}


	private static void perm(int dep) {
		if (dep == M) {
			for (int n : tgt) sb.append(n).append(" ");
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (select[i]) continue;

			tgt[dep] = src[i];
			select[i] = true;
			perm(dep + 1);
			select[i] = false;
		}
	} // end perm
}

/*
 * N개의 자연수는 모두 다른 수..
 * N개의 자연수 중에서 M개를 고른 수열 ? 
 * src 수열이 주어지는 구만.
 * */
