package ps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Guild {
	static int N;
	static Integer[] arr;
	static int X, cnt, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new Integer[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		

//		Arrays.sort(arr, Collections.reverseOrder()); // 배열 내림차수 정렬을 하려면 배열cInteger타입으로 선언
//		for (int i = 0; i < N; i++) {
//			if (cnt == 0) {
//				X = arr[i];
//				cnt++;
//			} else {
//				cnt++;
//				if (cnt == X) {
//					answer++;
//					cnt = 0;
//				}
//			}
//
//		}

		// 6
		// 4 3 3 3 3 2
		// ????????????????????

		// 공포도 X인 모험가는 X명 이상이면 그룹이 될 수 있다.
		// 1 2 2 2 3
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			cnt++;
			if (arr[i] <= cnt) {
				answer++;
				cnt = 0;
			}

		}
		System.out.println(answer);

	}

}
