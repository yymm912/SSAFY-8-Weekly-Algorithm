package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BJ15663_N과M9 {
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

		int prev = -1;
		for (int i = 0; i < N; i++) {
			if (select[i]) continue;
			if (src[i] == prev) continue;

			tgt[dep] = src[i];
			select[i] = true;
			prev = src[i];
			perm(dep + 1);
			select[i] = false;
		}

	} // end perm

}

/*
 * 어디서 잡아야하는거지 ?
 * 9 7 9 1
 * 1 7 9 9
 * =>
 * 1 7
 * 1 9
 * 1 9 (X)
 * 7 1
 * 7 9
 * 7 9 (X)
 * 9 1
 * 9 7
 * 9 9
 * 
 * 지금 X가 되는 부분이 =>
 * 이전 tgt에 있던 배열이 있으면 X 가 되는 것이다.
 * */
