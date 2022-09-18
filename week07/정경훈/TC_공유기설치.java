package baekjoon.공유기설치_2110;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] house;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		house = new int[N];
		
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		int rst = 0;
		int l = 0;
		int r = house[N-1] - house[0];
		
		while(l <= r) {
			int mid = (l + r) / 2;
			int cnt = 1;
			int prev = house[0];
			for (int i = 0; i < N; i++) {
				if(house[i] - prev >= mid) {
					cnt++;
					prev = house[i];
				}
			}
			if( cnt < C ) {
				r = mid-1;
			}else {
				l = mid+1;
				if(rst<mid) rst = mid;
			}
		}
		
		System.out.println(rst);
	}
}
