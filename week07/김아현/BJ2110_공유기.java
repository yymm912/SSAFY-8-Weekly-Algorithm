package binary_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110_공유기 {

	static int N, C, max;
	static int[] house;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		house = new int[N];
		for(int i=0; i<N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house); // 이진탐색을 위한 정렬
		
		int min = 1; // 공유기가 설치된 집과의 최소거리
		int max = house[N-1] - house[0]; // 최대거리
		
		// 나란히 붙어있는 두집만 있을 경우 min값과 max값이 일치하기 때문에
		// min max가 같을 경우에도 탐색할 수 있도록 해야함
		while(min <= max) {
			int mid = (min + max) / 2; // 공유기 설치 기준거리
			int cnt = 1; // 공유기 설치 갯수
			int prev = house[0]; // 최근에 공유기를 설치한 집 (거리계산 기준점)
			
			for (int i = 1; i < N; i++) {
				if(house[i] - prev >= mid) {
					prev = house[i];
					cnt++;
				}
			}
			
			// 설치해야 하는 공유기 갯수보다 적게 설치했다면
			// 기준거리가 멀다는 것이므로 상한값을 내림
			if(C > cnt) max = mid - 1;
			// 설치해야 하는 공유기 갯수보다 많이 설치했다면
			// 기준거리가 좁다는 것이므로 하한값을 올림
			// + 두 공유기 사이의 거리를 최대로 해야 하므로 갯수가 같아졌을 때도 하한값 올림
			else min = mid + 1;
		}
		
		System.out.println(min-1);
	}

}
