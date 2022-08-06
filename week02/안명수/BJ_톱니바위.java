package algorithm.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14891 {

	// 톱니바퀴
	// 0 = N극 , 1 = S극
	static char[][] wheel = new char[5][];

	// 회전 횟수, 톱니 번호, 회전방향
	static int K, num, dir;

	// 각 톱니바퀴들의 동쪽, 서쪽에 해당하는 인덱스 저장
	static int[] east = new int[5];
	static int[] west = new int[5];

	// 각 톱니바퀴들의 회전방향 저장
	static int[] spin = new int[5];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 1; i <= 4; i++) {
			wheel[i] = br.readLine().toCharArray();
			east[i] = 2;
			west[i] = 6;
		}

		K = Integer.parseInt(br.readLine());

		// K회 톱니 회전
		while (K > 0) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			num = Integer.parseInt(stk.nextToken());
			dir = Integer.parseInt(stk.nextToken());

			spin[num] = dir;
			wheelMove(true);

			K--;
		}

		// 각 톱니의 12시 인덱스 구하기
		for (int i = 1; i <= 4; i++) {
			east[i] -= 2;
			if (east[i] < 0)
				east[i] = 8 + east[i];

		}

		// 각 톱니의 12시 위치 값에따른 결과 결정
		int sum = 0;
		for (int i = 1, n = 1; i <= 8; i *= 2, n++)
			if (wheel[n][east[n]] == '1')
				sum += i;

		System.out.println(sum);
	}

	// 톱니바퀴 회전
	static void wheelMove(boolean move) {

		// dir이 1이면 시계방향
		// dir이 -1이면 반시계방향
		update(num, spin[num]);

		// 왼쪽
		// 회전하는동안 반복
		for (int i = num - 1, w; i >= 1 && move; i--) {
			w = west[i + 1];

			if (move) {
				w += spin[i + 1];
				if (w == -1) w = 7;
				else if (w == 8) w = 0;
			}
			
			move = false;
			// 오른쪽 톱니의 회전 이전의 좌측문양과 비교
			if (wheel[i][east[i]] != wheel[i + 1][w]) {
				spin[i] = -spin[i + 1];
				update(i, spin[i]);
				move = true;
			}

		}

		move = true;
		// 오른쪽 
		// 회전하는동안 반복
		for (int i = num + 1, e; i <= 4 && move; i++) {
			e = east[i - 1];
			
			if(move) {
				e += spin[i - 1];
				if (e == -1) e = 7;
				else if (e == 8) e = 0;
			}
			move = false;
			
			// 왼쪽 톱니의 회전 이전의 우측문양과 비교
			if (wheel[i - 1][e] != wheel[i][west[i]]) {
				spin[i] = -spin[i - 1];
				update(i, spin[i]);
				move = true;
			}
		}
	}

	// 회전
	static void update(int num, int dir) {
		west[num] -= dir;
		if (west[num] == -1)
			west[num] = 7;
		else if (west[num] == 8)
			west[num] = 0;

		east[num] -= dir;
		if (east[num] == -1)
			east[num] = 7;
		else if (east[num] == 8)
			east[num] = 0;
	}
}
