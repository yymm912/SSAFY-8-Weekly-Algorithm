package boj;

import java.util.Scanner;

public class BOJ_이진수_3460 {
	
	static int T, n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			n = sc.nextInt();
			String str = Integer.toBinaryString(n);
			
			for (int i = str.length() - 1; i >= 0; i--) {
				if (str.charAt(i) == '1') {
					sb.append(str.length() - i - 1);
					sb.append(" ");
				}
			}
		}
		
		System.out.println(sb);
	}
}
