package problem.TC;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [입력]
 * N (모험가의 수)
 * arr (각 모험가의 공포도)
 * retrun 만들 수 있는 그룹의 수
 * */

//5
//2 3 1 2 2
//-> 2

//5
//4 2 2 3 1
//-> 2


// 그리디 
public class TC_모험가길드 {
	static int N, ans = 1;
	static int[] arr;


	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		N = Integer.parseInt(br.readLine());
		System.out.println(N);

		// 배열 입력 받기
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = -Integer.parseInt(st.nextToken());
		}

		// 배열 내림차순 정렬하기
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			arr[i] = -arr[i];
		}

		System.out.println(Arrays.toString(arr));

		int max = Integer.MIN_VALUE;
		int ans = 0;

		for (int i = 0; i < N; i++) {
			int cur = arr[i]; // 현재 비교 대상
			int cnt = 0; // 비교 대상 만큼 카운팅 되면 하나의 그룹으로

			for (int j = i; j < N; j++) {
				// 카운팅이 완성되면 하나의 그룹으로 치기
				if (cnt == cur) {
					cur = arr[j]; // 다음 그룹을 만들기 위해 cur 변경
					ans++; // 각 단계마다의 max 값 저장하기 위하여
					cnt = 0; // 카운트 초기화
				}

				if (cur >= arr[j]) cnt++;

			}

			// 최댓값 비교
			max = Math.max(max, cnt);

		}

		System.out.println(max);

	}
}
