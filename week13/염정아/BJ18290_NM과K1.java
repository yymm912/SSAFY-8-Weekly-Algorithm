package problem.BJ;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ18290_NM과K1 {
	static int ans, N, M, K;
	static int[][] map;
	static boolean[][] visit;

	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ans = Integer.MIN_VALUE;
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0);

		System.out.println(ans);

	} // end main


	private static void dfs(int dep, int sum) {
		if (dep == K) {
			ans = Math.max(ans, sum);
			return;
		}

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				// 현재 위치 방문헀는지 확인
				if (visit[y][x]) continue;

				// 상하좌우 인접한 부분 방문했는지 확인
				// 만약 이전에 체크한 부분에서 인접한 부분이라면 그냥 다음으로 넘어간다.
				boolean flag = true;
				for (int d = 0; d < 4; d++) {
					int ny = y + dy[d];
					int nx = x + dx[d];

					if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

					if (visit[ny][nx]) flag = false;
				}

				// 만약 인접한 부분이 아니라면 dep 를 탄다.
				if (flag) {
					visit[y][x] = true;
					dfs(dep + 1, sum + map[y][x]);
					visit[y][x] = false;
				}
			}
		}

	} // end dfs

}

/*
 * N x M
 * 칸 K 선택  
 * 인접한 칸을 선택하면 안된다. (상하좌우) 에 있는 칸이 인접한 칸이다.
 * K 칸을 선택했을 때, 이 K 칸의 수를 모두 더한 값의 최대 => ans 
 * 
 * 1 2
 * 3 4
 * 
 * =>
 * 1 4 -> 5
 * 2 3 -> 5
 * 여기서 최대는 5 이다.
 * 일단 나중에 합을 모두 더하는 함수를 구하고, => Math.max(ans, max) 로 답을 구한다. 
 * 
 * 어떻게 칸을 선택하지? 칸을 선택하는 것은 재귀로 풀 수 있다.
 * 첫번째 칸을 선택하고
 * 내가 할 일이 뭐지 ?
 * 1. 칸을 선택한다.
 * 2. 인접한 칸은 피한다. 
 * visit 체크를 해야하나?
 * 움직여야할 것은 int y, int x
 * 
 * 일단 첫번째 값을 정한다.
 * (0, 0) 이 정해졌어. 
 * 그 다음에 내가 할 일은 뭔가 다음으로 보내고
 * 인접한 칸인지 ?
 * 
 * (0, 0)
 * 그 다음에 뭐 대충 보낸다면 => (0, 1)
 * (1, 0)
 * (1, 1) 이런식으로 찾아보지 않을까? 행을 겨눈다음에 그 다음에 열을 보는 방식으로...
 * 근데 만약에 해당 선택하려는 숫자가  (r-1, c), (r+1, c), (r, c-1), (r, c+1) 내에 있다면 skip
 * 
 * 첫번째 숫자도 perm 인 거고 
 * (0, 0), (0, 1), (0, 2)
 * (1, 0), (1, 1), (1, 2)
 * 
 * */
