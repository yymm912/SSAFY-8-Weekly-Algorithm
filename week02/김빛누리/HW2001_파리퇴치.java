package swea;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HW2001_파리퇴치{
	static int[][] grid;
	static int logic(int N, int M) {
		grid = new int[N][N];
		int max = 0;
		int tmp;

		for (int i = 0; i <= N - M; i++) {
			tmp = 0;
			for (int j = 0; j <= N - M; j++) {
				int x = j;
				int y = i;
				tmp = 0;

				while (y < i + M) {

					tmp += grid[y][x++];
					if (x == j + M) {
						x = j;
						y++;
					}

				}
				if (tmp > max)
					max = tmp;
			}

		}
		return max;

	}

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			// 간단한 숫자 입력
			String[] str = sc.nextLine().split(" ");
			int N = sc.nextInt();
			int M = sc.nextInt(); // 파리채 크기

			// 2차원 숫자 배열 입력
			grid = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = sc.nextInt();
				}
			}
			int result = logic(N, M);

			String output = String.format("#%d %d", test_case, result);
			System.out.println(output);
			
			

		}	
		long end = System.nanoTime();
	}
}