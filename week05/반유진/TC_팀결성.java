// BOJ 1717번 집합의 표현
// 이코테 - 팀 결성

package BOJ.Data_Structures;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_1717 {

	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		makeSet();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (op == 0)
				union(a, b);
			else if (op == 1) {
				if (findSet(a) == findSet(b))
					System.out.println("YES");
				else
					System.out.println("NO");
			}

		}
	}

	static void makeSet() {
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int a) {
		if (parent[a] == a)
			return a;

		return parent[a] = findSet(parent[a]);
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px < py)
			parent[py] = px;
		else
			parent[px] = py;
	}

}
