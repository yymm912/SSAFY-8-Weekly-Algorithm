package ssafy;

import java.io.*;
import java.util.*;

public class SW_Shuffle_O_Matic {
	
	static StringBuilder sb = new StringBuilder();
	static int T, N, M, ans;
	static int[] input, left, right;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			M = N/2;
			input = new int[N];
			left = new int[M];
			right = new int[M];
			
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(stk.nextToken());
			}
			
			ans = 100;
			solve(input, 0);
			if (ans == 100) ans = -1;
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static void solve(int[] arr, int cnt) {
		if (cnt == 6 || cnt >= ans) return;
		if (isUp(arr) || isDown(arr)) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		for (int i = 1; i < N; i++) {
			solve(shuffle(arr, i), cnt+1);
		}
	}
	
	static int[] shuffle(int[] arr, int c) {
		divide(arr);	// left, right 준비
		
		if (c < M) {
			return shuffleL(c);
		}
		else return shuffleR(N-1-c);
	}
	
	static int[] shuffleL(int c) {
		// 기준색 M-c 개 넣어두고, 섞기
		int[] tmp = new int[N];
		int idx = 0, li = 0, ri = 0;
		
		for (; li < M-c; li++) {
			tmp[idx++] = left[li];
		}
		for (; li < M && ri < M; li++, ri++) {
			tmp[idx++] = right[ri];
			tmp[idx++] = left[li];
		}
		for (; ri < M; ri++) {
			tmp[idx++] = right[ri];
		}
		
		return tmp;
	}
	
	static int[] shuffleR(int c) {
		int[] tmp = new int[N];
		int idx = 0, li = 0, ri = 0;
		
		for (; ri < M-c; ri++) {
			tmp[idx++] = right[ri];
		}
		for (; li < M && ri < M; li++, ri++) {
			tmp[idx++] = left[li];
			tmp[idx++] = right[ri];
		}
		for (; li < M; li++) {
			tmp[idx++] = left[li];
		}
		
		return tmp;
	}
	
	static void divide(int[] arr) {
		for (int i = 0; i < M; i++) {
			left[i] = arr[i];
		}
		for (int i = 0; i < M; i++) {
			right[i] = arr[M+i];
		}
	}
	
	static boolean isUp(int[] arr) {
		for (int i = 0; i < N; i++) {
			if (arr[i] != i+1) return false;
		}
		return true;
	}
	
	static boolean isDown(int[] arr) {
		for (int i = 0; i < N; i++) {
			if (arr[i] != N-i) return false;
		}
		return true;
	}
	
}
