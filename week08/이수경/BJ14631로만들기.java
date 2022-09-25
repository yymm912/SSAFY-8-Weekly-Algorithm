package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ14631로만들기 {

	static int X;
	static int max = 100;
	static int num[] = new int[max + 1];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		X = Integer.parseInt(br.readLine());
		
		num[1] = 0;
		num[2] = 1;

		for (int i = 3; i <= max; i++) {
			// 1. 3으로 나누어 떨어지면 num/=3
			// 2. 2로 나누어 떨어지면 num/=2
			// 3. num-1

			// 둘 다 나누어 떨어지는 경우 횟수 더 작은걸로
			if (i % 2 == 0 && i % 3 == 0) {
				if (num[i / 3] + 1 < num[i / 2] + 1) {
					num[i] = num[i / 3] + 1;
				}
				else {
					num[i] = num[i / 2] + 1;
				}
				if (num[i - 1] + 1 < num[i]) {
					num[i] = num[i - 1] + 1;
				}
					
			} else if (i % 3 == 0) { // 3으로만 나누어 떨어지는 경우 이전 dp+1 과 /3 +1 비교
				if (num[i / 3] + 1 < num[i - 1] + 1){
						num[i] = num[i / 3] + 1;
				}
				else {
					num[i] = num[i - 1] + 1;
				}
			} else if (i % 2 == 0) { // 2로만 나누어 떨어지는 경우 이전 dp+1 과 /2 + 1비교 
				if (num[i / 2] + 1 < (num[i - 1] + 1)) {
					num[i] = num[i / 2] + 1;
				}
				else {
					num[i] = num[i - 1] + 1;
				}
			} else { // 모두 해당하지 않는 경우 num-1 
				num[i] = num[i - 1] + 1;
			}
		}

		System.out.println(num[X]);

	}

}
