package week10.김정윤;

import java.io.*;
import java.util.*;

public class BJ3190_뱀 {
	static int N, K, L, X;
	static char C;
	static int[][] map, dir;
	// 우-하-좌-상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static List<int[]> snake = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보드 크기
		K = Integer.parseInt(br.readLine()); // 사과 개수
		map = new int[N+1][N+1];
		snake.add(new int[] {1, 1}); // 뱀 시작 위치
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			map[row][col] = 1; // 사과 위치 저장
		}
		
		L = Integer.parseInt(br.readLine());
		dir = new int[L][2];
		
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			C = st.nextToken().charAt(0);
			
			dir[i][0] = X;
			if (C == 'L') dir[i][1] = -1; // L - 왼쪽 90도
			else dir[i][1] = 1; // D - 오른쪽 90도
		}
		System.out.println(Dummy(1, 1, 0, dir));
	}
	private static int Dummy(int cx, int cy, int cd, int[][] dir) {
		int time = 0;
		int turn = 0;
		
		while (true) {
			time++;
			int nx = cx + dx[cd];
			int ny = cy + dy[cd];
			
			// 게임 종료
			// 다음 위치가 맵의 범위를 벗어나는 경우
			if (nx <= 0 || nx > N || ny <= 0 || ny > N) return time;
			// 맵에 뱀이 닿은 경우
			for (int i = 0; i < snake.size(); i++) {
				int[] s = snake.get(i);
				if (s[0] == nx && s[1] == ny) return time;
			}
			
			if (map[nx][ny] == 1) { // 사과를 먹은 경우
				snake.add(new int[] {nx, ny});
				map[nx][ny] = 0;
			} else { // 사과를 먹지 못한 경우
				snake.add(new int[] {nx, ny});
				snake.remove(0); // 꼬리 제거
			}
			
			cx = nx;
			cy = ny;
			
			if (turn < L) {
				if (time == dir[turn][0]) {
					int next = (cd + dir[turn][1]) % 4;
			        if (next == -1) next = 3;
					cd = next;
                    turn++;
				}
			}

		}
	}
}
