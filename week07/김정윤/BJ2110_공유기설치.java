package week7.김정윤;

import java.io.*;
import java.util.*;

public class BJ2110_공유기설치 {
	static int N, C;
	static int[] house;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for (int n = 0; n < N; n++) {
			house[n] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		int min = 1; // 공유기 설치 최소 거리
		int max = house[N-1] - house[0] + 1; // 공유기 설치 최대 거리
		
		while (min < max) {
			int mid = (min + max) / 2; // 중간값
			
			// 첫번째 집 무조건 설치
			int installed = house[0]; // 현재 설치된 공유기 위치
			int cnt = 1; // 설치된 공유기 개수
			
			for (int i = 1; i < N; i++) {
				int locate = house[i]; // 공유기를 설치할 위치
				if (locate - installed >= mid) { // 인접한 공유기 사이의 거리 측정
					cnt++; // 설치 확인 및 개수 증가
					installed = locate; // 현재 설치된 공유기 위치 재설정
				}
			}
			
			if (cnt < C) { // 공유기를 다 설치하지 못한 경우
				max = mid; // 거리 최댓값 줄이기
			} else { // 공유기가 전부 설치된 경우
				min = mid + 1; // 거리 최솟값 높이기
			}
		}
		System.out.println(min-1); // 인접한 두 공유기 사이 거리 최댓값

	}

}
