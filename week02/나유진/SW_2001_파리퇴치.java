

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class fly_sw_2001 {
	static int[][] arr;
	static int T, N, M;
	static int sum = 0, max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			arr = new int[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + Integer.parseInt(st.nextToken());
				}
			}

			max = Integer.MIN_VALUE;

			for (int i = 1; i <= N - (M - 1); i++) {
				for (int j = 1; j <= N - (M - 1); j++) { //M*M이 가능한 점 방문
					int temp =arr[i+M-1][j+M-1]-arr[i+M-1][j-1]-arr[i-1][j+M-1]+arr[i-1][j-1]; //사각형 누적합 빼기 
					if(temp>max) {
						max = temp; // max 값 변경
					}
				}
			}
			System.out.println("#" + t + " " + max);
		}

	}
}
