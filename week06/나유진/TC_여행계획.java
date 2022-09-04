package TC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class tc_여행계획 {
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parents = new int[N + 1]; // 0 dummy

		MakeSet();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 1) {
					Union(i, j);
				}
			}
		}

		boolean ans = true;
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int Ap = Find(A);
		for (int i = 1; i < M; i++) {
			int B = Integer.parseInt(st.nextToken());
			int Bp = Find(B);
			if (Ap == Bp)
				continue;
			else {
				ans = false;
				break;
			}
		}

		if (ans)
			System.out.println("YES");
		else
			System.out.println("NO");

	}

	static void MakeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	static void Union(int a, int b) {
		int ap = Find(a);
		int bp = Find(b);

		if (a < b) {
			parents[ap] = bp;
		} else {
			parents[bp] = ap;
		}
	}

	static int Find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = Find(parents[a]);
	}
}
