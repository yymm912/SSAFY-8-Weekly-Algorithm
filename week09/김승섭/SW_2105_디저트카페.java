package algo1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * 1차 제출 오류: dfs 기저 조건을 시작하자마자 넣어 ny nx가 출발지점에 들어가지도 못해서 count 자체를 못했음
 * 
 * 
 * 2차 제출 오류: 문제에서 요구하는 경로는 사각형 모양을 그려야했다.
 * 					=> dfs parameter로 방향을 추가해서 4방향만 그릴 수 있는 경로만 고정함.
 * 
 * 3차 제출 오류: dfs 내에서 방문 false 해제를 안해줬다. ㅇㅏ..
 *
 */

public class SW_디저트카페_2105 {
	static int N, max, ans, sx, sy;
	static int[][] map;
	// 좌상- 좌하 - 우하- 우상
	static int[] dy = { -1, 1, 1, -1 };
	static int[] dx = { -1, -1, 1, 1 };
	static boolean[] cafe; 			// 같은 종류의 수를 지닌 가게인지 체크
	static boolean[][] visited; 	// 방문 배열
	static boolean canGo; 			// 출발지점까지 다시 돌아오는지 체크

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			/*************** case ****************/
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			canGo = false;
			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[N][N];
					cafe = new boolean[101];
					visited[i][j] = true;
					cafe[map[i][j]] = true;
					sy = i; sx = j;
					dfs(i, j, 1, 0);
				}
			}

			System.out.println("#" + t + " " + (canGo ? max : -1));
			/*************** case ****************/

		}

	}

	static void dfs(int y, int x, int depth, int dir) {


		for (int d = dir; d < 4; d++) { // 사각형을 그리기 때문에 같은 방향으로 다시 그리지 않는다.
			int ny = y + dy[d];			// 어짜피 Main에서 모든 배열을 순회하며 dfs할 때 4방향만을 이용해 그리는 요소가 포함돼있기 때문.
			int nx = x + dx[d];			// = 여기서 시작하나 저기서 시작하나 똑같다..
			
			
			if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			if(depth > 2 &&  ny == sy && nx == sx) { // 시작하자마자가 아니라(종료하지 못하고 돌아오는 경우를 제외시킴), 끝부분에서 출발지점으로 돌아왔을 때,
				canGo = true;
				max = Math.max(max, depth);
				continue;
			}
			if(!visited[ny][nx] && !cafe[map[ny][nx]]) {
				visited[ny][nx] = true;
				cafe[map[ny][nx]] = true;
				dfs(ny, nx, depth+1, d);
				visited[ny][nx] = false;
				cafe[map[ny][nx]] = false;
			}
		}
	}

}
