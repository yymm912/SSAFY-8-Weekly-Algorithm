// BOJ 14891번 톱니바퀴 

package BOJ.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_14891 {

	static int[][] gear = new int[4][8];
	static int K;
	static int[][] arrK;

	static int[] dir;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 4; i++) {
			char[] temp = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = temp[j] - '0';
			}
		}

		K = Integer.parseInt(br.readLine());
		arrK = new int[K][2];
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arrK[i][0] = Integer.parseInt(st.nextToken());
			arrK[i][1] = Integer.parseInt(st.nextToken());
		}

		solution();

		int ans = 0;
		int add = 1;
		for (int i = 0; i < 4; i++) {
			if (gear[i][0] == 1) { // S극
				ans += add;
			}
			add *= 2;
		}

		System.out.println(ans);
	}

	static void solution() {
		for (int k = 0; k < K; k++) {
			dir = new int[4];

			int num = arrK[k][0] - 1; // 톱니바퀴 번호
			int d = arrK[k][1];

			right_check(num, d);
			left_check(num, d);

			for (int i = 0; i < 4; i++) {
				if (dir[i] == -1) {
					gear[i] = unRotation(gear[i]);
				} else if (dir[i] == 1) {
					gear[i] = rotation(gear[i]);
				}
			}
		}

	}

	static void right_check(int num, int d) {
		dir[num] = d;

		while (true) {
			if (num + 1 < 4) {
				if (gear[num][2] != gear[num + 1][6]) {
					dir[num + 1] = dir[num] * (-1);
				} else {
					dir[num + 1] = 0;
				}
				num++;
			} else {
				return;
			}
		}
	}

	static void left_check(int num, int d) {
		dir[num] = d;

		while (true) {
			if (num > 0) {
				if (gear[num][6] != gear[num - 1][2]) {
					dir[num - 1] = dir[num] * (-1);
				} else {
					dir[num - 1] = 0;
				}
				num--;
			} else {
				return;
			}
		}
	}

	static int[] rotation(int[] list) { // 시계 방향
		int tmp = list[7];
		for (int i = 6; i >= 0; i--) {
			list[i + 1] = list[i];
		}
		list[0] = tmp;

		return list;
	}

	static int[] unRotation(int[] list) { // 반시계 방향
		int tmp = list[0];
		for (int i = 0; i < 7; i++) {
			list[i] = list[i + 1];
		}
		list[7] = tmp;

		return list;
	}

}
