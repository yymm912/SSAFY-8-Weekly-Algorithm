// BOJ 9372번 상근이의 여행 

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_9372 {

	static int T, N, M, ans;
	static int[] parent;
	static int[][] edge;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			makeSet();

			edge = new int[M][2];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				edge[m][0] = Integer.parseInt(st.nextToken());
				edge[m][1] = Integer.parseInt(st.nextToken());
			}

			int cnt = 0;
			ans = 0;
			for (int i = 0; i < M; i++) {
				if (findSet(edge[i][0]) != findSet(edge[i][1])) {
					cnt++;
					union(edge[i][0], edge[i][1]);
					ans++;
				}
				if (cnt == N - 1)
					break;
			}

			System.out.println(ans);
		}
	}

	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = findSet(parent[x]);
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);

		if (px < py)
			parent[py] = parent[px];
		else
			parent[px] = parent[py];
	}

}
