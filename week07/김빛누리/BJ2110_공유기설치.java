package week07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110_공유기설치 {

	static int N, C, minX, maxX;	// 집 개수, 공유기 개수
	static int[] location;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		location = new int[N];	// 집 위치 기록할 배열
		for (int i = 0; i < N; i++) {
			location[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(location);
		
		int result = 0;
		
		int left = 0;	// 가능한 공유기 거리 최소
		int right = (location[N-1]-location[0]) / (C-1) + 1;	// 가능한 공유기거리 최대
		int mid = 0;
		while(left <= right) {
			mid = (right+left) / 2;
			
			if(setWifi(mid)) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}

	static boolean setWifi(int mid) {
		int cnt = C-1;	//공유기 개수, 시작점에 하나 놓고 시작
		
		int prev = location[0];	// 이전 공유기 위치
		for (int i = 1; i < location.length; i++) {	
			while(true) {
				if(i == location.length) return false;	// 모든 집 확인했는데 공유기 못놓은 경우 false
				
				if(location[i++] - prev >= mid) break;
			}
			prev = location[--i];	// 이전 공유기 위치 업데이트, while문 내부에서 하나 더 늘어나므로 빼주기
			cnt--;
			
			if(cnt == 0) return true;	// 공유기 다 놓았으면 더이상 확인 X
		}
		
		return false;
	}

}
