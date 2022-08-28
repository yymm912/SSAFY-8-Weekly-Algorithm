package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_야구_17281 {
	
	static int N, ans;
	static int[][] players;
	static int[] src = new int[9];
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		players = new int[N][9];
		for (int i = 0; i < 9; i++) src[i] = i;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				players[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while (true) {
			if (src[3] == 0) {
				check();
			}
			
			if (!np()) {
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static void check() {
		//	점수
		int score = 0;
		//	루에 있는 주자를 기록하는 배열
		boolean[] base = new boolean[4];
		//	출전 선수 순번
		int idx = 0;
		//	이닝 반복
		for (int i = 0; i < N; i++) {
			//	아웃 카운트 => 3이 되면 다음 이닝
			int outCount = 0;
			Arrays.fill(base, false);
			while (outCount < 3) {
				base[0] = true;
				int behavior = players[i][src[idx]];
				if (behavior == 0) {
					outCount++;
					base[0] = false;
				} else if (behavior == 1) {
					if (base[3]) score++;
					for (int j = 3; j > 0; j--) {
						base[j] = base[j - 1];
					}
					base[0] = false;
				} else if (behavior == 2) {
					if (base[3]) score++;
					if (base[2]) score++;
					for (int j = 3; j > 1; j--) {
						base[j] = base[j - 2];
					}
					base[1] = false;
					base[0] = false;
				} else if (behavior == 3) {
					if (base[3]) score++;
					if (base[2]) score++;
					if (base[1]) score++;
					base[3] = base[0];
					base[2] = false;
					base[1] = false;
					base[0] = false;
				} else if (behavior == 4) {
					if (base[3]) score++;
					if (base[2]) score++;
					if (base[1]) score++;
					if (base[0]) score++;
					base[3] = false;
					base[2] = false;
					base[1] = false;
					base[0] = false;
				}
				
				if (++idx >= 9) idx = 0;
			}
		}
		
		ans = Math.max(ans, score);
	}
	
	static boolean np() {
		int i = src.length - 1;
		while (i > 0 && src[i - 1] >= src[i]) --i;
		
		if (i == 0) return false;
		
		int j = src.length - 1;
		while (src[i - 1] >= src[j]) --j;
		
		swap(i - 1, j);
		
		int k = src.length - 1;
		while (i < k) swap(i++, k--);
		
		return true;
	}
	
	static void swap(int i, int j) {
		int temp = src[i];
		src[i] = src[j];
		src[j] = temp;
	}
}
