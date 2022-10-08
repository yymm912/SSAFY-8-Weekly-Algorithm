package problem.SW;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


// M 시간 이후의 남아있는 미생물의 수 
public class SW2382_미생물격리 {
	static int ans, T, N, M, K;
	static List<Micro> micros = new ArrayList<>();

	// 상하좌우
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };

	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			micros.clear();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				micros.add(new Micro(i, y, x, s, d));
			}

			// 초기화
			ans = 0;

			// 시뮬레이션
			for (int time = 0; time < M; time++) {
				move();
				check();
			}

			for (Micro m : micros) ans += m.s;

			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");

			// break;

		}

		System.out.println(sb.toString());

	} // end main


	// 1시간 마다 미생물을 이동시키는 함수
	private static void move() {
		for (Micro m : micros) {
			int ny = m.y + dy[m.d];
			int nx = m.x + dx[m.d];

			// 약품이 발라져 있는 곳
			if (ny <= 0 || ny >= N - 1 || nx <= 0 || nx >= N - 1) {
				m.s /= 2;
				m.d = dir(m.d);
			}

			m.y = ny;
			m.x = nx;
		}

	} // end move


	// 칸이 겹치는 미생물이 있는지 확인하는 함수
	private static void check() {

		for (int i = micros.size() - 1; i >= 0; i--) {
			if (i >= micros.size()) continue;
			Micro m = micros.get(i);

			List<Micro> same = new ArrayList<>();
			same.add(m);

			for (int j = micros.size() - 1; j >= 0; j--) {
				if (i == j) continue;
				Micro mCT = micros.get(j); // comparison target

				if (m.y == mCT.y && m.x == mCT.x) {
					same.add(mCT);
					micros.remove(j);
				}

			}

			if (same.size() > 1) {
				int no = 0;
				int sum = 0;
				int dir = 0;
				int max = Integer.MIN_VALUE;

				for (Micro micro : same) {
					sum += micro.s;
					if (micro.s > max) {
						max = micro.s;
						dir = micro.d;
						no = micro.no;
					}

				}

				m.no = no;
				m.d = dir;
				m.s = sum;
			}

		}

	} // end check


	private static int dir(int d) {
		if (d == 1) d = 2;
		else if (d == 2) d = 1;
		else if (d == 3) d = 4;
		else if (d == 4) d = 3;
		return d;
	} // end dir


	static class Micro {
		int no;
		int y, x;
		int s; // 미생물 수
		int d; // 방향


		public Micro(int no, int y, int x, int s, int d) {
			this.no = no;
			this.y = y;
			this.x = x;
			this.s = s;
			this.d = d;
		}


		@Override
		public String toString() {
			return "Micro [no=" + no + ", y=" + y + ", x=" + x + ", s=" + s + ", d=" + d + "]";
		}

	} // end Micro
}
