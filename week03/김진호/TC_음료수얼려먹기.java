package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_음료수얼려먹기 {
	static int N, M;
	static int arr[][];
	static int answer;
	static boolean visited[][];

	// 상 하 좌 우
	// dy -1 1 0 0
	// dx 0 0 -1 1

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0 && !visited[i][j]) { // 현재 좌표가 유효할 때 DFS로 가능한 모든 얼음 틀 탐색 후 정답추
					DFS(i, j);
					answer++;
				}

			}
		}
		System.out.println(answer);

	}

	static void DFS(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= M || visited[x][y] || arr[x][y] == 1) {
			return;
		}

		if (arr[x][y] == 0 && !visited[x][y]) {
			visited[x][y] = true;
			// 상
			DFS(x - 1, y);
			// 하
			DFS(x + 1, y);
			// 좌
			DFS(x, y - 1);
			// 우
			DFS(x, y + 1);
		}

	}

}
