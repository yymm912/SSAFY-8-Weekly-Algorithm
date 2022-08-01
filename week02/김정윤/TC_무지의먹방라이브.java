package week2.김정윤;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TC_무지의먹방라이브 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 먹어야 할 음식의 수
		int N = Integer.parseInt(br.readLine());
		// 음식을 먹는데 필요한 시간
		int[] food_times = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			food_times[i] = Integer.parseInt(st.nextToken());
		}
		
		// 네트워크 장애 발생 시간
		int K = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		int a = 0, i = 0;

		while (true) {
			// 네트워크 장애 발생 시
			if (i == K) {
				food_times[i%N]--;
				// 남은 음식이 있는지 확인
				for (int j = 0; j < N; j++) {
					if (food_times[j] != 0) cnt++;
				}
				// 남은 음식이 없을 경우
				if (cnt == N) {
					System.out.println(-1);
					break;
				} else { // 남은 음식이 있을 경우
					System.out.println((i-1)%N);
					break;
				}
			}
			
			if (food_times[i%N] == 0) {
				food_times[(i+1)%N]--;
				i = i+1;
			} else {
				food_times[i%N]--;
				i++;
			}
		}
	}

}
