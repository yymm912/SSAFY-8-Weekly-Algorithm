package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA2805농작물수확하기 {

	static int T, N;
	static int map[][];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("SWEA2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			// 입력 완
			
			int center = N / 2;
			int width = 0;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				for (int j = center - width; j < center; j++)
					sum += map[i][j];
				sum += map[i][center];
				for (int j = center + width; j > center; j--)
					sum += map[i][j];

				if (i < center)
					width++;
				else
					width--;
			}

			System.out.println("#" + t + " " + sum);
		} // testcase

	}

}
