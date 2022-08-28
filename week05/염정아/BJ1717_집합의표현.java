package first.m08.month.practice;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ1717_집합의표현 {
	static int N, M;
	static int[] p;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N + 1];

		makeSet();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (op == 1) {
				System.out.println(findSet(a) == findSet(b) ? "YES" : "NO");
			} else union(a, b);

		}

	} // end main


	private static void makeSet() {
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

	} // end makeSet


	private static int findSet(int x) {
		if (x == p[x]) return x;

		return p[x] = findSet(p[x]);
	} // end findSet


	private static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px < py) p[py] = findSet(p[px]);
		else p[px] = findSet(p[py]);

	} // end union
}
