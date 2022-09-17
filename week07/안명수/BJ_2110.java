package algo.BJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2110 {
	static int N, C;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		// 인접 공유기 간의 최소거리와 최대거리
		int min = 1;
		int max = arr[N-1] - arr[0];
		
		int answer = 0;
		while(min <= max) {
			
			// 최소거리와 최대거리의 중간값 기준으로 확인
			int mid = (min + max) / 2;
			
			// 공유기 하나는 설치하고 시작
			int count = 1;
			
			// 이전 공유기 좌표
			int prev = arr[0];
			for(int i = 1; i < N; i++) {
				
				// 원하는 거리값이 가능한 위치라면 공유기 설치 및 이전 공유기 위치 갱신
				if(arr[i] - prev >= mid) {
					prev = arr[i];
					count++;
				}
			}
			
			// 설치한 공유기 개수가 조건보다 많으면 더 큰 값이 가능한지 확인
			if(count >= C) {
				min = mid + 1;
				answer = Math.max(answer, mid);
			}else max = mid - 1; // 현재 값보다 적은값 확인
		}
		
		System.out.println(answer);
	}
}
