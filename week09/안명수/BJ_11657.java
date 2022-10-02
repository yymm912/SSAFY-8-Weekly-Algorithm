package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11657 {
	static int N, M;
	static long INF = Long.MAX_VALUE;
	static ArrayList<ArrayList<int[]>> list = new ArrayList<>();

	static long[] cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());

		cost = new long[N + 1];
		Arrays.fill(cost, INF);

		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(br.readLine());
			int here = Integer.parseInt(stk.nextToken());
			int there = Integer.parseInt(stk.nextToken());
			int val = Integer.parseInt(stk.nextToken());
			list.get(here).add(new int[] { there, val });
		}

		cost[1] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cost[j] == INF) continue;
				for (int[] next : list.get(j)) {
					if (cost[j] + next[1] < cost[next[0]]) {
						if (i == N) {
							System.out.println(-1);
							return;
						}

						cost[next[0]] = cost[j] + next[1];
					}
				}
			}
		}

		for (int i = 2; i <= N; i++) {
			if (cost[i] == Long.MAX_VALUE)
				cost[i] = -1;

			System.out.println(cost[i]);
		}
	}

}
