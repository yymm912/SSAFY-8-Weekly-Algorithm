package BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_13251_조약돌꺼내기 {
	static int N, K, sum = 0; // 구슬 색 수, 뽑아야하는 수, 총 구슬 갯수
	static int[] src; // 색깔 별 구슬 갯 수 담을 공간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		src = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			sum += n; // 총 구슬 갯수
			src[i] = n;
		}

		K = Integer.parseInt(br.readLine());

		// 입력

		double ans = 0; // 구슬을 뽑을 갯수. 나중에 sum으로 나누어 확률 계산.
		for (int i = 0; i < N; i++) {
			if (src[i] >= K) {
				double temp = 1;
				for (int j = 0; j < K; j++) {
					temp = temp * (src[i] - j) / (sum - j);
				}
				ans += temp;
			}
		}
		System.out.println(ans);
	}
}
