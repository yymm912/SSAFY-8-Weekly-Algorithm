package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj_18310_안테나 {
	static int N, Idx;
	static int[] H;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		H = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			H[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(H);

		if (N % 2 == 0)
			Idx = N / 2 - 1;
		else
			Idx = N / 2;

		System.out.println(H[Idx]);

	}
}
