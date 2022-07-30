package al.jul;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Turret {
	static int T;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			String[] sub = s.split(" ");
			double x1 = Double.parseDouble(sub[0]); // A의 x좌표
			double y1 = Double.parseDouble(sub[1]); // y좌표
			double r1 = Double.parseDouble(sub[2]); // A 좌표 류재명과 거리

			double x2 = Double.parseDouble(sub[3]); // B의 x좌표
			double y2 = Double.parseDouble(sub[4]); // y좌표
			double r2 = Double.parseDouble(sub[5]); // B에서 류재명과 거리

			double xd = Math.pow(x1-x2, 2); // (x1-x2)(x1-x2) // Math.pow(숫자, 제곱수)
			double yd = Math.pow(y1-y2, 2);
			double d = Math.sqrt(xd + yd); // 제곱근

			int res = 0; // default = -1, 두 원이 겹치지 않는 경우 류재명(res)는 위치를 가질 수 없음.

			if (r1 + r2 > d && Math.max(r1, r2)< Math.min(r1, r2)+d) {
				// 두 점 사이의 거리(d)가 각 지점에서 류재명까지의 거리(r1+r2)보다
				// 작으면 두 원이 겹치므로 류재명(res)은 2개의 위치를 가질 수 있음.
				res = 2;
			} else if (r1 + r2 == d || Math.abs(r1 - r2) == d) { // 같으면 두 원이 교접하므로 류재명(res)은 1개의 위치를 가질 수 있음.
				res = 1; // Math.abs, 절댓값
			}

			if (x1 == x2 && y1 == y2 && r1 == r2) { // 만약 두 원이 완전히 겹치는 경우 류재명(res)의 위치는 무한대임. 이럴경우 -1 출력.
				res = -1;
			}

			System.out.println(res);

		}
	}
}
