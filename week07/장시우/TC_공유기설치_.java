package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_공유기설치_2110 {
	
	static int N, C, ans;
	static int[] houses;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		houses = new int[N];
		
		// 거리의 최솟값
		int left = 1;
		// 거리의 최댓값
		int right = 0;
		
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			houses[i] = temp;
			right = Math.max(right, temp);
		}
		
		right -= left;
		
		Arrays.sort(houses);
		
		// 이분탐색으로 가장 인접한 두 공유기 사이의 거리를 구한다.
		while (left <= right) {
			int mid = (left + right) / 2;
			
			if (canInstall(mid)) {
				ans = Math.max(ans, mid);
				left = mid + 1;
			}
			else right = mid - 1;
		}
		
		System.out.println(ans);
	}
	
	static boolean canInstall(int dist) {
		int count = C - 1;
		int prevIdx = 0;
		
		for (int i = 1; i < houses.length; i++) {
			if (houses[i] - houses[prevIdx] >= dist) {
				prevIdx = i;
				count--;
			}
		}
		
		if (count > 0) return false;
		else return true;
	}
}
