package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2343_기타레슨 {
	
	static int N, M, result;
	static int[] blarr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 블루레이 영상 전체 길이
		M = Integer.parseInt(st.nextToken()); // 나누어야 할 수
		
		st = new StringTokenizer(br.readLine());
		blarr = new int[N];
		int max = 0;
		
		for (int i = 0; i < blarr.length; i++) { // 배열 생성
			blarr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, blarr[i]); // 이진 탐색을 할 때 가장 큰 값을 좌측값으로 할당하기 위해 저장
		}
		
		result = searchMin(max, 1000000000); // 최댓값과 가능한 전체 강의 최대 길이 (최대 강의 개수 100_000개 x 각 강의 최대 길이10_000m)
		
		System.out.println(result);
		
	}
	
	
	public static int searchMin(int left, int right) {
		
		while(left <= right) { //좌측값이 우측값을 초과하면 종료하고 좌측값을 리턴
			
			int avg = (left + right) /2; // 중간값인 좌우측값의 평균
			int sum = 0; //
			int cnt = 1; // 블루레이 강의 그룹의 수
			
			for (int i = 0; i < N; i++) {
				sum += blarr[i]; // 강의 하나씩 sum에 할당
				if(sum > avg) { // 강의를 합친 값이 중간값을 넘어서면
					sum = blarr[i]; // sum에 해당 강의 길이로 초기화
					cnt++; // 그룹을 하나 더 늘린다.
				}
			}
			if (cnt > M) // 설정한 블루에이 개수를 넘어서면
				left = avg + 1; // 좌측값을 중간값의 + 1
			else
				right = avg - 1;// 넘어서지 않으면 - 1

		}
		return left; // 최종적으로 남은 좌측값 출력
	}

}
