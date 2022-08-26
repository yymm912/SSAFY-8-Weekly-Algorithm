// BOJ 1922번 네트워크 연결 

package BOJ.Graph_Theory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_1922 {

	static int N, M;
	static int[] parent;
	static int[][] edge;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		parent = new int[N + 1];
		makeSet();

		edge = new int[M][3];
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			edge[m][0] = Integer.parseInt(st.nextToken());
			edge[m][1] = Integer.parseInt(st.nextToken());
			edge[m][2] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(edge, (e1, e2) -> e1[2] - e2[2]);

		int cnt = 0;
		int sum = 0;
		for (int i = 0; i < M; i++) {
			if (findSet(edge[i][0]) != findSet(edge[i][1])) {
				cnt++;
				union(edge[i][0], edge[i][1]);
				sum += edge[i][2];
			}

			if (cnt == N - 1)
				break;
		}
		System.out.println(sum);
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
			parent[py] = px;
		else
			parent[px] = py;
	}
}
