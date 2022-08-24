package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_일곱난쟁이_2309 {
	
	static int[] src = new int[9];
	static int[] tgt = new int[7];
	static int[] ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i < 9; i++) {
			src[i] = sc.nextInt();
		}
		
		comb(0, 0, 0);
		Arrays.sort(ans);
		printComb();
	}
	
	static void comb(int srcIdx, int tgtIdx, int sum) {
		if (tgtIdx == tgt.length) {
			if (sum == 100) {
				ans = Arrays.copyOfRange(tgt, 0, 7);
			}
			return;
		}
		
		if (srcIdx == src.length) {
			return;
		} 
		
		tgt[tgtIdx] = src[srcIdx];
		
		comb(srcIdx + 1, tgtIdx + 1, sum + tgt[tgtIdx]);
		comb(srcIdx + 1, tgtIdx, sum);
				
//		for (int i = srcIdx; i < 9; i++) {
//			tgt[tgtIdx] = src[i];
//			comb(i + 1, tgtIdx + 1, sum + tgt[tgtIdx]);
//		}
	}
	
	static void printComb() {
		for (int i = 0; i < 7; i++) {
			System.out.println(ans[i]);
		}
	}
}
