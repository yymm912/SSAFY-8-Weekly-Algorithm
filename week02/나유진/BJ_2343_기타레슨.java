package BJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_2343_guitar_lesson {
	static int N, M, ans = Integer.MAX_VALUE;
	static int[] Array;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		s = br.readLine().split(" ");

		Array = new int[s.length];
		int n = 0; // 배열 저장에만 쓰일 idx 변수
		int max = Integer.MIN_VALUE; // 동영상 최대 길이
		int total = 0; // 동영상 합

		for (String string : s) {
			int b = Integer.parseInt(string);
			Array[n++] = b;
			max = Math.max(max, b);
			total += b;
		}

		int std = (total + max) / 2; // standard 블루레이 길이, 정답변수
		int start = max, end = total;

		while (start <= end) { // mid 조건
			int blu_ray = 0; // 계산한 블루레이 갯수
			int sum = 0; // 블루레이 길이 계산할 변수
			int idx = 0; // array 접근 할 index 변수
			while (idx < N && blu_ray <= M) { // array를 돌면서 블루레이 갯수 계산, 블루레이 갯수가 문제에서 준 갯수보다 커지면 나감
				sum += Array[idx]; // 현재 블루레이에 누적 합
				if (sum < std) {// 합이 std 보다 작으면 다음 동영상 추가하러감
					idx++;
					continue;
				} else if (sum >= std) { // 합이 같으면 블루레이 갯수 추가 후 sum을 0으로 초기화
					if (sum == std)
						idx++;
					blu_ray++;
					sum = 0;
				}
			}

			if (sum > 0)
				blu_ray++;

			if (blu_ray > M) {
				start = std +1;
			} else if (blu_ray <= M) {
				if(std<ans)ans =std;
				end = std - 1;
			}

			std = (start + end) / 2;
		}

		System.out.print(ans);

	}
}
