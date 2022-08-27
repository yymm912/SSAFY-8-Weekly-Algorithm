package lecture.day220822.problem;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


// N: 데이터의 길이 
// S: 시작 vertex
// map: 인접 행렬 
// parents: 자신의 부모 노드를 표시하는 배열 
// rank: depth 관리 배열 
// ans: 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람 
public class SW1238_Contact {
	static int N, S, t, ans;
	static int[][] map;
	static int[] parents, rank;
	static boolean[] visit;
	static Queue<Integer> q = new ArrayDeque<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			++t;
			String tc = br.readLine();

			if (tc == null || tc.length() == 0) break;

			StringTokenizer st = new StringTokenizer(tc);

			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			// 초기화
			map = new int[101][101];
			visit = new boolean[101];
			parents = new int[101];
			rank = new int[101];

			ans = 0;
			q.clear();
			pq.clear();

			// 인접 행렬 만들기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				map[from][to] = 1;
			}

			bfs(S);

			// 마지막에 연락이 닿은 사람들의 트리 레벨 찾기
			int rankMax = Arrays.stream(rank).max().getAsInt();
			find(rankMax);

			ans = pq.poll();

			sb.append("#" + t + " " + ans + "\n");

		} // end tc

		System.out.println(sb.toString());

	} // end main


	static void bfs(int idx) {
		visit[idx] = true;
		q.offer(idx);

		while (!q.isEmpty()) {
			idx = q.poll();

			rank[idx] = rank[parents[idx]] + 1;

			for (int i = 1; i <= 100; i++) {
				if (!visit[i] && map[idx][i] == 1) {
					visit[i] = true;
					q.offer(i);
					parents[i] = idx;
					rank[i] = 1;
				}

			}

		}

	} // end dfs


	// 마지막에 연락이 닿은 노드를 pq에 추가하는 함수
	static void find(int rankMax) {
		for (int i = 1; i <= 100; i++) if (rank[i] == rankMax) pq.offer(i);
	} // end find

}
