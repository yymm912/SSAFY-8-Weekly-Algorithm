package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {

	static int n, m;
	static int[][] map;
	static int[] parent;
	static int[] init;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		parent = new int[n + 1];
		int start;
		boolean flag=true;
		
		for (int i = 1; i < n + 1; i++)parent[i] = i;

		for (int i = 1; i < n + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j < n + 1; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x==1)union(i, j);//연결시킴
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		start = findset(Integer.parseInt(st.nextToken()));//첫번째꺼
		
		for (int i = 1; i < m; i++) {
			int x = Integer.parseInt(st.nextToken());

			if (start != findset(x)) {//다르면
				flag=false;
				break;
			}
		}

		System.out.println(flag ? "YES" : "NO");
	}

	static void union(int a, int b) {
		int pa = findset(a);
		int pb = findset(b);

		if (pa > pb)
			parent[pa] = pb;
		else
			parent[pb] = pa;
	}

	static int findset(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = findset(parent[x]);
	}
}
