import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717_집합의표현 {
	static int n, m;
	static int op, a, b;
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

			op = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (op == 0)
				union(a, b);
			else {
				if (findSet(a) == findSet(b))
					System.out.println("YES");
				else
					System.out.println("NO");

			}

		}

	}

	static void makeSet() {
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if (parent[x] == x)
			return x;
		else
			return parent[x] = findSet(parent[x]);
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px > py)
			parent[py] = px;
		else
			parent[px] = py;

	}

}
