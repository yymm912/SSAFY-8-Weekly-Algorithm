package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14499_주사위굴리기 {
	static int map[][];
	static int N, M, R, C, K;
	static int[] dice;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		dice = new int[6];

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			int command = Integer.parseInt(st.nextToken());

			rolling(command);
		}

		System.out.println(sb);
	}

	public static void rolling(int command) {
		switch (command) {
		case 4:
			if (R + 1 >= N)
				break;
			R++;
			change(4);
			sb.append(dice[3] + "\n");
			if (map[R][C] == 0) {
				map[R][C] = dice[1];
			} else {
				dice[1] = map[R][C];
				map[R][C] = 0;
			}
			break;

		case 3:
			if (R - 1 < 0)
				break;
			R--;
			change(3);
			sb.append(dice[3] + "\n");
			if (map[R][C] == 0) {
				map[R][C] = dice[1];
			} else {
				dice[1] = map[R][C];
				map[R][C] = 0;
			}
			break;

		case 2:
			if (C - 1 < 0)
				break;
			change(2);
			C--;
			sb.append(dice[3] + "\n");
			if (map[R][C] == 0) {
				map[R][C] = dice[1];
			} else {
				dice[1] = map[R][C];
				map[R][C] = 0;
			}
			break;

		case 1:
			if (C + 1 >= M)
				break;
			change(1);
			C++;
			sb.append(dice[3] + "\n");
			if (map[R][C] == 0) {
				map[R][C] = dice[1];
			} else {
				dice[1] = map[R][C];
				map[R][C] = 0;
			}
		}
	}

	public static void change(int c) {
		int temp = 0, temp2 = 0;
		switch (c) {
		case 4:
			temp = dice[0];
			for (int i = 0; i < 3; i++) {
				dice[i] = dice[i + 1];
			}
			dice[3] = temp;
			break;
		case 3:
			temp = dice[3];
			for (int i = 3; i > 0; i--) {
				dice[i] = dice[i - 1];
			}
			dice[0] = temp;
			break;
		case 2:
			temp = dice[3];
			dice[3] = dice[5];
			temp2 = dice[4];
			dice[4] = temp;
			temp = dice[1];
			dice[1] = temp2;
			dice[5] = temp;
			break;
		case 1:
			temp = dice[3];
			dice[3] = dice[4];
			temp2 = dice[5];
			dice[5] = temp;
			temp = dice[1];
			dice[1] = temp2;
			dice[4] = temp;
			break;
		}
	}
}
