package 페어_dfs_bfs연습;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//출제자 : 나유진
/*
[입력 값]
첫 줄에 배열의 크기 N, M이 주어진다.
둘째 줄부터 N줄만큼 배열이 주어진다.
짝수만 연속된 최대 길이를 구해라.

[입출력 예제]
[입력]
3 3
1 2 3
4 5 6
7 8 9

[출력]
1

[입력]
3 3
1 3 5
6 8 4
2 5 10

[출력]
5 
*/

public class DFS_나유진_Solution {
	static int N, M;
	static int[][] arr;
	static boolean[][] visit;
	static int[] dx = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
	static int[] dy = { 0, 0, -1, 1 };
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] % 2 == 0 && !visit[i][j]) {
					max = Math.max(max, dfs(i, j, 0));
				}
			}
		}
		System.out.println(max);
	}

	static int dfs(int x, int y, int d) {
		visit[x][y] = true;
		d++;
		int temp = 1;
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny])
				continue;
			if (arr[nx][ny] % 2 == 0) {
				temp += dfs(nx, ny, d);
			}
		}
		return temp;
	}
}
