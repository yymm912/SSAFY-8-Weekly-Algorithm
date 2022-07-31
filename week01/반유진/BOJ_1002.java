// 1002번 터렛

package BOJ.Mathematics;

import java.util.Scanner;

public class No_1002 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int r1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			int r2 = sc.nextInt();

			int result = point(x1, y1, r1, x2, y2, r2);
			System.out.println(result);
		}
	}

	public static int point(int x1, int y1, int r1, int x2, int y2, int r2) {
		double dis = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);

		// 1. 두 원의 중심이 같고, 반지름도 같을 때 (접점의 개수가 무한)
		if (x1 == x2 && y1 == y2 && r1 == r2) {
			return -1; // 무제한일땐 -1 출력
		}
		// 2. 접점이 없을 때
		// 2-1. 두 점 사이의 거리가 각 원의 반지름의 합보다 클 때
		else if (dis > Math.pow(r1 + r2, 2)) {
			return 0;
		}
		// 2-2. 한 원 안에 다른 원이 있으면서 접점이 없을 때
		else if (dis < Math.pow(r2 - r1, 2)) {
			return 0;
		}
		// 3. 접점이 하나일 때
		// 3-1. 내접할 때
		else if (dis == Math.pow(r2 - r1, 2)) {
			return 1;
		}
		// 3-2. 외접할 때
		else if (dis == Math.pow(r1 + r2, 2)) {
			return 1;
		}
		// 위의 조건 이외에는 모두 접점 두개
		else {
			return 2;
		}
	}
}
