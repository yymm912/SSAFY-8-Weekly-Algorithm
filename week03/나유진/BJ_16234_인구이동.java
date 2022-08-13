package algorithm_assignments;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_16234_인구이동 {
	static int N, L, R; // 나라 크기, 최소 값, 최대 값
	static int[][] arr; // 인구 수 저장
	static boolean[][] visit; // 방문 체크

	static Queue<int[]> q = new LinkedList<>();
	static int[] dx = { 1, -1, 0, 0 }; // 하 - 상 - 우- 좌
	static int[] dy = { 0, 0, 1, -1 };
	static int ans = 0; // 인구이동필요한 날 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			visit = new boolean[N][N]; // 인구이동 필요할 때마다 초기화
			boolean check = false;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						int n = dfs(i, j);
						if (q.size() != 1) { // dfs 경로가 자기 자신 외 더 있다면 move()
							check = true; // 인구이동한 날
							move(n);
						}
						q.clear();
					}
				}
			}

			if (check == false) // 더이상 인구이동이 없었다면 break;
				break;
			ans++; // 인구이동 day ++
		}

		System.out.println(ans);

	}

	static void move(int n) { // queue있는 dfs경로의 좌표들을 뽑아 모두 (모든 인구수/나라 수)값으로 전환
		int avg = n / q.size();
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			arr[cur[0]][cur[1]] = avg;
		}
	}

	static int dfs(int n, int m) {
		int ans = arr[n][m];
		visit[n][m] = true;
		q.offer(new int[] { n, m }); // queue에 dfs 경로 모두 저장

		for (int i = 0; i < 4; i++) {
			int nx = n + dx[i];
			int ny = m + dy[i];

			if (!check(nx, ny))
				continue;

			// 이번 턴에 방문한 적 없고 인접한 자리와 L 이상 R이하 만큼 차이 나면 탐색
			if (!visit[nx][ny] && Math.abs(arr[n][m] - arr[nx][ny]) >= L && Math.abs(arr[n][m] - arr[nx][ny]) <= R) {
				ans += dfs(nx, ny); // 인구수 누적
			}

		}

		return ans;
	}

	static boolean check(int n, int m) { // 방문가능한지 체크
		return n >= 0 && n < N && m >= 0 && m < N;
	}
}
