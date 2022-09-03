package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_2980_도로와신호등 {
	static int N, L, time = 0, p = 0; // 시각, 현재위치
	static Light[] light;
	static int lIdx = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		light = new Light[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			light[i] = new Light(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		while (lIdx != N) {
			// 다음 신호등까지 막히지 않고 직진.
			time += light[lIdx].D-p;
			p =light[lIdx].D;
			int stop = time % (light[lIdx].R + light[lIdx].G);
			if (stop < light[lIdx].R) {
				time += light[lIdx].R- stop;
			} // 멈춰야함
			lIdx++;
		}
		
		//길 마지막까지 가는 데걸리는 시간
		time += L -p;

		System.out.println(time);
	}

	static class Light {
		int D;
		int R;
		int G;

		public Light(int d, int r, int g) {
			D = d;
			R = r;
			G = g;
		}

	}

}
