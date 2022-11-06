package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17825_주사위윷놀이 {
	static int[] tgt = new int[10];
	static int[] dice;
	static int[] map = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, // 0 ~ 21
			10, 13, 16, 19, 25, 30, 35, 40, 0, // 22 ~ 30
			20, 22, 24, 25, 30, 35, 40, 0, // 31 ~ 38
			30, 28, 27, 26, 25, 30, 35, 40, 0 }; // 39 ~ 47
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dice = new int[10];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		perm(0);
		System.out.println(max);

	}

	static void perm(int tgtidx) {
		if (tgtidx == 10) {
			cal();
			return;
		}
		for (int i = 0; i < 4; i++) {
			tgt[tgtidx] = i;
			perm(tgtidx + 1);
		}
	}

	static void cal() {
		int a0 = 0;
		int a1 = 0;
		int a2 = 0;
		int a3 = 0;// 말 위치 초기화
		int score = 0;

		for (int i = 0; i < 10; i++) {
			int d = dice[i];
			int h = tgt[i]; // 말 번호

			switch (h) {
			case 0:
				if (isEnd(a0))
					return;
				else {
					if (isDuplicated(a0, a1, a2, a3, d))
						return;

					int tmp = otherRoad(a0, d);
					a0 = tmp;
					score += map[a0];
				}
				break;
			case 1:
				if (isEnd(a1))
					return;
				else {
					if (isDuplicated(a1, a0, a2, a3, d))
						return;

					int tmp = otherRoad(a1, d);
					a1 = tmp;
					score += map[a1];
				}
				break;
			case 2:
				if (isEnd(a2))
					return;
				else {
					if (isDuplicated(a2, a0, a1, a3, d))
						return;

					int tmp = otherRoad(a2, d);
					a2 = tmp;
					score += map[a2];
				}
				break;
			case 3:
				if (isEnd(a3))
					return;
				else {
					if (isDuplicated(a3, a0, a1, a2, d))
						return;

					int tmp = otherRoad(a3, d);
					a3 = tmp;
					score += map[a3];
				}
				break;
			}
		}
		max = Math.max(max, score);
	}

	static boolean isEnd(int idx) {
		return (idx == 21) || (idx == 30) || (idx == 38) || (idx == 47);
	}

	static boolean isDuplicated(int h1, int h2, int h3, int h4, int d) {
		// 도착점 넘김
		if (h1 < 21 && h1 + d >= 21)
			return false;
		else if (h1 < 30 && h1 + d >= 30)
			return false;
		else if (h1 < 38 && h1 + d >= 38)
			return false;
		else if (h1 < 47 && h1 + d >= 47)
			return false;

		// 5,10,15 다른 데로 갈 수도 있
		int tmp = h1 + d;
		if (tmp == 5)
			tmp = 22;
		if (tmp == 10)
			tmp = 31;
		if (tmp == 15)
			tmp = 39;

		// 점프할 곳에 있는 것 있는지
		if (tmp == h2 || tmp == h3 || tmp == h4)
			return true;

		// 25, 30, 35, 40 은 중복 공유위치
		if (map[tmp] == 25) {
			if (map[h2] == 25 || map[h3] == 25 || map[h4] == 25)
				return true;
		} else if (map[tmp] == 35) {
			if (map[h2] == 35 || map[h3] == 35 || map[h4] == 35)
				return true;
		} else if (map[tmp] == 40) {
			if (map[h2] == 40 || map[h3] == 40 || map[h4] == 40)
				return true;
		} else if (map[tmp] == 30) {
			if (tmp == 39) {
				if (h2 == 39 || h3 == 39 || h4 == 39)
					return true;
			} else if (tmp == 27) {
				if (h2 == 27 || h3 == 27 || h4 == 27)
					return true;
				if (h2 == 35 || h3 == 35 || h4 == 35)
					return true;
				if (h2 == 44 || h3 == 44 || h4 == 44)
					return true;
			} else if (tmp == 35) {
				if (h2 == 27 || h3 == 27 || h4 == 27)
					return true;
				if (h2 == 35 || h3 == 35 || h4 == 35)
					return true;
				if (h2 == 44 || h3 == 44 || h4 == 44)
					return true;
			} else if (tmp == 44) {
				if (h2 == 27 || h3 == 27 || h4 == 27)
					return true;
				if (h2 == 35 || h3 == 35 || h4 == 35)
					return true;
				if (h2 == 44 || h3 == 44 || h4 == 44)
					return true;
			}
		}

		return false;

	}

	static int otherRoad(int h1, int d) {
		int tmp = h1 + d;
		if (h1 < 21) {
			if (tmp >= 21)
				tmp = 21;
		} else if (h1 < 30) {
			if (tmp >= 30)
				tmp = 30;
		} else if (h1 < 38) {
			if (tmp >= 38)
				tmp = 38;
		} else if (h1 < 47) {
			if (tmp >= 47)
				tmp = 47;
		}

		if (tmp == 5)
			return 22;
		if (tmp == 10)
			return 31;
		if (tmp == 15)
			return 39;

		return tmp;

	}

}
