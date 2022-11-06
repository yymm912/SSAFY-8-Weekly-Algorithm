package boj;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_약수구하기_2501 {
	
	static int N, K;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		int cnt = 0;
		
		for (int i = 1; i <= N; i++) {
			if (N % i == 0) {
				cnt++;
				if (cnt == K) {
					System.out.println(i);
					return;
				}
			}
		}
		
		System.out.println(0);
	}
}
