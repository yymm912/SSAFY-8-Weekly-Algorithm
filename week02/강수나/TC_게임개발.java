package algo_study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TC_게임개발 {

	static int[][] map;
	static int N, M, startX, startY, dir;
	static int[] dir_x = {-1, 0, 1, 0}; //북 동 남 서
	static int[] dir_y = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken());
		startY = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());
		
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x = 0; x < M; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		int cnt = 0;
		while (true) {
			map[startY][startX] = 2;
			dir--; //왼쪽으로 회전
			if (dir < 0) dir = 3;
			
			if (map[startY + dir_y[dir]][startX + dir_x[dir]] == 0) {
				startY += dir_y[dir];
				startX += dir_x[dir];
				map[startY][startX] = 2;
				ans++;
				cnt = 0;
			}
			else {
				cnt++;
			}
			if (cnt == 4) { //뒤로
				if (map[startY + dir_y[dir]][startX + dir_x[dir]] == 0) {
					startY += dir_y[dir-2];
					startX += dir_x[dir-2];
					cnt = 0;
				}
				else break;
 			}
		}
		
//		int ans = 0;
//		for (int y = 0; y < N; y++) {
//			for (int x = 0; x < M; x++) {
//				if (map[y][x] == 2) ans++;
//			}
//		}
		System.out.println(ans);
	}

}
