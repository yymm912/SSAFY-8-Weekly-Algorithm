package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110공유기설치 {

	static int N, C;
	static int house[];
	static int wifi[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 집 개수 
		C = Integer.parseInt(st.nextToken()); // 공유기 개수 
		house = new int[N];
		wifi = new int[N];
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine()); // 집들의 위치 
		}
		
		// 집 위치 오름차순으로 정렬
		Arrays.sort(house);
		
		// 거리를 탐색 범위로 잡고 이분탐색 -> 해당 거리에 대해 설치 가능한 공유기 개수에 따라 탐색하는 거리 범위 좁히기
		int left = 1;
		int right = house[N-1];
		int center = 0;
	
		// 2 4 7 8 
		while(left < right) {
			center = ( left + right ) / 2; // center: 최소 거리 
			
			    // 첫 번째 집은 항상 공유기 설치 
				int count = 1;
				int lastLocate = house[0];
					
                // 최대한 공유기를 넓게 사용할 수 있는 거리 찾기
				for(int i = 1; i < house.length; i++) {
					int locate = house[i];
					
					if(locate - lastLocate >= center) {
						count++; // 공유기 설치 개수 
						lastLocate = locate;
					}
				}
				if( count < C ) right = center; // right 10 -> 5 -> 4 
				else left = center + 1; // 1 -> 4
			
		
		}
		
		System.out.println(left - 1);
		
	}

}
