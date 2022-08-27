package algo_study._8월3주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630_색종이만들기 {

	static int white, blue, N;
	static int[][] board;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		partition(0, 0, N);

		System.out.println(white);
		System.out.println(blue);

	}

	public static void partition(int y, int x, int size) {
		if (colorCheck(y, x, size)) {
			if (board[y][x] == 0) {
				white++;
			} else {
				blue++;
			}
			return;
		}

		int newSize = size / 2; // 절반 사이즈
		// 재귀 호출
		partition(y, x, newSize); // 1구역
		partition(y, x + newSize, newSize); // 2구역
		partition(y + newSize, x, newSize); // 3구역
		partition(y + newSize, x + newSize, newSize); // 4구역
	}

	// 현재 구역의 컬러가 같은지 체크한다.
	static boolean colorCheck(int y, int x, int size) {

		int color = board[y][x]; 
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (board[i][j] != color) {
					return false; // 색상이 같이 않다면 false를 리턴
				}
			}
		}
		// 검사 모두 통과했으므로 true 리턴
		return true;
	}

}
