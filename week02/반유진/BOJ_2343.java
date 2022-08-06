// BOJ 2343번 기타 레슨 

package BOJ.Binary_Search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = 0;

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right += arr[i]; // 총합
			left = left < arr[i] ? arr[i] : left; // 최대값 (총합 중 가장 작을 값)
		}

		// 1 2 3 4 5 6 7 8 9
		while (left <= right) {
			int mid = (left + right) / 2; // 임의의 블루레이 크기
			int sum = 0; // 강의 시간
			int cnt = 0; // 블루레이 개수
			for (int i = 0; i < N; i++) {
				sum += arr[i];
				if (sum > mid) {
					sum = arr[i];
					cnt++;
				}
			}
			if (sum > 0) // 남은 강의들로 하나 ++
				cnt++;

			if (cnt <= M)
				right = mid - 1;
			else
				left = mid + 1;

		}
		System.out.println(left); // 크기의 최솟값을 구해야 하니까 left 출력
	}
}
