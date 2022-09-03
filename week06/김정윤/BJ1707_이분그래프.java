package week6.김정윤;

import java.io.*;
import java.util.*;

public class BJ1707_이분그래프 {
	static int K, V, E;
	static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
	static int[] visit; // 방문 여부
	static boolean isBipartite; // 이분 그래프인지 판별
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점
			E = Integer.parseInt(st.nextToken()); // 간선
			visit = new int[V+1]; // 0 dummy
			
			for (int i = 0; i <= V; i++) { // 0 dummy
				node.add(new ArrayList<>());
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 그래프 생성(방향 무관)
				node.get(from).add(to);
				node.get(to).add(from);
			}
			
			isBipartite = true;
			for (int i = 1; i <= V; i++) {
				if (visit[i] == 0) // 방문한 적 없는 정점일 경우
					dfs(i, 1); // 이분그래프 체크
			}
			for (int i = 1; i <= V; i++) { // 시작 정점
				for (int n : node.get(i)) { // 도착 정점
					if(visit[i] == visit[n]) { // 시작 정점과 도착 정점이 일치하는 경우
						isBipartite = false; // 이분 그래프가 아님
					}
				}
			}
			System.out.println(isBipartite ? "YES" : "NO");
		}
	}
	static void dfs(int c, int v) {
		visit[c] = v;
		for (int i : node.get(c)) { // 도착 정점
			if (visit[i] == 0) { // 방문한적 없는 경우
				dfs(i, 3-v); // 0: 그룹 생성 X, 1: 첫 번째 그룹, 2: 두 번째 그룹 => 총 3번
			}
		}
	}
}
