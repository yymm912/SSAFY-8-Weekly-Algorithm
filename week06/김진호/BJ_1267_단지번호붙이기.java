import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BJ_1267_단지번호붙이기 {

	static int N;
	static int[][] map;

	static boolean[][] visited;
	static int dy[] = { -1, 1, 0, 0 };
	static int dx[] = { 0, 0, -1, 1 };
	static List<Integer> list = new ArrayList<>();
	static int homeCnt;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					dfs(i, j);
					list.add(cnt);
					cnt = 0;
				}
			}
		}
		System.out.println(list.size());
		Collections.sort(list, (o1, o2) -> o1 - o2);
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));

	}

	static void dfs(int y, int x) {
		cnt++;
		visited[y][x] = true;
		for (int d = 0; d < 4; d++) {

			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N || map[ny][nx] == 0 || visited[ny][nx])
				continue;
			dfs(ny, nx);
		}

	}

}
