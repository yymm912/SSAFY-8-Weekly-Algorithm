package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_암호만들기_1759 {
	
	static int N, C;
	static char[] src;
	static boolean[] selected;
	static List<Character> vowel = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		src = new char[C];
		selected = new boolean[C];
		
		vowel.add('a');
		vowel.add('e');
		vowel.add('i');
		vowel.add('o');
		vowel.add('u');
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			src[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(src);
		subset(0);
	}
	
	static void subset(int srcIdx) {
		if (srcIdx == C) {
			int vowelCnt = 0;
			int consonantCnt = 0;
			
			for (int i = 0; i < C; i++) {
				if (!selected[i]) {
					continue;
				}
				
				if (vowel.contains(src[i])) {
					vowelCnt++;
				} else {
					consonantCnt++;
				}
			}
			
			if (vowelCnt >= 1 && consonantCnt >= 2 && vowelCnt + consonantCnt == N) {
				printSubset();
			}
			return;
		}
		
		selected[srcIdx] = true;
		subset(srcIdx + 1);
		
		selected[srcIdx] = false;
		subset(srcIdx + 1);
	}
	
	static void printSubset() {
		for (int i = 0; i < C; i++) {
			if (selected[i]) {
				System.out.print(src[i]);
			}
		}
		System.out.println();
	}
}
