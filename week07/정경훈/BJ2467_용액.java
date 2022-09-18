package baekjoon.용액_2467;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, min, minLeft, minRight;
	static int[] sol;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		minLeft = 0;
		minRight = 0;
		sol = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sol[i] = Integer.parseInt(st.nextToken());
		}
		// -99 -2 -1 4 98
		
		int l = 0;
		int r = N-1;
		while(l < r) {
			int sum = Math.abs(sol[l] + sol[r]);
			if( min > sum ) {
				min = sum;
				minLeft = l;
				minRight = r;
			}
			if( sol[l] + sol[r] >= 0 ) { // 만약 sum이 0보다 컷다면 
				r--; // 양수쪽 줄이기
			}else { // 0보다 작았다면
				l++; // 음수쪽 늘리기
			}
		}
		
		System.out.println(sol[minLeft] + " " + sol[minRight]);

	}

}
