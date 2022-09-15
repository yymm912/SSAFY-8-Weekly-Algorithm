package week7.김정윤;

import java.io.*;
import java.util.*;

public class BJ2467_용액 {
	static int N;
	static int[] liquid;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		liquid = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = 0, max = N-1; // 용액 특성 최솟, 최대 자리값
		int rst = Math.abs(liquid[min] + liquid[max]);
		int left = 0, right = 0; // 선택된 용액의 자리값
		
		while (min < max) {
			int sum = liquid[min] + liquid[max]; // 용액 혼합 값
			if (rst > Math.abs(sum)) { // 값이 최소일 경우
				rst = Math.abs(sum);
				// 혼합값이 최소인 용액의 각 자릿값
				left = min;
				right = max;
			}
			if (sum >= 0) max--; // 합이 양수인 경우 -> 최대 자릿값 -1
			else min++; // 합이 음수인 경우 -> 최소 자릿값 + 1
		}
		System.out.println(liquid[left] + " " + liquid[right]);
		
	}
}
