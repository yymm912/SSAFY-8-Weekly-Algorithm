package boj;

import java.util.Scanner;

public class BOJ_모든순열_10974 {
	
	static int N;
	static int[] array, tgt;
	static boolean[] selected;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = (i + 1);
		}
		
		tgt = new int[N];
		selected = new boolean[N];
		perm(0);
	}
	
	static void perm(int tgtIdx) {
		if (tgtIdx == tgt.length) {
			printPerm();
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			if (selected[i]) {
				continue;
			}
			
			tgt[tgtIdx] = array[i];
			selected[i] = true;
			perm(tgtIdx + 1);
			selected[i] = false;
		}
	}
	
	static void printPerm() {
		for (int i = 0; i < tgt.length; i++) {
			System.out.print(tgt[i] + " ");
		}
		System.out.println();
	}
}
