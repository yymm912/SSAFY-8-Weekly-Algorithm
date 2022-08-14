package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11724_연결요소의개수 {
	static int N, M, ans;
	static int[][] map;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 노드 개수 와 간선 선언
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		
		map = new int[N+1][N+1]; // 노드 탐색 저장 배열
		isVisited = new boolean[N+1]; // 방문 여부 체크

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 간선의 양 끝점 선언
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 해당 노드 존재여부 체크
			map[a][b] = 1;
			map[b][a] = 1;
		}
		for (int i = 1; i <= N; i++) { // 방문하지 않은 열은 dfs 탐색한 후 노드연결 개수 카운트를 증가시킨다.
			if (!isVisited[i]) { dfs(i); ans++; }
		}
		
		System.out.println(ans);
	}

	static void dfs(int num) {
		
		isVisited[num] = true; // 해당 열은 방문 처리
		
		for (int i = 1; i <= N; i++) { // 전체 열을 탐색하여 방문하지 않고 선언된 노드라면 탐색.
			if(map[num][i] == 1 && !isVisited[i]) dfs(i); // 마친 열은 방문처리되며 탐색을 끝낸다.
		}
		
		return;
	}

}
