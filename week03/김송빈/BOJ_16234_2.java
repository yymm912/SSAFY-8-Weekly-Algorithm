package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16234_2 {

	static int n;
	static int l;
	static int r;
	static int[][] map;
	static int[][] visited;

	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, -1, 1, 0 };
	static int check = 1;
	static int ans;

	static void dfs(int y, int x) {

		// if (y < 0 || x < 0 || y >= n || x >= n) return;

		// 국경선 여는 곳 확인
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny < 0 || nx < 0 || ny >= n || nx >= n)
				continue;
			// 차이 확인
			int diff = Math.abs(map[y][x] - map[ny][nx]);
			if (diff >= l && diff <= r) {
				visited[y][x] = check;//몇 번째 유닛인지 표시
				// 국경선 열 수 있는 국가 표시
				if (visited[ny][nx] != 0)
					continue;
				dfs(ny, nx);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//while문 돌려서 동시에 발생하는 인구이동 구현
		while (true) {
			boolean flag = true;
			int cnt = 0;
			check = 1;
			visited = new int[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] != 0)
						continue;
					dfs(i, j);
					check++;
					cnt++;
				}
			}


			if (cnt == n * n)
				break;

			// 이동
			int[] uni = new int[check];//동시에 일어나는 이동 수만큼 유닛 
			int[] unicnt = new int[check];//유닛에 포함되는 국가 수
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == 0)
						continue;
					uni[visited[i][j]] += map[i][j];//인구 합
					unicnt[visited[i][j]]++;//횟수 증가
				}
			}

			// 평균값으로 매꿔줌
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == 0)
						continue;
					map[i][j] = uni[visited[i][j]] / unicnt[visited[i][j]];
				}
			}
		}

		System.out.println(ans);

	}
}