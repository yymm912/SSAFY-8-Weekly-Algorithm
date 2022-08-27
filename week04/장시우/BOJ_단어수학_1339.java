package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_단어수학_1339 {
	
	static int N, ans = 0;
	static Integer[] alpha;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		alpha = new Integer[26];
		for(int i = 0; i < 26; i++) {
			alpha[i] = 0;
		}
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int len = str.length();
			for (int j = 0; j < str.length(); j++) {
				alpha[str.charAt(j) - 'A'] += (int)Math.pow(10, len - 1);
				len -= 1;
			}
		}
		
		Arrays.sort(alpha, Collections.reverseOrder());
		
		int multiple = 9;
		for (int i = 0; i < 26; i++) {
			if (alpha[i] > 0) {
				ans += alpha[i] * multiple;
				multiple -= 1;
			}
		}
		
		System.out.println(ans);
	}
}
