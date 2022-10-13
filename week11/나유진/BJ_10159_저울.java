package STUDY.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_10159_저울 {
	static int N, M;
	static boolean visit[][]; // 열 : 자신의 부모, 행 :자신의 자식 // dfs 하며 자신의 부모를 체크 해놓으면 나중에 열을 보면 자식수를 알 수 있음.
	static List<List<Integer>> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 물건 갯수
		M = Integer.parseInt(br.readLine()); // 비교 갯수

		visit = new boolean[N + 1][N + 1];

		for (int i = 0; i <= N; i++) { // 0 dummy
			list.add(new ArrayList<>());
		}

		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b); // 방향이 있는 그래프
		}

		for (int i = 1; i <= N; i++) {
			dfs(i);
		}

		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (visit[i][j])
					cnt++; // 자신의 부모 체크. 자신보다 무거운 물건 수
				if (visit[j][i])
					cnt++; // 자신의 자식 체크. 자신보다 가벼운 물건 수 
			}
			System.out.println(N - cnt - 1); // 모든 정점 - ( 자신보다 무거운 물건+자신보다 가벼운물건 ) - 자기 자신
		}

	}

	private static void dfs(int i) {
		Queue<Integer> queue = new ArrayDeque<>();
		// 자기 자신부터 dfs 시작
		// dfs 하며 자신보다 visit[][] 에 visit check
		queue.add(i);

		while (!queue.isEmpty()) {
			int n = queue.poll();
			List<Integer> parent = list.get(n);
			for (int j = 0; j < parent.size(); j++) {
				int p = parent.get(j);
				if (!visit[p][i]) {
					queue.add(p);
					visit[p][i] = true;
				}
			}
		}
	}
}
