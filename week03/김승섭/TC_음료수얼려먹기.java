package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_음료수얼려먹기 {

	static int N, M, COUNT;
	static int[][] map;
	static boolean[][] visited;
	static boolean isIcecream;
	// 상 - 하 - 좌 - 우 순
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		
		// map 배열 선언
		// 0 ==> 구멍 , 1 ==> 칸막이
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		COUNT = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				isIcecream = false;
				dfs(i, j);
				if(isIcecream) COUNT++;
			}
		}
		System.out.println("Count: "+COUNT);
		
		
	}
	
	static void dfs(int y, int x) {
		//종료 조건 - 범위 밖을 초과함
		if(x >= M || y >= N || x < 0 || y < 0 || visited[y][x] || map[y][x] == 1 ) {
			isIcecream = false;
			return;
		}
		visited[y][x] = true;

		for (int d = 0; d < 4; d++) {

			int nx = x + dx[d];
			int ny = y + dy[d];
			if(nx < M && ny < N && nx >= 0 && ny >=0) {

			dfs(ny, nx);
			isIcecream = true;
			}
		}
		

	}

}
