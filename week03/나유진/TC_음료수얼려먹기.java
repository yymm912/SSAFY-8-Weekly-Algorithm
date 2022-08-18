package TC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class tc_음료수얼려먹기 {
	static int N; // 배열 크기
	static int M;
	static char[][] ice; // 아이스 값
	static int ans;
	static int[] dx = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ice = new char[N][M];
		for (int i = 0; i < N; i++) {
			ice[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (ice[i][j] == '0') {
					dfs(i, j);
					ans++;
				}
			}
		}

		System.out.println(ans);
	}

	static void dfs(int i, int j) {
		ice[i][j] = '1'; // 방문한 후엔 1로 만듦. 방문체크
		for (int k = 0; k < 4; k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && ice[nx][ny] == '0') {
				dfs(nx, ny);
			}
		}
	}
}
