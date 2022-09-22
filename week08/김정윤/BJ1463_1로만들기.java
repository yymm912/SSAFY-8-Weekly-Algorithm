package week8.김정윤;

import java.util.*;
import java.io.*;

public class BJ1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(make1(N, 0));
		
	}
	static int make1(int N, int cnt) {
		System.out.println(N + ":" + cnt);
		if (N < 2) {
			return cnt;
		}
		
		return Math.min(make1(N/2, cnt+1 + (N%2)), make1(N/3, cnt+1 + (N%3)));
	}
}
